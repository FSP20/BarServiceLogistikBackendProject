package com.example.sprotte.datahandling.product.service;

import com.example.sprotte.constants.ResponseMessageConstants;
import com.example.sprotte.datahandling.product.repository.ProductRepository;
import com.example.sprotte.entity.Product;
import com.example.sprotte.errorhandling.product.ProductNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl service;

    @Test
    void getProductId() {
        Long productId = 1L;
        Product product = new Product();
        given(productRepository.findById(productId)).willReturn(Optional.of(product));

        Product foundProduct = service.findProductById(productId);

        assertThat(foundProduct).isNotNull();
        then(productRepository).should().findById(anyLong());
    }

    @Test
    void getProductIdThrow() {
        Long productId = 1L;
        given(productRepository.findById(productId)).willThrow(new ProductNotFoundException(ResponseMessageConstants.PRODUCT_NOT_FOUND));

        assertThrows(ProductNotFoundException.class, () -> service.deleteProductById(productId));

        then(productRepository).should().findById(anyLong());
    }

    @Test
    void getProductByDescription() {
        String productDescription = "Cola Cola Flasche";
        Product product = new Product();
        given(productRepository.findByDescription(productDescription)).willReturn(product);

        Product foundProduct = service.findProductByDescription(productDescription);

        assertThat(foundProduct).isNotNull();
        then(productRepository).should().findByDescription(anyString());
    }

    @Test
    void getProductByDescriptionThrow() {
        String productDescription = "Cola Cola Flasche";
        given(productRepository.findByDescription(productDescription)).willThrow(new ProductNotFoundException(ResponseMessageConstants.PRODUCT_NOT_FOUND));

        assertThrows(ProductNotFoundException.class, () -> service.findProductByDescription(productDescription));

        then(productRepository).should().findByDescription(anyString());
    }


}