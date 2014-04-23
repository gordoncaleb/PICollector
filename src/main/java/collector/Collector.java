package collector;

import java.util.List;

import pi.Person;

public interface Collector {
	
	List<Person> collectInfo(List<Person> p);

	Person collectInfo(Person p);
}
