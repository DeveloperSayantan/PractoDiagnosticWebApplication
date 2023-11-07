package com.practo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practo.entity.TestEntity;
import com.practo.services.TestService;

@RestController
public class TestController {

	@Autowired
	TestService testService;
	
//	@PostMapping("/addTest")
//	public TestEntity addTest(@RequestBody TestEntity testEntity) {
//		
//		return testService.addTest(null, null, null)		;
//	}
	
	@GetMapping("/getTestDetails")
	public List<TestEntity> getTestDetails(){
		
		return testService.fetchAllTestDetails();
	}
	
	@GetMapping("/getTestDetails/{id}")
	public TestEntity getTestDetailsById(@PathVariable("id")int id) {
			
		return testService.getById(id);
	}
	
	@PutMapping("/updateTestDetails/{id}")
	public TestEntity updateTestDetails(@PathVariable("id") int id, @RequestBody TestEntity testEntity) {
		
		return testService.updateTestByID(id, testEntity);
	}
	
	@DeleteMapping("/deleteTest/{id}")
	public String deleteTest(@PathVariable("id")int id) {
		
		return testService.deleteTest(id);
	}
}
