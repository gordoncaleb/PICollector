package collector;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import persistence.PersonDAOImpl;
import pi.Person;
import pi.job.Organizations;

public abstract class Collector {

	abstract List<Person> collectInfo(List<Person> p);

	abstract Person collectInfo(Person p);

	public static void main(String[] args) {
		PersonDAOImpl dao = new PersonDAOImpl();

		List<Person> ecuPeople = dao.findPersonsByOrganization(Organizations.EAST_CAROLINA);

		System.out.println("Searching for " + ecuPeople.size());

		Collector collector = Organizations.getCollector(Organizations.EAST_CAROLINA);

		for (Person p : ecuPeople) {

			if (p.getUpdated() == null || ((System.currentTimeMillis() - p.getUpdated().getTime()) > TimeUnit.HOURS.toMillis(1))) {
				collector.collectInfo(p);
				dao.save(p);
			}

		}

	}
}
