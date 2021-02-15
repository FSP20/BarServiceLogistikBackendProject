package com.example.sprotte.datahandling.bar.repository;

import com.example.sprotte.entity.ContainerCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContainerCategoryRepository extends JpaRepository<ContainerCategory, Long> {

    ContainerCategory findByDescription(String description);
}
