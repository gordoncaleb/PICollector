package com.gordoncaleb.persistence;

import java.util.List;

import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.gordoncaleb.domain.pi.Person;
import com.gordoncaleb.domain.pi.name.Name;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

public class PersonDAOImpl implements PersonDAO {

	@Override
	public void save(Person p) {

		if (p.getId() == null) {
			p.setId(MongoDAO.getSequenceNum(Person.class));
		}

		MongoDAO.piDataMongoTemplate.save(p);
	}

	@Override
	public void removePerson(Person p) {
		if (p.getId() != null) {
			MongoDAO.piDataMongoTemplate.remove(p);
		}
	}

	@Override
	public List<Person> findPersons(Name firstName, Name lastName, Name middleName, String jobOrg) {
		Query query = new Query();

		Criteria c = where("firstName.name").is(firstName.getName()).and("lastName.name").is(lastName.getName());

		if (middleName != null) {
			c = c.andOperator(where("middleName.name").is(middleName.getName()).orOperator(where("middleName.initial").is(middleName.getName())));
		}

		if (jobOrg != null) {
			c = c.andOperator(where("jobs").elemMatch(where("organization").is(jobOrg)));
		}

		query.addCriteria(c);

		// System.out.println(query.toString());

		return MongoDAO.piDataMongoTemplate.find(query, Person.class);
	}

	public List<Person> basicQuery(String query) {
		return MongoDAO.piDataMongoTemplate.find(new BasicQuery(query), Person.class);
	}

	public List<Person> hasEmail() {
		return MongoDAO.piDataMongoTemplate.find(query(where("emailAddresses").not().size(0)), Person.class);
	}

	@Override
	public List<Person> findPersonsByOrganization(String jobOrg) {
		return MongoDAO.piDataMongoTemplate.find(query(where("jobs").elemMatch(where("organization").is(jobOrg))), Person.class);
	}

	@Override
	public List<Person> findPersonsByFirstNameAndLastName(Name firstName, Name lastName) {
		return findPersons(firstName, lastName, null, null);
	}

	@Override
	public List<Person> findPersonsByFirstName(Name firstName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("firstName.name").is(firstName.getName()));

		return MongoDAO.piDataMongoTemplate.find(query, Person.class);
	}

	@Override
	public List<Person> findPersonsByLastName(Name lastName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("lastName.name").is(lastName.getName()));

		return MongoDAO.piDataMongoTemplate.find(query, Person.class);
	}

}
