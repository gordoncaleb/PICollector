package persistence;

import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.MongoClient;

public class MongoDAO {

	public static MongoTemplate piDataMongoTemplate;

	static {
		try {
			piDataMongoTemplate = new MongoTemplate(new MongoClient("127.0.0.1"), "pidata");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Long getSequenceNum(Class<?> c) {

		// get sequence id
		Query query = new Query(Criteria.where("id").is(c.getCanonicalName()));

		// increase sequence id by 1
		Update update = new Update();
		update.inc("sequenceNum", 1);

		// return new increased id
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);

		// this is the magic happened.
		SequenceId seqId = piDataMongoTemplate.findAndModify(query, update, options, SequenceId.class);

		if (seqId == null) {
			seqId = new SequenceId(c.getCanonicalName(), 0L);
			piDataMongoTemplate.save(seqId);
		}

		return seqId.getSequenceNum();
	}

}
