package com.example.sprotte.datahandling.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sprotte.dto.employee.EmployeeRegistrationDto;
import com.example.sprotte.entity.Employee;
import com.example.sprotte.datahandling.employee.services.EmployeeRegistrationService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/registration")
public class EmployeeRegistrationController {

	@Autowired
	EmployeeRegistrationService userRegistrationService;

	
	@PostMapping("/newemployee")
	@ApiOperation(value = "Insert new Employee in Database",
				notes = "Employee is a like a user, with username etc.",
				response = String.class)
	public Employee setNewEmployee(@RequestBody EmployeeRegistrationDto employeeRegistrationDto) {
		return userRegistrationService.saveNewEmployee(employeeRegistrationDto);
	}
}
