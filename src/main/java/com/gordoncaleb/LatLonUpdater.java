package com.gordoncaleb;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.gordoncaleb.domain.pi.contact.PhysicalAddress;
import com.gordoncaleb.geocoding.google.GoogleGeoCode;
import com.gordoncaleb.geocoding.google.GeoCodeAPI;
import com.gordoncaleb.persistence.repository.PhysicalAddressRepository;
import com.gordoncaleb.util.StringUtils;

@ComponentScan
@EnableAutoConfiguration
public class LatLonUpdater implements CommandLineRunner {

	@Autowired
	private PhysicalAddressRepository addressRepo;

	@Autowired
	private MongoTemplate mongoTemplate;

	public static void main(String[] args) throws Exception {

		SpringApplication app = new SpringApplication(LatLonUpdater.class);

		app.run(args);

	}

	@Override
	public void run(String... arg0) throws InterruptedException {

		// List<PhysicalAddress> addresses = CSVDeserializer
		// .readCSVAddressFile("./needlatlon.csv");
		//
		// System.out.println("Adding " + addresses.size()
		// + " addresses to mongo!");
		// addressRepo.save(addresses);

		// updateLatLon();

		// export("e:\\addresses_with_latlon.csv");

		processAll();
	}

	public void processAll() {

		Page<PhysicalAddress> addressPage;

		addressPage = addressRepo.findAll(new PageRequest(0, 1000));
		processAddresses(addressPage.getContent());

		while (addressPage.hasNext()) {
			addressPage = addressRepo.findAll(addressPage.nextPageable());
			processAddresses(addressPage.getContent());
		}

	}

	public void processAddresses(List<PhysicalAddress> addresses) {

		for (PhysicalAddress addr : addresses) {

			parseZipFromFormattedAddress(addr);

		}

		addressRepo.save(addresses);
	}

	public void parseZipFromFormattedAddress(PhysicalAddress addr) {
		
		
	}

	public void updateLatLon() {

		System.out.println("Getting addresses without lat/lons");

		System.out.println();

		// List<PhysicalAddress> needLats =
		// addressRepo.findByGeoCodeExists(false,
		// new PageRequest(0, 3));

		for (int api = 0; api < GeoCodeAPI.APIKEY.length; api++) {

			GeoCodeAPI.apiIndex = api;

			for (int n = 0; n < 4; n++) {
				Page<PhysicalAddress> needLats = addressRepo.findByEmptyGeoCode(new PageRequest(0, 625));

				System.out.println("Page shows " + needLats.getTotalElements() + " need lat/lons");

				int i = 0;

				for (PhysicalAddress addr : needLats) {
					System.out.println(addr);

					try {

						if (addr.getGeoCode() == null || addr.getGeoCode().isEmpty()) {

							List<GoogleGeoCode> geoCodes = GeoCodeAPI.queryForGeoCode(addr.generateMailingAddress());

							addr.setGeoCode(geoCodes);

							System.out.println("Got geocode for " + addr.generateMailingAddress() + " \nGeocode:\n" + geoCodes);

							addressRepo.save(addr);

							System.out.println("Got #" + i);
							i++;

							Thread.sleep(200);
						}

					} catch (Exception e) {
						e.printStackTrace();
						break;
					}

				}

			}

		}

		System.out.println("Done");

	}

	public void export(String fileName) {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {

			Page<PhysicalAddress> addressPage;

			addressPage = addressRepo.findAll(new PageRequest(0, 1000));
			writeToFile(addressPage.getContent(), bw);

			while (addressPage.hasNext()) {
				addressPage = addressRepo.findAll(addressPage.nextPageable());
				writeToFile(addressPage.getContent(), bw);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void writeToFile(List<PhysicalAddress> addresses, BufferedWriter bw) throws IOException {

		for (PhysicalAddress a : addresses) {

			String lat = "";
			String lon = "";

			List<GoogleGeoCode> geoCodes = a.getGeoCode();

			if (geoCodes != null && !geoCodes.isEmpty()) {
				lat = geoCodes.get(0).getGeometry().getLocation().getLat();
				lon = geoCodes.get(0).getGeometry().getLocation().getLng();
			}

			String line = StringUtils.csv(a.getFipsCode(), a.getUnformattedApn(), a.getHouseNumber(), a.getStreetName(), a.getMode(), a.getCity(),
					a.getState(), lat, lon);

			bw.write(line);
			bw.newLine();

		}
	}
}
