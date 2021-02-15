package com.example.sprotte.datahandling.employee.controller;

import com.example.sprotte.dto.employee.EmployeeChangePasswordDto;
import com.example.sprotte.dto.employee.UpdateEmployeeDto;
import com.example.sprotte.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.sprotte.datahandling.employee.services.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/get/{employeeId}")
	public Employee getEmployeeById(@PathVariable("employeeId") Long employeeId) {
		return employeeService.getEmployeeById(employeeId);
	}

	@GetMapping("/get/{username}")
	public Employee getEmployeeByUsername(@PathVariable("username") String username) {
		return employeeService.getEmployeeByUsername(username);
	}

	@GetMapping("/get/{firstname}/{lastname}")
	public Employee getEmployeeByFullName(@PathVariable("firstname") String firstName,
										  @PathVariable("lastname") String lastName){
		return employeeService.getEmployeeByFullName(firstName, lastName);
	}

	@DeleteMapping("/delete/{employeeId}")
	public void deleteEmployee(@PathVariable("employeeId") Long employeeId) {
		employeeService.deleteEmployeeById(employeeId);
	}

	@PostMapping("/updateemployee")
	public Employee updateEmployee(@RequestBody UpdateEmployeeDto dto) {
		return employeeService.updateEmployee(dto);
	}

	@PostMapping("/changepassword")
	public String changePasswordById(@RequestBody EmployeeChangePasswordDto dto) {
		return employeeService.changePasswordById(dto);
	}

	@PostMapping("/changeusername/{id}/{username}")
	public String changeUsernameById(@PathVariable("id") Long employeeId, @PathVariable("username") String newUsername) {
		return employeeService.changeUsernameById(employeeId, newUsername);
	}

	@GetMapping("/getemployees")
	public List<Employee> getEmployees() {
		return employeeService.getEmployees();
	}

	@PostMapping("/setactive/{employeeId}/{deviceId}")
	public void setEmployeeActiveById(@PathVariable("employeeId") Long employeeId, @PathVariable("deviceId") Long deviceId) {
		employeeService.setEmployeeActiveById(employeeId, deviceId);
	}

	@PostMapping("/setinactive/{employeeId}/{deviceId}")
	public void setEmployeeInactiveById(@PathVariable("employeeId") Long employeeId, @PathVariable("deviceId") Long deviceId) {
		employeeService.setEmployeeInactiveById(employeeId, deviceId);
	}

	@PostMapping("/setroleemployee/{employeeId}/{rolename}")
	public Employee addRoleToEmployee(@PathVariable("employeeId") Long employeeId, @PathVariable("rolename") String roleName) {
		return employeeService.addRoleToEmployee(employeeId, roleName);
	}

	@DeleteMapping("/deleteroleemployee/{employeeId}/{rolename}")
	public Employee deleteRoleFromEmployee(@PathVariable("employeeId") Long employeeId, @PathVariable("rolename") String roleName) {
		return employeeService.deleteRoleFromEmployee(employeeId, roleName);
	}

	@PostMapping("/setmaxnumbersoforders/{employeeId}/{maxnumbersoforders}")
	public String updateMaxNumbersOfOrders(@PathVariable("employeeId") Long employeeId,
										   @PathVariable("maxnumbersoforders") int maxNumbersOfOrders) {
		return employeeService.updateMaxNumbersOfOrders(employeeId, maxNumbersOfOrders);
	}
}
