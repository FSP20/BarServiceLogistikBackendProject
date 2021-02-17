package com.example.sprotte.datahandling.product.service;

import com.example.sprotte.constants.ResponseMessageConstants;
import com.example.sprotte.datahandling.product.repository.ProductRepository;
import com.example.sprotte.dto.product.SaveNewProductDto;
import com.example.sprotte.dto.product.UpdateProductDto;
import com.example.sprotte.entity.Product;
import com.example.sprotte.errorhandling.product.IllegalProductException;
import com.example.sprotte.errorhandling.product.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        List<Product> products = productRepository.findAll();
        if(products.size() == 0)
            throw new ProductNotFoundException(ResponseMessageConstants.PRODUCT_NOT_FOUND);

        return products;
    }

    @Override
    public Product saveProduct(SaveNewProductDto dto) {
        if (dto.getProductDescription() != null) {
            // Proof Product Description already exist
            Product product = findProductByDescription(dto.getProductDescription());
            if (product == null) {
                return productRepository.save(mapNewProductDtoToProduct(dto));
            } else {
                throw new IllegalProductException(ResponseMessageConstants.PRODUCT_ALREADY_EXIST);
            }
        } else {
            throw new RuntimeException(ResponseMessageConstants.PRODUCT_IS_EMPTY);
        }
    }

    @Override
    public Product findProductById(Long productId) {
        Product product = findById(productId);
        if(product == null)
            throw new ProductNotFoundException(ResponseMessageConstants.PRODUCT_NOT_FOUND);

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
        if(dto.getProductId() == 0) {
            return productRepository.save(mapUpdateProductDtoToProduct(dto));
        } else {
            throw new RuntimeException(ResponseMessageConstants.PRODUCT_IS_EMPTY);
        }
    }

    @Override
    public Product updateProductDescription(Long productId, String productDescription) {
        Product product = findById(productId);
        if (product == null)
            throw new ProductNotFoundException(ResponseMessageConstants.PRODUCT_NOT_FOUND);
        product.setProductDescription(productDescription);

        return productRepository.save(product);
    }

    @Override
    public String deleteProductById(Long productId) {
        Product product = findById(productId);
        if(product == null)
            throw new ProductNotFoundException(ResponseMessageConstants.PRODUCT_NOT_FOUND);

        productRepository.deleteById(productId);
        return ResponseMessageConstants.PRODUCT_SUCCESSFULLY_DELETE;
    }

    Product findById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public Product mapNewProductDtoToProduct(SaveNewProductDto dto) {
        Product product = new Product();

        product.setProductDescription(dto.getProductDescription());
        product.setQuantity(dto.getQuantity());
        product.setUnitOfMeasure(dto.getUnitOfMeasure());

        return product;
    }

    public Product mapUpdateProductDtoToProduct(UpdateProductDto dto) {
        Product product = findById(dto.getProductId());
        if (product == null)
            throw new ProductNotFoundException(ResponseMessageConstants.PRODUCT_NOT_FOUND);

        product.setProductDescription(dto.getProductDescription());
        product.setQuantity(dto.getQuantity());
        product.setUnitOfMeasure(dto.getUnitOfMeasure());

        return product;
    }
}
