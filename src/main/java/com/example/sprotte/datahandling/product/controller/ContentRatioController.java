package com.example.sprotte.datahandling.product.controller;

import com.example.sprotte.datahandling.product.service.ContentRatioService;
import com.example.sprotte.dto.product.SaveContentRatioProductBarContainerDto;
import com.example.sprotte.entity.contentratio.ContentRatioProductBarContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/contentRatio")
public class ContentRatioController {

    @Autowired
    ContentRatioService contentRatioService;

    @PostMapping("/setContentRatioProductBarContainer")
    ContentRatioProductBarContainer setContentRatioProductBarContainer(@RequestBody SaveContentRatioProductBarContainerDto dto) {
        return contentRatioService.setContentRatioProductBarContainer(dto);
    }

    @PostMapping("/updateMaxQuantityContentRatio/{productId}/{barContainerId}/{max}")
    ContentRatioProductBarContainer updateMaxQuantityContentRatio(@PathVariable("productId") Long productId,
                                                                  @PathVariable("barContainerId") Long barContainerID,
                                                                  @PathVariable("max") int maxQuantity) {
        return contentRatioService.updateMaxQuantityContentRatio(productId, barContainerID, maxQuantity);
    }

    @PostMapping("/updateActualQuantityContentRatio/{productId}/{barContainerId}/{actual}")
    ContentRatioProductBarContainer updateActualQuantityContentRatio(@PathVariable("productId") Long productId,
                                                                     @PathVariable("barContainerId") Long barContainerID,
                                                                     @PathVariable("actual") int actualQuantity) {
        return contentRatioService.updateActualQuantityContentRatio(productId, barContainerID, actualQuantity);
    }

    @PostMapping("/updateThresholdContentRatio/{productId}/{barContainerId}/{max}")
    ContentRatioProductBarContainer updateThresholdContentRatio(@PathVariable("productId") Long productId,
                                                                @PathVariable("barContainerId") Long barContainerID,
                                                                @PathVariable("threshold") int threshold) {
        return contentRatioService.updateThresholdContentRatio(productId, barContainerID, threshold);
    }

    @DeleteMapping("/deleteRelationContentRatio/{productId}/{barContainerId}")
    String deleteRelationContentRatio(@PathVariable("productId") Long productId,
                                      @PathVariable("barContainerId") Long barContainerID) {
        return contentRatioService.deleteRelationContentRatio(productId, barContainerID);
    }
}
