package com.practo.dao.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practo.entity.CustomerEntity;
import com.practo.entity.TestEntity;

@Repository
public interface TestRepo extends JpaRepository<TestEntity, Integer>{
	
	List<TestEntity> findByCustomer(CustomerEntity customer);

}
