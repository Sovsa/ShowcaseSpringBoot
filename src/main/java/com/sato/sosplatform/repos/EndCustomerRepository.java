package com.sato.sosplatform.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sato.sosplatform.entities.EndCustomer;

public interface EndCustomerRepository extends CrudRepository<EndCustomer, Integer> {
	
	@Query("SELECT u FROM EndCustomer u WHERE u.registered = 0")
	Iterable<EndCustomer> getAllNotRegistered();
	
	@Query("SELECT u FROM EndCustomer u WHERE u.registered = 1")
	Iterable<EndCustomer> getAllRegistered();
}
