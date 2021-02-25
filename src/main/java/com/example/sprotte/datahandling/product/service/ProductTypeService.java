package com.example.sprotte.datahandling.product.service;

import com.example.sprotte.entity.ProductType;

import java.util.List;

public interface ProductTypeService {

    List<ProductType> getProductTypes();

    ProductType saveProductType(ProductType productType);

    ProductType findProductTypeById(Long productTypeId);

    ProductType updateProductType(ProductType productType);

    String deleteProductTypeById(Long productTypeId);

}
