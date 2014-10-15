package com.gordoncaleb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.gordoncaleb.domain.pi.contact.PhysicalAddress;
import com.gordoncaleb.geocoding.GeoCode;
import com.gordoncaleb.geocoding.GeoCodeAPI;
import com.gordoncaleb.persistence.repository.PhysicalAddressRepository;

@ComponentScan
@EnableAutoConfiguration
public class Application implements CommandLineRunner {

	@Autowired
	private PhysicalAddressRepository addressRepo;

	public static void main(String[] args) throws Exception {

		SpringApplication app = new SpringApplication(Application.class);

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

		System.out.println("Getting addresses without lat/lons");

		// List<PhysicalAddress> needLats =
		// addressRepo.findByGeoCodeExists(false,
		// new PageRequest(0, 3));

		for (int api = 0; api < GeoCodeAPI.APIKEY.length; api++) {
			
			GeoCodeAPI.apiIndex = api;

			for (int n = 0; n < 4; n++) {
				Page<PhysicalAddress> needLats = addressRepo
						.findByEmptyGeoCode(new PageRequest(0, 625));

				System.out.println("Page shows " + needLats.getTotalElements()
						+ " need lat/lons");

				int i = 0;

				for (PhysicalAddress addr : needLats) {
					System.out.println(addr);

					try {
						List<GeoCode> geoCodes = GeoCodeAPI
								.queryForGeoCode(addr.generateMailingAddress());

						addr.setGeoCode(geoCodes);

						System.out.println("Got geocode for "
								+ addr.generateMailingAddress()
								+ " \nGeocode:\n" + geoCodes);

						addressRepo.save(addr);

						System.out.println("Got #" + i);
						i++;

						Thread.sleep(200);

					} catch (Exception e) {
						e.printStackTrace();
						break;
					}

				}

			}

		}

		System.out.println("Done");

	}

}
