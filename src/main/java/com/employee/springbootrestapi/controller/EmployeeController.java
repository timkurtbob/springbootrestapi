package com.employee.springbootrestapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.springbootrestapi.dao.EmployeeDAO;
import com.employee.springbootrestapi.model.Employee;

@RestController
@RequestMapping("/company")
public class EmployeeController {
	
	@Autowired
	EmployeeDAO employeeDAO;
	
	
	/* to save an employee */
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee emp) {
		return employeeDAO.save(emp);
	}
	
	
	/* get all employees */
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeDAO.findAll();
	}
	
	
	/* get employee by empid */
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id") Long empid){
		
		Optional<Employee> emp=employeeDAO.findOne(empid);
		
		if(!emp.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(emp.get());
		
	}
	
	
	/* update an employee by empid */
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id") Long empid,@Valid @RequestBody Employee empDetails){
		
		Optional<Employee> emp=employeeDAO.findOne(empid);
		if(!emp.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		emp.get().setFirstName(empDetails.getFirstName());
		emp.get().setSurname(empDetails.getSurname());
		emp.get().setJobTitle(empDetails.getJobTitle());
		
		Employee updateEmployee=employeeDAO.save(emp.get());
		return ResponseEntity.ok().body(updateEmployee);
	}
	
	
	/* delete an employee*/
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable(value="id") Long empid) {
		
		Optional<Employee> emp=employeeDAO.findOne(empid);
		if(!emp.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		employeeDAO.delete(emp.get());
		
		return ResponseEntity.ok().build();		
	}
}