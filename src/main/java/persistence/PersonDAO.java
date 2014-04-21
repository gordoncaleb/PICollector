package persistence;

import java.util.List;

import pi.Person;
import pi.name.Name;

public interface PersonDAO {

	public void addPerson(Person p);

	public void removePerson(Person p);

	public void findPersonByName(List<Name> names);

}
