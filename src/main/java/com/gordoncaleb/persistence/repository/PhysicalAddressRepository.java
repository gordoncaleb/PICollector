package com.gordoncaleb.persistence.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.gordoncaleb.domain.pi.contact.PhysicalAddress;

public interface PhysicalAddressRepository extends
		MongoRepository<PhysicalAddress, Long> {

	public List<PhysicalAddress> findByGeoCodeExists(boolean exists,
			Pageable pageable);

	@Query("{'geoCode.0': {$exists: false}}")
	public Page<PhysicalAddress> findByEmptyGeoCode(Pageable pageable);
	
	@Query("{'schoolCodes.0': {$exists: false}}")
	public Page<PhysicalAddress> findByEmptySchools(Pageable pageable);
}
