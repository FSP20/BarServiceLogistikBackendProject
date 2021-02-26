package com.example.sprotte.datahandling.product.repository;

import com.example.sprotte.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

    ProductType findByProductType(String productType);

}
