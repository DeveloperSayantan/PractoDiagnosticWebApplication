package com.practo.services;

import java.util.List;
import java.util.Optional;

import com.practo.entity.CustomerEntity;

public interface CustomerService {

	CustomerEntity addCustomer(CustomerEntity customerEntity); //Add Customers
	
	boolean isEmailExists(String email); //Checking email already exists or not

	boolean isPhoneExists(Long mobile);	//Checking phone already exists or not
	
	List <CustomerEntity> fetchAllCustomer(); //Fetch All Customers Details
	
	CustomerEntity getById(int id); //Get Customer Details by Id.
	
	String deleteCustomer(int id); //Delete Customer By id
	
	CustomerEntity updateCustomer(int id, CustomerEntity customerEntity); //Update Customer By id

	CustomerEntity loginByEmail(String email);
	

}
