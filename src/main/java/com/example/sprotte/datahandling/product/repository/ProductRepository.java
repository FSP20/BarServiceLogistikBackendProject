package com.example.sprotte.datahandling.product.repository;

import com.example.sprotte.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByDescription(String description);
}
