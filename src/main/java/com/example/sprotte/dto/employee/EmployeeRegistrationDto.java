package com.example.sprotte.dto.employee;

import java.util.List;

public class EmployeeRegistrationDto {
	
	private String firstName;
	private String lastName;
	private int maxNumbersOfOrders;
	
	private String username;
	private String password;
	
	private long deviceId;
	
	private List<Long> roleIds;
	
	

	public EmployeeRegistrationDto() {
		
	}


	public EmployeeRegistrationDto(String firstName, String lastName, int maxNumbersOfOrders, String username,
			String password, int deviceId, List<Long> roleIds) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.maxNumbersOfOrders = maxNumbersOfOrders;
		this.username = username;
		this.password = password;
		this.deviceId = deviceId;
		this.roleIds = roleIds;
	}




	public String getFirstName() {
		return firstName;
	}




	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}




	public String getLastName() {
		return lastName;
	}




	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




	public int getMaxNumbersOfOrders() {
		return maxNumbersOfOrders;
	}




	public void setMaxNumbersOfOrders(int maxNumbersOfOrders) {
		this.maxNumbersOfOrders = maxNumbersOfOrders;
	}




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public long getDeviceId() {
		return deviceId;
	}




	public void setDeviceId(long deviceId) {
		this.deviceId = deviceId;
	}




	public List<Long> getRoleIds() {
		return roleIds;
	}




	public void setRoleIds(List<Long> roleId) {
		this.roleIds = roleId;
	}

	
}
