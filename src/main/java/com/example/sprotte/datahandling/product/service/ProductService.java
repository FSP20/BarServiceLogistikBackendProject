package com.example.sprotte.datahandling.product.service;

import com.example.sprotte.dto.product.SaveNewProductDto;
import com.example.sprotte.dto.product.UpdateProductDto;
import com.example.sprotte.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();

    Product saveProduct(SaveNewProductDto dto);

    Product findProductById(Long productId);

    Product findProductByDescription(String productDescription);

    Product updateProduct(UpdateProductDto dto);

    Product updateProductDescription(Long productId, String productDescription);

    String deleteProductById(Long productId);
}
