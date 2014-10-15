package com.gordoncaleb.collector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gordoncaleb.domain.pi.Person;
import com.gordoncaleb.domain.pi.contact.EmailAddress;
import com.gordoncaleb.domain.pi.contact.Phone;
import com.gordoncaleb.util.IOUtils;

public class ECUCollector extends Collector {

	private String peopleURI;

	public ECUCollector() {

		try {
			peopleURI = IOUtils.getResource("/collectors", "universities", "ecu", "people_uri.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Person> collectInfo(List<Person> people) {

		for (Person p : people) {
			collectInfo(p);
		}

		return people;
	}

	@Override
	public Person collectInfo(Person p) {

		try {
			// String resPage = IOUtils.getResource("/collectors",
			// "universities", "ecu", "example_search_results",
			// "js_search.htm");

			String personURI = peopleURI.replaceAll("%lastname%", p.getLastName().getName()).replaceAll("%firstname%", p.getFirstName().getName());

			System.out.println("Getting URI " + personURI);

			Document doc = Jsoup.connect(personURI).get();

			// Document doc = Jsoup.parse(resPage);

			Elements tables = doc.select("table");

			// System.out.println("Found " + tables.size() + " tables");

			if (tables.size() > 0) {

				Elements tbodies = tables.get(0).select("tbody");

				// System.out.println("Found " + tbodies.size() + " tbodies");
				for (Element e : tbodies) {

					Elements namesElements = e.select("td[itemprop=name]");
					Elements emailElements = e.select("td[itemprop=email]");
					Elements phoneElements = e.select("td[itemprop=telephone]");
					// Elements mailStop = e.select("tr[itemprop=name]");
					// Elements office = e.select("td[itemprop=name]");
					// Elements dept = e.select("td[itemprop=name]");

					if (namesElements.size() > 0) {

						List<String> namesList = new ArrayList<String>(Arrays.asList(namesElements.get(0).text().split(" ")));

						if (p.nameMatch(namesList)) {

							if (emailElements.size() > 0) {
								String email = emailElements.get(0).text();
								System.out.println("Email:" + email);
								p.addEmailAddress(new EmailAddress(email));
							}

							if (phoneElements.size() > 0) {
								String phone = phoneElements.get(0).text();
								System.out.println("Phone:" + phone);
								p.addPhone(new Phone(phone));
							}

						}
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		p.setUpdated(new Date());

		return p;

	}

}
