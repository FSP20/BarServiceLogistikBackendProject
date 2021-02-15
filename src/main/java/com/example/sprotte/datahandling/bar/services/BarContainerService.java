package com.example.sprotte.datahandling.bar.services;

import com.example.sprotte.dto.bar.SaveNewBarContainerDto;
import com.example.sprotte.dto.bar.UpdateBarContainerDto;
import com.example.sprotte.entity.BarContainer;

import java.util.List;

public interface BarContainerService {

    List<BarContainer> getBarContainer();

    BarContainer saveBarContainer(SaveNewBarContainerDto dto);

    BarContainer findBarContainerById(Long barContainerId);

    BarContainer findBarContainerByDescription(String barContainerDescription);

    BarContainer updateBarContainer(UpdateBarContainerDto dto);

    BarContainer updateBarContainerDescription(Long barContainerId, String description);

    String deleteBarContainerById(Long barContainerId);

    BarContainer switchBarSegmentFromBarContainer(Long barContainerId, Long barSegmentId);

    BarContainer switchContainerCategoryFromBarContainer(Long barContainerId, Long containerCategoryId);
}
