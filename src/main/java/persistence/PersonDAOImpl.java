package persistence;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import pi.Person;
import pi.name.Name;

public class PersonDAOImpl implements PersonDAO {

	@Override
	public void addPerson(Person p) {
		p.setId(MongoDAO.getSequenceNum(Person.class));
		MongoDAO.mongoTemplate.save(p);
	}

	@Override
	public void removePerson(Person p) {
		if (p.getId() != null) {
			MongoDAO.mongoTemplate.remove(p);
		}
	}

	@Override
	public Person findPersonByFirstNameAndLastName(Name firstName, Name lastName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("firstName.name").is(firstName.getName()).and("lastName.name").is(lastName.getName()));

		return MongoDAO.mongoTemplate.findOne(query, Person.class);
	}

	@Override
	public Person findPersonByFirstName(Name firstName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("firstName.name").is(firstName.getName()));

		return MongoDAO.mongoTemplate.findOne(query, Person.class);
	}

	@Override
	public Person findPersonByLastName(Name lastName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("lastName.name").is(lastName.getName()));

		return MongoDAO.mongoTemplate.findOne(query, Person.class);
	}

}
