package com.gordoncaleb.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gordoncaleb.domain.pi.contact.PhysicalAddress;
import com.gordoncaleb.schoolsitelocator.School;

public interface SchoolRepository extends MongoRepository<PhysicalAddress, Long> {

	public School findBySchoolCode(String code);
	
}
