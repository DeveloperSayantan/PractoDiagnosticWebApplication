package com.practo.services;

import java.sql.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.practo.entity.CustomerEntity;
import com.practo.entity.TestEntity;

public interface TestService {


	void addTest(String name, Date date, MultipartFile file, CustomerEntity customerEntity);//Add test
	
	List<TestEntity> fetchAllTestDetails();	//Fetch all test details
	
	TestEntity getById(int id); //Fetch test details by id.
	
	TestEntity updateTestByID(int id, TestEntity testEntity); //Update Test details by id.
	
	String deleteTest(int id); //Delete test.


	
}
