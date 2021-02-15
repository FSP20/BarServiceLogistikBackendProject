package com.example.sprotte.datahandling.employee.repository;

import com.example.sprotte.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeProfileRepository extends JpaRepository<Profile, Long>{

	Optional<Profile> findByUsername(String username);
}
