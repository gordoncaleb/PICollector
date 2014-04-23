package collector;

import java.io.BufferedReader;
import java.util.List;

import pi.Person;
import util.IOUtils;
import util.StringUtils;

public class FileCollector {

	public static void main(String[] args) {

		try {
			BufferedReader br = IOUtils.getResourceAsBufferedReader("/NC_Faculty_Salaries_2_18_14.csv");

			for (int i = 0; i < 10; i++) {
				System.out.println(br.readLine());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Person parsePerson(String s) {

		// s.replaceAll("\"?:[^\"\\]|\\.*\"", replacement)
		String[] tokens = s.split(",");

		List<List<String>> names = StringUtils.getMatches("([a-zA-z]) ([a-zA-Z])", tokens[1]);
		
		return null;
	}
}
