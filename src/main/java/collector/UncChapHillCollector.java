package collector;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

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

public class UncChapHillCollector extends Collector {

	String peopleURI;

	public static void main(String[] args) {
		Person p = new Person();
		p.setLastName(new Name("Ralph"));
		p.setFirstName(new Name("Baric"));

		UncChapHillCollector collector = new UncChapHillCollector();

		System.out.println("Before: " + p);
		collector.collectInfo(p);

		System.out.println("After: " + p);

	}

	public UncChapHillCollector() {
		try {
			peopleURI = IOUtils.getResource("/collectors", "universities", "uncchapelhill", "people_uri.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	Person collectInfo(Person p) {

		try {
			// Document doc =
			// Jsoup.connect("https://itsapps.unc.edu/dir/dirSearch/search").data("searchString",
			// "ralph%20baric")
			// .userAgent("Mozilla").post();

			System.out.println("Collecting data on " + p.getFirstName() + " " + p.getLastName());

			Document doc = Jsoup.parse(sendPost(peopleURI, "searchString=" + p.getFirstName().getName() + "%20" + p.getLastName().getName()));

			Map<String, Object> map = StringUtils.jsonToMap(doc.text());

			Object listObj = map.get("list");

			if (listObj instanceof List) {
				List<Map<String, Object>> results = (List<Map<String, Object>>) listObj;

				if (results != null) {
					for (Map<String, Object> jsonPerson : results) {

						Object email = jsonPerson.get("mail");
						Object phone = jsonPerson.get("telephoneNumber");

						if (email != null) {
							System.out.println("Mail:" + email);
							p.addEmailAddress(new EmailAddress(email.toString()));
						}

						if (phone != null) {
							System.out.println("Telephone Number:" + phone);
							p.addPhone(new Phone(phone.toString()));
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

	// HTTP POST request
	private static String sendPost(String url, String postData) throws Exception {

		// String url = "https://itsapps.unc.edu/dir/dirSearch/search";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		// add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		// String urlParameters = "searchString=ralph%20baric";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(postData);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		// System.out.println("\nSending 'POST' request to URL : " + url);
		// System.out.println("Post parameters : " + postData);
		// System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());

		return response.toString();

	}

}
