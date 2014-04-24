package collector;

import java.io.BufferedReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import persistence.PersonDAOImpl;
import pi.Person;
import pi.job.Job;
import pi.name.Name;
import util.IOUtils;
import util.StringUtils;

public class FileCollector {

	public static void main(String[] args) {

		try {
			BufferedReader br = IOUtils.getResourceAsBufferedReader("/NC_Faculty_Salaries_2_18_14.csv");

			PersonDAOImpl dao = new PersonDAOImpl();

			int i = 0;

			Person p;
			String line;
			while ((line = br.readLine()) != null) {
				try {
					p = parsePerson(line);

					if (dao.findPersons(p.getFirstName(), p.getLastName(), null, null).isEmpty()) {
						dao.save(p);
						System.out.println(p);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Person parsePerson(String s) throws ParseException {

		// name,campus,position,exempt2,employed,hiredate,fte,status,term,rank3,admin,class2,dept,stservyr,statesal,nonstsal,totalsal

		List<List<String>> quotes = StringUtils.getMatches("\"([^\"]*([^\"]*)*)\"", s);

		for (List<String> m : quotes) {
			s = s.replaceAll(m.get(0), m.get(0).replaceAll(",", "")).replaceAll("\"", "");
		}

		// System.out.println(s);

		String[] tokens = s.split(",");

		List<List<String>> names = StringUtils.getMatches("([\\w]+) ([\\w]+) ?([\\w]+)?", tokens[0]);

		if (names.isEmpty()) {
			System.out.println("Problem with =>" + s);
		}

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

		Job job = new Job();
		job.setOrganization(tokens[1]);
		job.setPosition(tokens[2]);
		job.setStartDate(parseHireDate(tokens[5]));
		job.setTitle(tokens[10]);
		job.setDepartment(tokens[12]);
		job.setSalery(Double.parseDouble(tokens[16]));
		p.addJob(job);

		return p;
	}

	private static Date parseHireDate(String s) throws ParseException {
		Date d = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			d = sdf.parse(s);
		} catch (Exception e) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				d = sdf.parse(s);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		return d;
	}
}
