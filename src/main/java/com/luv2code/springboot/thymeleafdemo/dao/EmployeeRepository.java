package com.luv2code.springboot.thymeleafdemo.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;

// Employee represents the type of data we are using and Integer represents the data type of the primary key
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// that's it ... no need to write any code LOL!
	// Sort the list by last name
	public List<Employee> findAllByOrderByLastNameAsc();
}
