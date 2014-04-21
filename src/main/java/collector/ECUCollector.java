package collector;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import pi.Person;
import util.IOUtils;

public class ECUCollector implements Collector {

	private String peopleRegex;
	private String peopleURI;

	private int nameGroupNum;
	private int emailGroupNum;
	private int mailstopGroupNum;
	private int officeGroupNum;
	private int deptGroupNum;

	private ECUCollector() {
		try {

			String peopleRegexFile = IOUtils.getResource("/collectors",
					"universities", "ecu", "people_regex.txt");

			List<List<String>> fields = IOUtils.getMatches("%([^%]+)%",
					peopleRegexFile);

			System.out.println(fields);

			peopleRegex = peopleRegexFile;

			for (int i = 0; i < fields.size(); i++) {

				switch (fields.get(i).get(1)) {
				case "name":
					nameGroupNum = i;
					break;
				case "emailaddress":
					emailGroupNum = i;
					break;
				case "mailstop":
					mailstopGroupNum = i;
					break;
				case "office":
					officeGroupNum = i;
					break;
				case "department":
					deptGroupNum = i;
					break;
				}

				peopleRegex = peopleRegex.replaceAll(fields.get(i).get(0),
						"(\\\\w+)");

			}

			System.out.println(peopleRegex);

			peopleURI = IOUtils.getResource("/collectors", "universities",
					"ecu", "people_uri.txt");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean collectInfo(List<Person> p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean collectInfo(Person p) {

		try {
			String resPage = IOUtils.getResource("/collectors", "universities",
					"ecu", "example_search_results", "js_search.htm");

			Document doc = Jsoup.parse(resPage);

			Elements tables = doc.select("table");
			
			System.out.println("Found " + tables.size() + " tables");

			if (tables.size() > 0) {
				
				Elements tbodies = tables.get(0).select("tbody");

				System.out.println("Found " + tbodies.size() + " tbodies");
				for (Element e : tbodies) {

					Elements names = e.select("td[itemprop=name]");
					Elements email = e.select("td[itemprop=email]");
					Elements phone = e.select("td[itemprop=telephone]");
					// Elements mailStop = e.select("tr[itemprop=name]");
					// Elements office = e.select("td[itemprop=name]");
					// Elements dept = e.select("td[itemprop=name]");

					if (names.size() > 0) {
						System.out.println("Name:" + names.get(0).text());
					}

					if (email.size() > 0) {
						System.out.println("Email:" + email.get(0).text());
					}

					if (phone.size() > 0) {
						System.out.println("Phone:" + phone.get(0).text());
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static void main(String[] args) {
		// new ECUCollector().collectInfo(new Person());

		Document doc;
		try {

			// need http protocol
			// doc = Jsoup.connect("http://google.com").get();

			String resPage = IOUtils.getResource("/collectors", "universities",
					"ecu", "example_search_results", "js_search.htm");

			doc = Jsoup.parse(resPage);

			// System.out.println(doc.html());

			// get page title
			String title = doc.title();
			System.out.println("title : " + title);

			// get all links
			Elements tables = doc.select("table");

			System.out.println("Found " + tables.size() + " tables");

			// System.out.println(tables.get(0).text());

			if (tables.size() > 0) {
				Elements tbodies = tables.get(0).select("tbody");

				System.out.println("Found " + tbodies.size() + " tbodies");
				for (Element e : tbodies) {

					Elements names = e.select("td[itemprop=name]");
					Elements email = e.select("td[itemprop=email]");
					Elements phone = e.select("td[itemprop=telephone]");
					// Elements mailStop = e.select("tr[itemprop=name]");
					// Elements office = e.select("td[itemprop=name]");
					// Elements dept = e.select("td[itemprop=name]");

					if (names.size() > 0) {
						System.out.println("Name:" + names.get(0).text());
					}

					if (email.size() > 0) {
						System.out.println("Email:" + email.get(0).text());
					}

					if (phone.size() > 0) {
						System.out.println("Phone:" + phone.get(0).text());
					}

				}
			}
			// for (Element match : matches) {
			//
			// // get the value from href attribute
			// System.out.println("Match:");
			// System.out.println(match.html());
			//
			// }

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
