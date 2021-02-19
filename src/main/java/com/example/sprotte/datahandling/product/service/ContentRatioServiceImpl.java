package com.example.sprotte.datahandling.product.service;

import com.example.sprotte.datahandling.bar.repository.BarContainerRepository;
import com.example.sprotte.datahandling.product.repository.ContentRatioRepository;
import com.example.sprotte.datahandling.product.repository.ProductRepository;
import com.example.sprotte.dto.product.SaveContentRatioProductBarContainerDto;
import com.example.sprotte.entity.BarContainer;
import com.example.sprotte.entity.Product;
import com.example.sprotte.entity.contentratio.ContentRatioProductBarContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentRatioServiceImpl implements ContentRatioService{

    @Autowired
    ContentRatioRepository contentRatioRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BarContainerRepository barContainerRepository;

    @Override
    public ContentRatioProductBarContainer setContentRatioProductBarContainer(SaveContentRatioProductBarContainerDto dto) {
        Product product = findProductById(dto.getProductId());

        BarContainer barContainer = findBarContainerById(dto.getBarContainerId());

        ContentRatioProductBarContainer contentRatio = new ContentRatioProductBarContainer();
        contentRatio.setBarContainer(barContainer);
        contentRatio.setProduct(product);
        contentRatio.setMaxQuantity(dto.getMaxQuantity());
        contentRatio.setActualQuantity(dto.getActualQuantity());
        contentRatio.setThreshold(dto.getThreshold());
        contentRatio.getId().setProductId(product.getId());
        contentRatio.getId().setBarContainerId(barContainer.getId());

        return contentRatioRepository.save(contentRatio);
    }

    public Product findProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public BarContainer findBarContainerById(Long barContainerId) {
        return barContainerRepository.findById(barContainerId).orElse(null);
    }
}
