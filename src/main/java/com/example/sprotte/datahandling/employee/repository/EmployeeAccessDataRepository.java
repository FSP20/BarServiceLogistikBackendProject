package com.example.sprotte.datahandling.employee.repository;

import com.example.sprotte.entity.AccessData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeAccessDataRepository extends JpaRepository<AccessData, Long> {

	public Optional<AccessData> findByToken(String token);
}
