package com.example.sprotte.datahandling.bar.repository;

import com.example.sprotte.entity.BarContainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarContainerRepository extends JpaRepository<BarContainer, Long> {

    BarContainer findByDescription(String description);
}
