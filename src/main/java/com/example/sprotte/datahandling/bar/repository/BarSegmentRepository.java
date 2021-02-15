package com.example.sprotte.datahandling.bar.repository;

import com.example.sprotte.entity.BarSegment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarSegmentRepository extends JpaRepository<BarSegment, Long> {

    BarSegment findByDescription(String description);
}
