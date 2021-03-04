package com.example.sprotte.datahandling.storage.repository;

import com.example.sprotte.entity.storageContentRatio.ContentRatioProductStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRatioProductStorageRepository extends JpaRepository<ContentRatioProductStorage, Long> {

    ContentRatioProductStorage findByProductIdAndStorageId(Long productId, Long storageId);

    void deleteByProductIdAndStorageId(Long productId, Long storageId);
}
