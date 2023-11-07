package com.practo.serviceImpl;

import java.sql.Date;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.practo.dao.repository.TestRepo;
import com.practo.entity.CustomerEntity;
import com.practo.entity.TestEntity;
import com.practo.services.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	TestRepo testRepo;
	
	
	@Override
	public void addTest(String name, Date date, MultipartFile file, CustomerEntity customerEntity) {

		TestEntity bookTest = new TestEntity();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		if(fileName != null) {
			try {
				
				bookTest.setFile(Base64.getEncoder().encodeToString(file.getBytes()));
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			bookTest.setTest_name(name);
			bookTest.setTest_date(date);
			bookTest.setCustomer(customerEntity);
		}
		testRepo.save(bookTest);
		
	}

	@Override
	public List<TestEntity> fetchAllTestDetails() {
		
		return testRepo.findAll();
	}

	@Override
	public TestEntity getById(int id) {
		
		Optional<TestEntity> actualTest = testRepo.findById(id);		
		
		if(actualTest.isPresent()) {
			
			return actualTest.get();
		}

		return null;
	}

	@Override
	public TestEntity updateTestByID(int id, TestEntity testEntity) {
		
		Optional<TestEntity> actualTest = testRepo.findById(id);
		
		if(actualTest.isPresent()) {
			
			TestEntity originalTest = actualTest.get();
			
			 if (Objects.nonNull(testEntity.getTest_name()) && !"".equalsIgnoreCase(testEntity.getTest_name())) {
				 originalTest.setTest_name(testEntity.getTest_name());
	            }
			 if (Objects.nonNull(testEntity.getTest_date())) {
				 originalTest.setTest_date(testEntity.getTest_date());
	            }
			 return testRepo.save(originalTest);
		}
		
		return null;
	}

	@Override
	public String deleteTest(int id) {
		
		Optional<TestEntity> actualTest = testRepo.findById(id);
		
		if(actualTest.isPresent()) {
			
			testRepo.deleteById(id);
			
			return "Test successfully deleted";
		}
		return "No such test found";
	}



}
