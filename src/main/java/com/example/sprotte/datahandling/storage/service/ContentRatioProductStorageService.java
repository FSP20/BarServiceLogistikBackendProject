package com.example.sprotte.datahandling.storage.service;

import com.example.sprotte.dto.product.SaveContentRatioProductStorageDto;
import com.example.sprotte.entity.storageContentRatio.ContentRatioProductStorage;

public interface ContentRatioProductStorageService {

    ContentRatioProductStorage setContentRatioProductStorage(SaveContentRatioProductStorageDto dto);

    ContentRatioProductStorage updateMaxQuantityContentRatioProductStorage(Long productId, Long storageId, int maxQuantity);

    ContentRatioProductStorage updateActualQuantityContentRatioProductStorage(Long productId, Long storageId, int actualQuantity);

    ContentRatioProductStorage updateThresholdContentRatioProductStorage(Long productId, Long storageId, int threshold);

    String deleteRelationContentRatioProductStorage(Long productId, Long storageId);
}
