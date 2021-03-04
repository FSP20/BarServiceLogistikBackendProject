package com.example.sprotte.datahandling.product.controller;

import com.example.sprotte.datahandling.product.service.ContentRatioProductBarContainerService;
import com.example.sprotte.dto.product.SaveContentRatioProductBarContainerDto;
import com.example.sprotte.entity.barcontainerContentratio.ContentRatioProductBarContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contentRatioBarContainer")
public class ContentRatioProductBarContainerController {

    @Autowired
    ContentRatioProductBarContainerService contentRatioService;

    @PostMapping("/setContentRatioProductBarContainer")
    ContentRatioProductBarContainer setContentRatioProductBarContainer(@RequestBody SaveContentRatioProductBarContainerDto dto) {
        return contentRatioService.setContentRatioProductBarContainer(dto);
    }

    @PutMapping("/updateMaxQuantityContentRatio/{productId}/{barContainerId}/{max}")
    ContentRatioProductBarContainer updateMaxQuantityContentRatio(@PathVariable("productId") Long productId,
                                                                  @PathVariable("barContainerId") Long barContainerID,
                                                                  @PathVariable("max") int maxQuantity) {
        return contentRatioService.updateMaxQuantityContentRatio(productId, barContainerID, maxQuantity);
    }

    @PutMapping("/updateActualQuantityContentRatio/{productId}/{barContainerId}/{actual}")
    ContentRatioProductBarContainer updateActualQuantityContentRatio(@PathVariable("productId") Long productId,
                                                                     @PathVariable("barContainerId") Long barContainerID,
                                                                     @PathVariable("actual") int actualQuantity) {
        return contentRatioService.updateActualQuantityContentRatio(productId, barContainerID, actualQuantity);
    }

    @PutMapping("/updateThresholdContentRatio/{productId}/{barContainerId}/{threshold}")
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
