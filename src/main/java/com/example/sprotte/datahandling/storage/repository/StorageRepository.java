package com.example.sprotte.datahandling.storage.repository;

import com.example.sprotte.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {

    Storage findByDescription(String description);
}
