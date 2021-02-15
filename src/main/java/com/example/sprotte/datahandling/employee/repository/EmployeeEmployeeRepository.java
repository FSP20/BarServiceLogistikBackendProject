package com.example.sprotte.datahandling.employee.repository;

import com.example.sprotte.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeEmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByProfileId(long profileId);

	Optional<Employee> findByFirstNameAndLastName(String firstName, String lastName);
}
