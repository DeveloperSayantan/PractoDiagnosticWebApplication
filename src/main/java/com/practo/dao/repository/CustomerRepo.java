package com.practo.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practo.entity.CustomerEntity;

@Repository
public interface CustomerRepo extends JpaRepository <CustomerEntity, Integer> {
	
	 CustomerEntity findByEmail(String email);
	 
	 boolean existsByEmail(String email);
		
	boolean existsByMobile(Long mobile);

}
