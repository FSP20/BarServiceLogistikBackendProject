package com.example.sprotte.datahandling.product.service;

import com.example.sprotte.dto.product.SaveContentRatioProductBarContainerDto;
import com.example.sprotte.entity.contentratio.ContentRatioProductBarContainer;

public interface ContentRatioService {

    public ContentRatioProductBarContainer setContentRatioProductBarContainer(SaveContentRatioProductBarContainerDto dto);
}
