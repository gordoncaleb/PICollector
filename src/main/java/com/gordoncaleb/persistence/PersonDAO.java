package com.gordoncaleb.persistence;

import java.util.List;

import com.gordoncaleb.domain.pi.Person;
import com.gordoncaleb.domain.pi.name.Name;

public interface PersonDAO {

	public void save(Person p);

	public void removePerson(Person p);
	
	public List<Person> findPersonsByOrganization(String jobOrg);

	public List<Person> findPersons(Name firstName, Name lastName, Name middleName, String jobOrg);

	public List<Person> findPersonsByFirstNameAndLastName(Name firstName, Name lastName);

	public List<Person> findPersonsByFirstName(Name firstName);

	public List<Person> findPersonsByLastName(Name lastName);

}
