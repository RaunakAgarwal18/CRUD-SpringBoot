package com.luv2code.springboot.thymeleafdemo.service;

import java.util.List;

import com.luv2code.springboot.thymeleafdemo.entity.Customer;

public interface CustomerService {

	List<Customer> findAll();
	
	Customer findById(int theId);
	
	void save(Customer theEmployee);
	
	void deleteById(int theId);
	
}
