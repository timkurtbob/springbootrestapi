package com.employee.springbootrestapi.repository;

import com.employee.springbootrestapi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}