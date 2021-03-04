package com.example.sprotte.datahandling.product.service;

import com.example.sprotte.dto.product.SaveContentRatioProductBarContainerDto;
import com.example.sprotte.entity.barcontainerContentratio.ContentRatioProductBarContainer;

public interface ContentRatioProductBarContainerService {

    public ContentRatioProductBarContainer setContentRatioProductBarContainer(SaveContentRatioProductBarContainerDto dto);

    public ContentRatioProductBarContainer updateMaxQuantityContentRatio(Long productId, Long barContainerId, int maxQuantity);

    public ContentRatioProductBarContainer updateActualQuantityContentRatio(Long productId, Long barContainerId, int actualQuantity);

    public ContentRatioProductBarContainer updateThresholdContentRatio(Long productId, Long barContainerId, int threshold);

    public String deleteRelationContentRatio(Long productId, Long barContainerId);
}
