package collector;

import java.io.BufferedReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import pi.Person;
import pi.job.Job;
import pi.name.Name;
import util.IOUtils;
import util.StringUtils;

public class FileCollector {

	public static void main(String[] args) {

		try {
			BufferedReader br = IOUtils.getResourceAsBufferedReader("/NC_Faculty_Salaries_2_18_14.csv");

			String line;
			for (int i = 0; i < 3; i++) {
				line = br.readLine();
				System.out.println(parsePerson(line));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Person parsePerson(String s) {

		// name,campus,position,exempt2,employed,hiredate,fte,status,term,rank3,admin,class2,dept,stservyr,statesal,nonstsal,totalsal

		List<List<String>> quotes = StringUtils.getMatches("\"([^\"]*([^\"]*)*)\"", s);

		for (List<String> m : quotes) {
			s = s.replaceAll(m.get(0), m.get(0).replaceAll(",", ""));
		}

		// System.out.println(s);

		String[] tokens = s.split(",");

		List<List<String>> names = StringUtils.getMatches("([\\w]+) ([\\w]+) ([\\w]+)", tokens[0]);

		Person p = new Person();

		if (names.size() > 0 && names.get(0).size() > 1) {
			p.setLastName(new Name(names.get(0).get(1)));
		}

		if (names.size() > 0 && names.get(0).size() > 2) {
			p.setFirstName(new Name(names.get(0).get(2)));
		}

		if (names.size() > 0 && names.get(0).size() > 3) {
			p.setMiddleName(new Name(names.get(0).get(3)));
		}

		try {
			Job job = new Job();
			job.setOrganization(tokens[1]);
			job.setPosition(tokens[2]);
			job.setStartDate(parseHireDate(tokens[5]));
			job.setTitle(tokens[10]);
			job.setDepartment(tokens[12]);
			
			p.addJob(job);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return p;
	}

	private static Date parseHireDate(String s) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		return sdf.parse(s);
	}
}
