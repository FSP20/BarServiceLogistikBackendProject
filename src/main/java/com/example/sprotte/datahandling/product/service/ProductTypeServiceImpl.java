package com.example.sprotte.datahandling.product.service;

import com.example.sprotte.constants.ResponseMessageConstants;
import com.example.sprotte.datahandling.product.repository.ProductTypeRepository;
import com.example.sprotte.entity.ProductType;
import com.example.sprotte.errorhandling.product.ProductTypeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    ProductTypeRepository productTypeRepository;

    @Override
    public List<ProductType> getProductTypes() {

        List<ProductType> productTypes = productTypeRepository.findAll();
        if(productTypes == null || productTypes.size() == 0 )
            throw new ProductTypeNotFoundException(ResponseMessageConstants.PRODUCT_TYPE_NOT_FOUND);

        return productTypes;

    }

    @Override
    public ProductType saveProductType(ProductType productType) {

        return productTypeRepository.save(productType);
    }

    @Override
    public ProductType findProductTypeById(Long productTypeId) {

        ProductType productType =  validateProductTypeExists(productTypeId);
        return productType;
    }

    @Override
    public ProductType updateProductType(ProductType productType) {

        validateProductTypeExists(productType.getId());
        return productTypeRepository.save(productType);

    }

    @Override
    public String deleteProductTypeById(Long productTypeId) {

        validateProductTypeExists(productTypeId);
        productTypeRepository.deleteById(productTypeId);
        return ResponseMessageConstants.DELETION_SUCCESS + ("(ID" +  productTypeId + ")");
    }

    private ProductType validateProductTypeExists(Long productTypeId){

        ProductType productType =findById(productTypeId);

        if(productType == null) {
            throw new ProductTypeNotFoundException(ResponseMessageConstants.PRODUCT_TYPE_NOT_FOUND
                    + "(" + productTypeId  + ")");
        }

        return productType;
    }

    public ProductType findById(Long productTypeId) {
        return productTypeRepository.findById(productTypeId).orElse(null);
    }

}
