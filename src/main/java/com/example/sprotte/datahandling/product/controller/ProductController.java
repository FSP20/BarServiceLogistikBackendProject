package com.example.sprotte.datahandling.product.controller;

import com.example.sprotte.datahandling.product.service.ProductService;
import com.example.sprotte.dto.product.SaveNewProductDto;
import com.example.sprotte.dto.product.UpdateProductDto;
import com.example.sprotte.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/getProducts")
    List<Product> getProducts() {
        return productService.getProducts();
    }

    @PostMapping("/saveProduct")
    Product saveProduct(@RequestBody SaveNewProductDto dto) {
        return productService.saveProduct(dto);
    }

    @GetMapping("/findProductById/{id}")
    Product findProductById(@PathVariable("id") Long productId) {
        return productService.findProductById(productId);
    }

    @GetMapping("/findProductByDescription/{description}")
    Product findProductByDescription(@PathVariable("description") String productDescription) {
        return productService.findProductByDescription(productDescription);
    }

    @PostMapping("/updateProduct")
    Product updateProduct(@RequestBody UpdateProductDto dto) {
        return productService.updateProduct(dto);
    }

    @PostMapping("/updateProductDescription/{id}/{description}")
    Product updateProductDescription(@PathVariable("id") Long productId,
                                     @PathVariable("description") String productDescription) {
        return productService.updateProductDescription(productId, productDescription);
    }

    @PutMapping("/updateProductType/{productId}/{productTypeId}")
    Product updateProductDescription(@PathVariable("productId") Long productId,
                                     @PathVariable("productTypeId") Long productTypeId) {
        return productService.updateProductType(productId, productTypeId);
    }

    @DeleteMapping("/deleteProductById/{id}")
    String deleteProductById(@PathVariable("id") Long productId) {
        return productService.deleteProductById(productId);
    }
}
