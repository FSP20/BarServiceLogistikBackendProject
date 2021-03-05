package com.example.sprotte.datahandling.storage.controller;

import com.example.sprotte.datahandling.storage.service.ContentRatioProductStorageService;
import com.example.sprotte.dto.product.SaveContentRatioProductStorageDto;
import com.example.sprotte.entity.storageContentRatio.ContentRatioProductStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contentRatioStorage")
public class ContentRatioProductStorageController {

    @Autowired
    ContentRatioProductStorageService contentRatioService;

    @PostMapping("/setContentRatioProductStorage")
    ContentRatioProductStorage setContentRatioProductStorage(@RequestBody SaveContentRatioProductStorageDto dto) {
        return contentRatioService.setContentRatioProductStorage(dto);
    }

    @PutMapping("/updateMaxQuantityContentRatioProductStorage/{productId}/{storageId}/{max}")
    ContentRatioProductStorage updateMaxQuantityContentRatioProductStorage(@PathVariable("productId") Long productId,
                                                                           @PathVariable("storageId") Long storageId,
                                                                           @PathVariable("max") int maxQuantity) {
        return contentRatioService.updateMaxQuantityContentRatioProductStorage(productId, storageId, maxQuantity);
    }

    @PutMapping("/updateActualQuantityContentRatioProductStorage/{productId}/{storageId}/{actual}")
    ContentRatioProductStorage updateActualQuantityContentRatioProductStorage(@PathVariable("productId") Long productId,
                                                                              @PathVariable("storageId") Long storageId,
                                                                              @PathVariable("actual") int actualQuantity) {
        return contentRatioService.updateActualQuantityContentRatioProductStorage(productId, storageId, actualQuantity);
    }

    @PutMapping("/updateThresholdContentRatioProductStorage/{productId}/{storageId}/{threshold}")
    ContentRatioProductStorage updateThresholdContentRatioProductStorage(@PathVariable("productId") Long productId,
                                                                         @PathVariable("storageId") Long storageId,
                                                                         @PathVariable("threshold") int threshold) {
        return contentRatioService.updateThresholdContentRatioProductStorage(productId, storageId, threshold);
    }

    @DeleteMapping("/deleteRelationContentRatioProductStorage/{productId}/{storageId}")
    String deleteRelationContentRatioProductStorage(@PathVariable("productId") Long productId,
                                                    @PathVariable("storageId") Long storageId) {
        return contentRatioService.deleteRelationContentRatioProductStorage(productId, storageId);
    }
}
