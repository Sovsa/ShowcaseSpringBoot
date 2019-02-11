package com.sato.sosplatform.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sato.sosplatform.entities.GMC;

public interface GMCRepository extends CrudRepository<GMC, Integer> {
	
	@Query("SELECT u FROM GMC u WHERE u.gmcName = ?1")
	GMC findByGmcName(String name);
}
