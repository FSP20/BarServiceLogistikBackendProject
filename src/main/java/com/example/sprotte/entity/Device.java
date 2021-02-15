package com.example.sprotte.entity;

import com.example.sprotte.constants.DatabaseConstants;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name= DatabaseConstants.DEVICE_TABLE)
public class Device {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name=DatabaseConstants.ID_DEVICE)
	private Long id;
	
	@Column(name=DatabaseConstants.DEVICE_NUMBER)
	private int deviceNumber;
	
	@Column(name=DatabaseConstants.DEVICE_DESCRIPTION)
	private String description;
	
	@Column(name=DatabaseConstants.IS_ACTIVE)
	private boolean active;
	

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade= {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
	@JoinColumn(name=DatabaseConstants.ID_EMPLOYEE)
	private Employee employee;


	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
	@JoinColumn(name=DatabaseConstants.ID_BAR)
	private Bar bar;
	
	
	public Device() {
		
	}

	public Device(int deviceNumber, String description, boolean active) {
		this.deviceNumber = deviceNumber;
		this.description = description;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(int deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Bar getBar() {
		return bar;
	}

	public void setBar(Bar bar) {
		this.bar = bar;
	}
}
