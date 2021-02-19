package com.example.sprotte.datahandling.product.repository;

import com.example.sprotte.entity.contentratio.ContentRatioProductBarContainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRatioRepository extends JpaRepository<ContentRatioProductBarContainer, Long> {

}
