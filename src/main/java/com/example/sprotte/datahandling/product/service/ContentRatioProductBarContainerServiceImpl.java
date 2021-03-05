package com.example.sprotte.datahandling.product.service;

import com.example.sprotte.constants.ResponseMessageConstants;
import com.example.sprotte.datahandling.bar.repository.BarContainerRepository;
import com.example.sprotte.datahandling.product.repository.ContentRatioProductBarContainerRepository;
import com.example.sprotte.datahandling.product.repository.ProductRepository;
import com.example.sprotte.dto.product.SaveContentRatioProductBarContainerDto;
import com.example.sprotte.entity.BarContainer;
import com.example.sprotte.entity.Product;
import com.example.sprotte.entity.barcontainerContentratio.ContentRatioProductBarContainer;
import com.example.sprotte.errorhandling.Bar.BarContainerNotFoundException;
import com.example.sprotte.errorhandling.product.ContentRatioRelationNotFoundException;
import com.example.sprotte.errorhandling.product.IllegalContentRatioException;
import com.example.sprotte.errorhandling.product.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentRatioProductBarContainerServiceImpl implements ContentRatioProductBarContainerService {

    @Autowired
    ContentRatioProductBarContainerRepository contentRatioRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BarContainerRepository barContainerRepository;

    @Override
    public ContentRatioProductBarContainer setContentRatioProductBarContainer(SaveContentRatioProductBarContainerDto dto) {
        Product product = findProductById(dto.getProductId());

        BarContainer barContainer = findBarContainerById(dto.getBarContainerId());

        ContentRatioProductBarContainer contentRatio = findContentRatioByProductIdAndBarContainerId(dto.getProductId(), dto.getBarContainerId());
        if (contentRatio != null)
            throw new IllegalContentRatioException(ResponseMessageConstants.CONTENT_RATIO_ALREADY_EXIST);

        contentRatio = new ContentRatioProductBarContainer();
        contentRatio.setBarContainer(barContainer);
        contentRatio.setProduct(product);
        contentRatio.setMaxQuantity(dto.getMaxQuantity());
        contentRatio.setActualQuantity(dto.getActualQuantity());
        contentRatio.setThreshold(dto.getThreshold());
        contentRatio.getId().setProductId(product.getId());
        contentRatio.getId().setBarContainerId(barContainer.getId());

        return saveContentRatio(contentRatio);
    }

    @Override
    public ContentRatioProductBarContainer updateMaxQuantityContentRatio(Long productId, Long barContainerId, int maxQuantity) {
        ContentRatioProductBarContainer contentRatio = findContentRatioByProductIdAndBarContainerId(productId, barContainerId);

        contentRatio.setMaxQuantity(maxQuantity);

        return contentRatio;
    }

    @Override
    public ContentRatioProductBarContainer updateActualQuantityContentRatio(Long productId, Long barContainerId, int actualQuantity) {
        ContentRatioProductBarContainer contentRatio = findContentRatioByProductIdAndBarContainerId(productId, barContainerId);

        contentRatio.setActualQuantity(actualQuantity);

        return contentRatio;
    }

    @Override
    public ContentRatioProductBarContainer updateThresholdContentRatio(Long productId, Long barContainerId, int threshold) {
        ContentRatioProductBarContainer contentRatio = findContentRatioByProductIdAndBarContainerId(productId, barContainerId);

        contentRatio.setThreshold(threshold);

        return contentRatio;
    }

    @Override
    public String deleteRelationContentRatio(Long productId, Long barContainerId) {
        Product product = findProductById(productId);

        BarContainer barContainer = findBarContainerById(barContainerId);

        deleteContentRatioByProductIdAndBarContainerId(productId, barContainerId);

        return ResponseMessageConstants.CONTENT_RATIO_SUCCESSFULLY_DELETE;
    }

    public Product findProductById(Long productId) {
        Product product =  productRepository.findById(productId).orElse(null);
        if(product == null)
            throw new ProductNotFoundException(ResponseMessageConstants.PRODUCT_NOT_FOUND);

        return product;
    }

    public BarContainer findBarContainerById(Long barContainerId) {
        BarContainer barContainer = barContainerRepository.findById(barContainerId).orElse(null);
        if(barContainer == null)
            throw new BarContainerNotFoundException(ResponseMessageConstants.BAR_CONTAINER_NOT_FOUND);

        return barContainer;
    }

    public ContentRatioProductBarContainer saveContentRatio(ContentRatioProductBarContainer contentRatio) {
        return contentRatioRepository.save(contentRatio);
    }

    public ContentRatioProductBarContainer findContentRatioByProductIdAndBarContainerId(Long productId, Long barContainerId) {
        ContentRatioProductBarContainer contentRatio = contentRatioRepository.findByProductIdAndBarContainerId(productId, barContainerId);
        if (contentRatio == null)
            throw new ContentRatioRelationNotFoundException(ResponseMessageConstants.CONTENT_RATIO_NOT_FOUND);

        return contentRatio;
    }

    public void deleteContentRatioByProductIdAndBarContainerId(Long productId, Long barContainerId){
        contentRatioRepository.deleteByProductIdAndBarContainerId(productId, barContainerId);
    }
}
