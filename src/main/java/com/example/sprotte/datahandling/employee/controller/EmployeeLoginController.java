package com.example.sprotte.datahandling.employee.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sprotte.dto.employee.EmployeeLoginDto;
import com.example.sprotte.entity.Employee;
import com.example.sprotte.datahandling.employee.services.EmployeeLoginService;

@RestController
@RequestMapping("/login")
public class EmployeeLoginController {

	@Autowired
	EmployeeLoginService employeeLoginService;
	
	@PutMapping("/employee")
	public Employee loginEmployee(@RequestBody EmployeeLoginDto employeeLoginDto) throws SQLException {
		return employeeLoginService.loginEmployee(employeeLoginDto);
	}
}
