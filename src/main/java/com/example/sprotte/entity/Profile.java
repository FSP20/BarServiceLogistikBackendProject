package com.example.sprotte.entity;

import com.example.sprotte.constants.DatabaseConstants;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name=DatabaseConstants.PROFILE_TABLE)
public class Profile {

	
	/*@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name=DatabaseConstants.ID_PROFILE)
	private Long id;
	
	@Column(name=DatabaseConstants.PASSWORD_EMPLOYEE)
	private String password;
	
	@Column(name=DatabaseConstants.USERNAME_EMPLOYEE)
	private String username;
	
	@JsonBackReference
	@OneToOne(fetch = FetchType.EAGER, cascade= CascadeType.ALL, mappedBy = "profile")
	private Employee employee;
	

	public Profile() {

	}

	public Profile(String password, String username, Employee employee) {
		this.password = password;
		this.username = username;
		this.employee = employee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
}
