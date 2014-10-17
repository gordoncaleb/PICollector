package com.gordoncaleb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.gordoncaleb.domain.pi.contact.PhysicalAddress;
import com.gordoncaleb.persistence.repository.PhysicalAddressRepository;
import com.gordoncaleb.persistence.repository.SchoolRepository;
import com.gordoncaleb.schoolsitelocator.School;
import com.gordoncaleb.schoolsitelocator.SchoolSiteLocatorAPI;

@ComponentScan
@EnableAutoConfiguration
public class SchoolUpdater implements CommandLineRunner {

	@Autowired
	private SchoolRepository schoolRepo;

	@Autowired
	private PhysicalAddressRepository addressRepo;

	public static void main(String[] args) throws Exception {

		SpringApplication app = new SpringApplication(SchoolUpdater.class);

		app.run(args);

	}

	@Override
	public void run(String... arg0) throws InterruptedException {

		System.out.println("Getting addresses without school info");

		Page<PhysicalAddress> needSchoolInfo = addressRepo
				.findByEmptySchools(new PageRequest(0, 55232));

		System.out.println("Page shows " + needSchoolInfo.getNumberOfElements()
				+ " need school info");

		int i = 0;

		Map<String, School> knownSchools = new HashMap<String, School>();

		SchoolSiteLocatorAPI locator = new SchoolSiteLocatorAPI();

		for (PhysicalAddress addr : needSchoolInfo) {
			System.out.println(addr);

			try {
				Map<String, String> schools = locator.getSchoolCodes(addr);

				addr.setSchoolCodes(new ArrayList<String>(schools.keySet()));

				System.out.println("Got schools for "
						+ addr.generateMailingAddress() + " \nSchools: "
						+ schools);

				addressRepo.save(addr);

				List<String> needSchoolInfos = new ArrayList<String>();

				for (String code : schools.keySet()) {

					if (knownSchools.get(code) == null) {
						School s = schoolRepo.findOneBySchoolCode(code);

						if (s != null) {
							knownSchools.put(code, s);
						} else {
							needSchoolInfos.add(code);
						}
					}
				}

				if (!needSchoolInfos.isEmpty()) {
					List<School> newSchools = locator
							.getSchoolInfo(needSchoolInfos);

					for (School s : newSchools) {
						s.setType(schools.get(s.getSchoolCode()));
					}

					System.out.println("Saving new schools: ");
					for (School s : newSchools) {
						System.out.println(s);
					}
					
					schoolRepo.save(newSchools);

				}

				System.out.println("Got #" + i);
				i++;

				Thread.sleep(200);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		System.out.println("Done");

	}

}
