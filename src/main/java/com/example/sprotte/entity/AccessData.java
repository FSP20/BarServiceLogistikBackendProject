package com.example.sprotte.entity;

import com.example.sprotte.constants.DatabaseConstants;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name= DatabaseConstants.ACCESS_DATA_TABLE)
public class AccessData {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name=DatabaseConstants.ID_ACCESS_DATA)
	private Long id;
	
	@Column(name=DatabaseConstants.TOKEN)
	private String token;
	
	@Column(name=DatabaseConstants.ACCESS_TIME_STAMP)
	private LocalDate accessTimeStamp;
	
	@JsonBackReference
	@OneToOne(mappedBy = DatabaseConstants.ACCESS_DATA, cascade= CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
	private Employee employee;

	public AccessData() {
		this.token = UUID.randomUUID().toString();
		this.accessTimeStamp = LocalDate.now();
	}

	public AccessData(String token, LocalDate accessTimeStamp, Employee employee) {
		this.token = token;
		this.accessTimeStamp = accessTimeStamp;
		this.employee = employee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDate getAccessTimeStamp() {
		return accessTimeStamp;
	}

	public void setAccessTimeStamp(LocalDate accessTimeStamp) {
		this.accessTimeStamp = accessTimeStamp;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void createNewTokenAndTimeStamp() {
		this.token = UUID.randomUUID().toString();
		this.accessTimeStamp = LocalDate.now();
	}
}
