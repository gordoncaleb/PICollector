package collector;

import java.util.List;
import java.util.concurrent.TimeUnit;

import persistence.PersonDAOImpl;
import pi.Person;
import pi.job.Organizations;

public abstract class Collector {

	public List<Person> collectInfo(List<Person> people) {
		for (Person p : people) {
			collectInfo(p);
		}
		return people;
	}

	abstract Person collectInfo(Person p);

	public static void main(String[] args) {
		PersonDAOImpl dao = new PersonDAOImpl();

		// collectOrganization(Organizations.EAST_CAROLINA, dao);
		// collectOrganization(Organizations.NC_STATE, dao);
		collectOrganization(Organizations.UNC_CHAPEL_HILL, dao);

		List<Person> people = dao.hasEmail();

		// for (Person p : people) {
		// System.out.println(p);
		// }

		System.out.println("There are " + people.size() + " people with email addresses in the database!");
	}

	public static void collectOrganization(String org, PersonDAOImpl dao) {
		List<Person> people = dao.findPersonsByOrganization(org);

		System.out.println("Searching for " + people.size());

		Collector collector = Organizations.getCollector(org);

		boolean test = false;
		int i = 0;
		for (Person p : people) {

			if (i > 5 && test) {
				break;
			}

			if (p.getUpdated() == null || ((System.currentTimeMillis() - p.getUpdated().getTime()) > TimeUnit.HOURS.toMillis(1)) || test) {
				collector.collectInfo(p);
				dao.save(p);
			}
			i++;

		}
	}
}
