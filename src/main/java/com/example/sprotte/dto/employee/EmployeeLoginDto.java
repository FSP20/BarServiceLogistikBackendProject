package com.example.sprotte.dto.employee;

// Data Transfer Object for REST API Interface --> Login Employee
public class EmployeeLoginDto {

	private String username;
	private String password;
	private Long deviceId;
	
	
	public EmployeeLoginDto(String username, String password, Long deviceId) {
		this.username = username;
		this.password = password;
		this.deviceId = deviceId;
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


	public Long getDeviceId() {
		return deviceId;
	}


	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}
}
