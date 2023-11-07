package com.practo.serviceImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practo.dao.repository.CustomerRepo;
import com.practo.entity.CustomerEntity;
import com.practo.services.CustomerService;


@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerRepo customerRepo;
	
	@Override 		//Create the new customer.
	public CustomerEntity addCustomer(CustomerEntity customerEntity) {
		
		return customerRepo.save(customerEntity);
	}

	@Override 		//Fetch all Customer details.
	public List<CustomerEntity> fetchAllCustomer() {
		
		return customerRepo.findAll();
	}

	@Override 		//Delete customer by Id.
	public String deleteCustomer(int id) {
		
		if(customerRepo.findById(id).isPresent()) {
			
			customerRepo.deleteById(id);
			
			return "Successfully remove the customer";
		}
		return "Sorry no such customer found";
	}

	@Override		//Update customer by Id.
	public CustomerEntity updateCustomer(int id, CustomerEntity customerEntity) {
		
		Optional<CustomerEntity> customer = customerRepo.findById(id);
		
		if(customer.isEmpty()) {
			
			CustomerEntity originalCustomer = customer.get();
			
			 if (Objects.nonNull(customerEntity.getName()) && !"".equalsIgnoreCase(customerEntity.getName())) {
				 originalCustomer.setName(customerEntity.getName());
	            }
			 
			 if (Objects.nonNull(customerEntity.getEmail()) && !"".equalsIgnoreCase(customerEntity.getEmail())) {
				 originalCustomer.setEmail(customerEntity.getEmail());
	            }
			 
			 if(Objects.nonNull(customerEntity.getMobile())) {
				 originalCustomer.setMobile(customerEntity.getMobile());
			 }
			 
			 if(Objects.nonNull(customerEntity.getCity()) && !"".equalsIgnoreCase(customerEntity.getCity())) {
				 originalCustomer.setCity(customerEntity.getCity());
			 }
			 
			 if (Objects.nonNull(customerEntity.getPassword()) && !"".equalsIgnoreCase(customerEntity.getPassword())) {
				 originalCustomer.setPassword(customerEntity.getPassword());
	            }
			
	            return customerRepo.save(originalCustomer);
			
		}
		
		return null;
	}

	@Override		//Get customer details by Id.
	public CustomerEntity getById(int id) {
		
		Optional<CustomerEntity> customerSearch = customerRepo.findById(id);
		
		if(customerSearch.isPresent()) {
			
			return customerSearch.get();
		}
		
		return null;
	}

	@Override	//login
	public CustomerEntity loginByEmail(String email) {
		
		
		return customerRepo.findByEmail(email);
	}


	

}
