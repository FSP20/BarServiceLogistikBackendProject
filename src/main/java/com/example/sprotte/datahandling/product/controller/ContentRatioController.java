package com.example.sprotte.datahandling.product.controller;

import com.example.sprotte.datahandling.product.service.ContentRatioService;
import com.example.sprotte.dto.product.SaveContentRatioProductBarContainerDto;
import com.example.sprotte.entity.contentratio.ContentRatioProductBarContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/contentRatio")
public class ContentRatioController {

    @Autowired
    ContentRatioService contentRatioService;

    @PostMapping("/setContentRatioProductBarContainer")
    ContentRatioProductBarContainer setContentRatioProductBarContainer(@RequestBody SaveContentRatioProductBarContainerDto dto) {
        return contentRatioService.setContentRatioProductBarContainer(dto);
    }

}
