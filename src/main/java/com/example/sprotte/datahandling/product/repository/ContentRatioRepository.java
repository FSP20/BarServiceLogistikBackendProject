package com.example.sprotte.datahandling.product.repository;

import com.example.sprotte.entity.barcontainerContentratio.ContentRatioProductBarContainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRatioRepository extends JpaRepository<ContentRatioProductBarContainer, Long> {

    ContentRatioProductBarContainer findByProductIdAndBarContainerId(Long productId, Long barContainerId);

    void deleteByProductIdAndBarContainerId(Long productId, Long barContainerId);
}
