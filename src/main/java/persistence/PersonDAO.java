package persistence;

import pi.Person;
import pi.name.Name;

public interface PersonDAO {

	public void addPerson(Person p);

	public void removePerson(Person p);

	public Person findPersonByFirstNameAndLastName(Name firstName, Name lastName);

	public Person findPersonByFirstName(Name firstName);

	public Person findPersonByLastName(Name lastName);

}
