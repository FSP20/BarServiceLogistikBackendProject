package com.example.sprotte.datahandling.product.controller;

import com.example.sprotte.datahandling.product.service.ProductTypeService;
import com.example.sprotte.entity.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productType")
public class ProductTypeController {

    @Autowired
    ProductTypeService productTypeService;

    @GetMapping("/getProductTypes")
    List<ProductType> getProductTypes() {
        return productTypeService.getProductTypes();
    }

    @PostMapping("/saveProductType")
    ProductType saveProductType(@RequestBody ProductType productType) {
        return productTypeService.saveProductType(productType);
    }

    @GetMapping("/findProductTypeById/{id}")
    ProductType findProductTypeById(@PathVariable("id") Long productTypeId) {
        return productTypeService.findProductTypeById(productTypeId);
    }

    @PutMapping("/updateProductType")
    ProductType updateProductType(@RequestBody ProductType productType) {
        return productTypeService.updateProductType(productType);
    }

    @DeleteMapping("/deleteProductTypeById/{id}")
    String deleteProductTypeById(@PathVariable("id") Long productTypeId) {
        return productTypeService.deleteProductTypeById(productTypeId);
    }
}
