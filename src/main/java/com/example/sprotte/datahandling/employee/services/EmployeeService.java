package com.example.sprotte.datahandling.employee.services;

import com.example.sprotte.dto.employee.EmployeeChangePasswordDto;
import com.example.sprotte.dto.employee.UpdateEmployeeDto;
import com.example.sprotte.entity.Employee;

import java.util.List;

public interface EmployeeService {

	Employee getEmployeeById(Long employeeId);

	Employee getEmployeeByUsername(String username);

	Employee getEmployeeByFullName(String firstName, String lastName);

	String deleteEmployeeById(Long employeeId);

	Employee updateEmployee(UpdateEmployeeDto dto);

	String changePasswordById(EmployeeChangePasswordDto dto);

	String changeUsernameById(Long employeeId, String newUsername);

	List<Employee> getEmployees();

	void setEmployeeActiveById(Long employeeId, Long deviceId);

	void setEmployeeInactiveById(Long employeeId, Long deviceId);

	Employee addRoleToEmployee(Long employeeId, String roleName);

	Employee deleteRoleFromEmployee(Long employeeId, String roleName);

	String updateMaxNumbersOfOrders(Long employeeId, int maxNumbersOfOrders);
}
