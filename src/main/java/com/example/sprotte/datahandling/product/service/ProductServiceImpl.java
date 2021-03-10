package com.example.sprotte.datahandling.product.service;

import com.example.sprotte.constants.ResponseMessageConstants;
import com.example.sprotte.datahandling.product.repository.ProductRepository;
import com.example.sprotte.dto.product.SaveNewProductDto;
import com.example.sprotte.dto.product.UpdateProductDto;
import com.example.sprotte.entity.Product;
import com.example.sprotte.entity.ProductType;
import com.example.sprotte.errorhandling.product.IllegalProductException;
import com.example.sprotte.errorhandling.product.ProductNotFoundException;
import com.example.sprotte.errorhandling.product.ProductTypeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductTypeService productTypeService;

    @Override
    public List<Product> getProducts() {
        List<Product> products = productRepository.findAll();
        if(products.size() == 0)
            throw new ProductNotFoundException(ResponseMessageConstants.PRODUCT_NOT_FOUND);

        return products;
    }

    @Override
    public Product saveProduct(SaveNewProductDto dto) {
        if (dto.getProductDescription() == null)
            throw new RuntimeException(ResponseMessageConstants.PRODUCT_IS_EMPTY);

        // Proof Product Description already exist
        Product product = productRepository.findByDescription(dto.getProductDescription());
        if (product != null)
            throw new IllegalProductException(ResponseMessageConstants.PRODUCT_ALREADY_EXIST);

        return productRepository.save(mapNewProductDtoToProduct(dto));
    }

    @Override
    public Product findProductById(Long productId) {
        Product product = findById(productId);

        return product;
    }

    @Override
    public Product findProductByDescription(String productDescription) {
        Product product = productRepository.findByDescription(productDescription);
        if (product == null)
            throw new ProductNotFoundException(ResponseMessageConstants.PRODUCT_NOT_FOUND);

        return product;
    }

    @Override
    public Product updateProduct(UpdateProductDto dto) {
        if(dto.getProductId() == 0)
            throw new RuntimeException(ResponseMessageConstants.PRODUCT_IS_EMPTY);

        return productRepository.save(mapUpdateProductDtoToProduct(dto));
    }

    @Override
    public Product updateProductDescription(Long productId, String productDescription) {
        Product product = findById(productId);

        product.setDescription(productDescription);

        return productRepository.save(product);
    }

    @Override
    public Product updateProductType(Long productId, Long productTypeId) {

        ProductType productType = productTypeService.findProductTypeById(productTypeId);
        Product product = findById(productId);

        if (productType == null) {
            throw new ProductTypeNotFoundException(ResponseMessageConstants.PRODUCT_TYPE_NOT_FOUND +
                    "(" + productTypeId +")");
        }

        product.setProductType(productType);

        return productRepository.save(product);
    }

    @Override
    public String deleteProductById(Long productId) {
        Product product = findById(productId);

        productRepository.deleteById(productId);

        return ResponseMessageConstants.PRODUCT_SUCCESSFULLY_DELETE;
    }

    Product findById(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null)
            throw new ProductNotFoundException(ResponseMessageConstants.PRODUCT_NOT_FOUND);

        return product;
    }

    public Product mapNewProductDtoToProduct(SaveNewProductDto dto) {
        Product product = new Product();

        product.setDescription(dto.getProductDescription());
        product.setQuantity(dto.getQuantity());
        product.setUnitOfMeasure(dto.getUnitOfMeasure());

        return product;
    }

    public Product mapUpdateProductDtoToProduct(UpdateProductDto dto) {
        Product product = findById(dto.getProductId());

        product.setDescription(dto.getProductDescription());
        product.setQuantity(dto.getQuantity());
        product.setUnitOfMeasure(dto.getUnitOfMeasure());

        return product;
    }
}
