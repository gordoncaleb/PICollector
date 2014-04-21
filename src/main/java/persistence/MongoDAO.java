package persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class MongoDAO {

	@Autowired
	MongoOperations mongoOperation;

	public Long getSequenceNum(Class<?> c) {

		// get sequence id
		Query query = new Query(Criteria.where("_id").is(c.getCanonicalName()));

		// increase sequence id by 1
		Update update = new Update();
		update.inc("seq", 1);

		// return new increased id
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);

		// this is the magic happened.
		SequenceId seqId = mongoOperation.findAndModify(query, update, options,
				SequenceId.class);

		return seqId.getSequenceNum();
	}

}
