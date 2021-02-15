package com.example.sprotte.entity;

import com.example.sprotte.constants.DatabaseConstants;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name=DatabaseConstants.ROLE_TABLE)
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name=DatabaseConstants.ID_ROLE)
	private Long id;
	
	@Column(name=DatabaseConstants.ROLE_DESCRIPTION)
	private String description;
	
	//Bi-Directional
	@JsonBackReference
	@ManyToMany(mappedBy="roles")
	private List<Employee> employees = new ArrayList<>();
	
	
	public Role() {
		
	}

	public Role(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	
}
