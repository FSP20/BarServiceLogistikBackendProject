package com.example.sprotte.datahandling.employee.services;

import com.example.sprotte.dto.employee.EmployeeRegistrationDto;
import com.example.sprotte.entity.Employee;

public interface EmployeeRegistrationService {

	public Employee saveNewEmployee(EmployeeRegistrationDto employee);
}
