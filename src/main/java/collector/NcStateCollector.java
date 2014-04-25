package collector;

import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import pi.Person;
import pi.contact.EmailAddress;
import pi.contact.Phone;
import pi.name.Name;
import util.IOUtils;
import util.StringUtils;

public class NcStateCollector extends Collector {

	String peopleURI;

	public static void main(String[] args) {
		Person p = new Person();
		p.setLastName(new Name("Brown"));
		p.setFirstName(new Name("Otis"));

		NcStateCollector collector = new NcStateCollector();

		System.out.println("Before: " + p);
		collector.collectInfo(p);

		System.out.println("After: " + p);
	}

	public NcStateCollector() {
		try {
			peopleURI = IOUtils.getResource("/collectors", "universities", "ncstate", "people_uri.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	Person collectInfo(Person p) {
		try {
			// String resPage = IOUtils.getResource("/collectors",
			// "universities", "ncstate", "beasley_mark_search.htm");

			System.out.println("Collecting data on " + p.getFirstName() + " " + p.getLastName());

			String personURI = peopleURI.replaceAll("%lastname%", p.getLastName().getName()).replaceAll("%firstname%", p.getFirstName().getName());

			System.out.println("Getting URI " + personURI);

			Document doc = Jsoup.connect(personURI).get();

			// Document doc = Jsoup.parse(resPage);

			Elements searchResults = doc.select("div.searchresult");

			//System.out.println("Found " + searchResults.size() + " searchResults");

			if (searchResults.size() > 0) {

				Elements tbodies = searchResults.get(0).select("tbody");

				//System.out.println("Found " + tbodies.size() + " tbodies");

				for (Element e : tbodies) {

					Elements rightElements = e.select("td[align=right]");

					if (!rightElements.isEmpty()) {

						String rightEl = rightElements.get(0).text();

						// System.out.println("Right Element = " + rightEl);

						List<List<String>> emailMatches = StringUtils.getMatches(StringUtils.emailRegex, rightEl);

						if (!emailMatches.isEmpty()) {
							EmailAddress emailAddress = new EmailAddress(emailMatches.get(0).get(0));
							p.addEmailAddress(emailAddress);
							System.out.println(emailAddress);
						}

						List<List<String>> phoneMatches = StringUtils.getMatches("Phone: ?([-0-9]+)", rightEl);

						if (!phoneMatches.isEmpty()) {
							Phone phone = new Phone(phoneMatches.get(0).get(1));
							p.addPhone(phone);
							System.out.println(phone);
						}

					}

					// Elements farRightElements =
					// e.select("td[align=right],[valign=middle]");
					//
					// if (!farRightElements.isEmpty()) {
					// Elements links = farRightElements.select("a[href*=evc]");
					//
					// for (Element link : links) {
					// String vcarUrl = StringUtils.getBaseURL(personURI) + "/"
					// + link.attr("href");
					// System.out.println("Get? " + vcarUrl);
					// Document vcard = Jsoup.connect(vcarUrl).get();
					// System.out.println(vcard.text());
					// }
					// }

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		p.setUpdated(new Date());

		return p;

	}
}
