package com.practo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.practo.entity.CustomerEntity;
import com.practo.services.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@PostMapping("/addCustomer")
	public CustomerEntity addCustomer(CustomerEntity customerEntity) {
		
		return customerService.addCustomer(customerEntity);
	}
	
	@GetMapping("/getAllCustomer")
	public List<CustomerEntity> fetchAllCustomers() {
		
		return customerService.fetchAllCustomer();
	}
	
	@GetMapping("/getCustomer/{id}")
	public CustomerEntity getCustomerById(@PathVariable("id") int id) {
		
		return customerService.getById(id);
	}
	
	
	@DeleteMapping("/deleteCustomer/{id}")
	public String deleteCustomer(@PathVariable("id") int id) {
		
		return customerService.deleteCustomer(id);
	}
	
	@PutMapping("/updateCustomer/{id}")
	public CustomerEntity updateCustomerEntity(@PathVariable("id") int id, @RequestBody CustomerEntity customerEntity) {
		
		return customerService.updateCustomer(id, customerEntity);
	}
	
	//Login by email id and password
//		@PostMapping("/loginCustomer")
//		public String login(CustomerEntity inputCsmailpass) {
//			System.out.println(inputCsmailpass.getEmail()+" "+inputCsmailpass.getPassword());
//			if( customerService.login(inputCsmailpass))
//			{
//				
//				return "redirect:/dashboard";
//			}else {
//				return "login";
//			}
//		}
	
//	//Login by email id and password
//		@PostMapping("/login")
//		public ResponseEntity<Object> login(@RequestBody Customers inputCsmailpass) {
//			Optional<Customers> customer = customerService.login(inputCsmailpass);
//			
//			 if (customer.isPresent()) {
//			        // Return the customerId in the response
//			        return ResponseEntity.ok(Collections.singletonMap("customerId", customer.get().getId()));
//			    } else {
//			        return ResponseEntity.badRequest().body("Invalid email or password");
//			    }
//			
//			
//			
//	/*		if( customerService.login(inputCsmailpass))
//			{
//				return ResponseEntity.ok("Login successful");
//			}else {
//				return ResponseEntity.badRequest().body("Invalid email or password");
//			}
}
