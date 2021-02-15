package com.example.sprotte.datahandling.employee.services;

import com.example.sprotte.dto.employee.EmployeeLoginDto;
import com.example.sprotte.entity.Employee;

import java.sql.SQLException;

public interface EmployeeLoginService {

	Employee loginEmployee(EmployeeLoginDto employeeLoginDto) throws SQLException;
}
