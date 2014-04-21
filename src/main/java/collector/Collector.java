package collector;

import java.util.List;

import pi.Person;

public interface Collector {
	
	boolean collectInfo(List<Person> p);

	boolean collectInfo(Person p);
}
