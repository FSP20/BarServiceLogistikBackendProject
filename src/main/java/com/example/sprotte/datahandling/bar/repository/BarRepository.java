package com.example.sprotte.datahandling.bar.repository;

import com.example.sprotte.entity.Bar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarRepository extends JpaRepository<Bar, Long> {

    Bar findByName(String name);
}
