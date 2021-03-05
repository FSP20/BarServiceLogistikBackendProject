package com.example.sprotte.datahandling.storage.service;

import com.example.sprotte.constants.ResponseMessageConstants;
import com.example.sprotte.datahandling.product.repository.ProductRepository;
import com.example.sprotte.datahandling.storage.repository.ContentRatioProductStorageRepository;
import com.example.sprotte.datahandling.storage.repository.StorageRepository;
import com.example.sprotte.dto.product.SaveContentRatioProductStorageDto;
import com.example.sprotte.entity.Product;
import com.example.sprotte.entity.Storage;
import com.example.sprotte.entity.storageContentRatio.ContentRatioProductStorage;
import com.example.sprotte.errorhandling.product.ContentRatioRelationNotFoundException;
import com.example.sprotte.errorhandling.product.IllegalContentRatioException;
import com.example.sprotte.errorhandling.product.ProductNotFoundException;
import com.example.sprotte.errorhandling.storage.StorageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentRatioProductStorageServiceImpl implements ContentRatioProductStorageService {

    @Autowired
    ContentRatioProductStorageRepository contentRatioRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    StorageRepository storageRepository;

    @Override
    public ContentRatioProductStorage setContentRatioProductStorage(SaveContentRatioProductStorageDto dto) {
        Product product = findProductById(dto.getProductId());

        Storage storage = findStorageById(dto.getStorageId());

        ContentRatioProductStorage contentRatio = findContentRatioByProductIdAndStorageId(dto.getProductId(), dto.getStorageId());
        if (contentRatio != null)
            throw new IllegalContentRatioException(ResponseMessageConstants.CONTENT_RATIO_ALREADY_EXIST);

        contentRatio = new ContentRatioProductStorage();
        contentRatio.setProduct(product);
        contentRatio.setStorage(storage);
        contentRatio.setMaxQuantity(dto.getMaxQuantity());
        contentRatio.setActualQuantity(dto.getActualQuantity());
        contentRatio.setThreshold(dto.getThreshold());
        contentRatio.getId().setProductId(product.getId());
        contentRatio.getId().setStorageId(storage.getId());

        return saveContentRatio(contentRatio);
    }

    @Override
    public ContentRatioProductStorage updateMaxQuantityContentRatioProductStorage(Long productId, Long storageId, int maxQuantity) {
        ContentRatioProductStorage contentRatio = findContentRatioByProductIdAndStorageId(productId, storageId);

        contentRatio.setMaxQuantity(maxQuantity);

        return contentRatio;
    }

    @Override
    public ContentRatioProductStorage updateActualQuantityContentRatioProductStorage(Long productId, Long storageId, int actualQuantity) {
        ContentRatioProductStorage contentRatio = findContentRatioByProductIdAndStorageId(productId, storageId);

        contentRatio.setActualQuantity(actualQuantity);

        return contentRatio;
    }

    @Override
    public ContentRatioProductStorage updateThresholdContentRatioProductStorage(Long productId, Long storageId, int threshold) {
        ContentRatioProductStorage contentRatio = findContentRatioByProductIdAndStorageId(productId, storageId);

        contentRatio.setThreshold(threshold);

        return contentRatio;
    }

    @Override
    public String deleteRelationContentRatioProductStorage(Long productId, Long storageId) {
        // Only Proof that Product exist
        findProductById(productId);

        // Only Proof that Storage exist
        findStorageById(storageId);

        deleteContentRatioByProductIdAndStorageId(productId, storageId);

        return ResponseMessageConstants.CONTENT_RATIO_SUCCESSFULLY_DELETE;
    }

    public Product findProductById(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if(product == null)
            throw new ProductNotFoundException(ResponseMessageConstants.PRODUCT_NOT_FOUND);

        return product;
    }

    public Storage findStorageById(Long storageId) {
        Storage storage = storageRepository.findById(storageId).orElse(null);
        if (storage == null)
            throw new StorageNotFoundException(ResponseMessageConstants.STORAGE_NOT_FOUND);

        return storage;
    }

    public ContentRatioProductStorage saveContentRatio(ContentRatioProductStorage contentRatio) {
        return contentRatioRepository.save(contentRatio);
    }

    public ContentRatioProductStorage findContentRatioByProductIdAndStorageId(Long productId, Long storageId) {
        ContentRatioProductStorage contentRatio = contentRatioRepository.findByProductIdAndStorageId(productId, storageId);
        if (contentRatio == null)
            throw new ContentRatioRelationNotFoundException(ResponseMessageConstants.CONTENT_RATIO_NOT_FOUND);

        return contentRatio;
    }

    public void deleteContentRatioByProductIdAndStorageId(Long productId, Long storageId) {
        contentRatioRepository.deleteByProductIdAndStorageId(productId, storageId);
    }
}
