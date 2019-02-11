package com.sato.sosplatform.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sato.sosplatform.entities.BusinessPartner;

public interface BusinessPartnerRepository extends CrudRepository<BusinessPartner, Integer>{
	
	@Query("SELECT u FROM BusinessPartner u WHERE u.registered = 0")
	Iterable<BusinessPartner> getAllNotRegistered();
	
	@Query("SELECT u FROM BusinessPartner u WHERE u.registered = 1")
	Iterable<BusinessPartner> getAllRegistered();
}
