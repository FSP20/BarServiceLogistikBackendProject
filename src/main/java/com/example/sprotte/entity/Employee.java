package com.example.sprotte.entity;

import com.example.sprotte.constants.DatabaseConstants;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= DatabaseConstants.EMPLOYEE_TABLE)
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name=DatabaseConstants.ID_EMPLOYEE)
	private Long id;
	
	@Column(name=DatabaseConstants.FIRST_NAME)
	private String firstName;
	
	@Column(name=DatabaseConstants.LAST_NAME)
	private String lastName;
	
	@Column(name=DatabaseConstants.MAX_NUMBERS_OF_ORDERS)
	private int maxNumbersOfOrders;
	
	@JsonManagedReference
	@OneToOne(fetch= FetchType.EAGER, cascade= CascadeType.ALL, optional = false)
	@JoinColumn(name=DatabaseConstants.ID_PROFILE, nullable = false)
	private Profile profile;
	
	@JsonManagedReference
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name=DatabaseConstants.ID_ACCESS_DATA, nullable = false)
	private AccessData accessData;
	

	@JsonManagedReference
	@OneToMany
	@JoinColumn(name = DatabaseConstants.ID_EMPLOYEE, insertable = false, updatable = true)
	@Fetch(value = FetchMode.JOIN)
	private List<Device> devices = new ArrayList<>();
	

	//@OneToMany(mappedBy = "employee", cascade= {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
	//private List<Device> devices;
	@JsonManagedReference
	@ManyToMany(fetch=FetchType.LAZY, cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name=DatabaseConstants.EMPLOYEE_HAS_ROLE,
				joinColumns=@JoinColumn(name=DatabaseConstants.ID_EMPLOYEE, insertable = false, updatable = true),
				inverseJoinColumns=@JoinColumn(name=DatabaseConstants.ID_ROLE, insertable = false, updatable = true))
	private List<Role> roles = new ArrayList<Role>();


	@JsonManagedReference
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name=DatabaseConstants.EMPLOYEE_HAS_WORK_SHIFT,
				joinColumns = @JoinColumn(name = DatabaseConstants.ID_EMPLOYEE, insertable = false, updatable = true),
				inverseJoinColumns = @JoinColumn(name = DatabaseConstants.ID_WORK_SHIFT, insertable = false, updatable = true))
	private List<WorkShift> workShifts = new ArrayList<>();
	
	
	public Employee() {
		
	}
	

	public Employee(String firstName, String lastName, int maxNumbersOfOrders) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.maxNumbersOfOrders = maxNumbersOfOrders;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public AccessData getAccessData() {
		return accessData;
	}


	public void setAccessData(AccessData accessData) {
		this.accessData = accessData;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public List<WorkShift> getWorkShifts() {
		return workShifts;
	}

	public void setWorkShifts(List<WorkShift> workShifts) {
		this.workShifts = workShifts;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
