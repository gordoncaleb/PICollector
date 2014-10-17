package com.gordoncaleb.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gordoncaleb.schoolsitelocator.School;

public interface SchoolRepository extends MongoRepository<School, Long> {

	public School findOneBySchoolCode(String code);
	
}
