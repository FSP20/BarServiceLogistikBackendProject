package com.example.sprotte.datahandling.bar.controller;

import com.example.sprotte.datahandling.bar.services.BarContainerService;
import com.example.sprotte.dto.bar.SaveNewBarContainerDto;
import com.example.sprotte.dto.bar.UpdateBarContainerDto;
import com.example.sprotte.entity.BarContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barContainer")
public class BarContainerController {

    @Autowired
    BarContainerService barContainerService;

    @GetMapping("/getBarContainers")
    public List<BarContainer> getBarContainers() {
        return barContainerService.getBarContainer();
    }

    @PostMapping("/saveBarContainer")
    public BarContainer saveBarContainer(@RequestBody SaveNewBarContainerDto dto) {
        return barContainerService.saveBarContainer(dto);
    }

    @GetMapping("/findBarContainerById/{id}")
    public BarContainer findBarContainerById(@PathVariable("id") Long barContainerId) {
        return barContainerService.findBarContainerById(barContainerId);
    }

    @GetMapping("/findBarContainerByDescription/{description}")
    public BarContainer findBarContainerByDescription(@PathVariable("description") String description) {
        return barContainerService.findBarContainerByDescription(description);
    }

    @PutMapping("/updateBarContainer")
    public BarContainer updateBarContainer(@RequestBody UpdateBarContainerDto dto) {
        return barContainerService.updateBarContainer(dto);
    }

    @PutMapping("/updateBarContainerDescription/{id}/{description}")
    public BarContainer updateBarContainerDescription(@PathVariable("id") Long barContainerId,
                                                      @PathVariable("description") String description) {
        return barContainerService.updateBarContainerDescription(barContainerId, description);
    }

    @DeleteMapping("/deleteBarContainerById/{id}")
    public String deleteBarContainerById(@PathVariable("id") Long barContainerId) {
        return barContainerService.deleteBarContainerById(barContainerId);
    }

    @PutMapping("/switchBarSegmentFromBarContainer/{barContainerId}/{barSegmentId}")
    public BarContainer switchBarSegmentFromBarContainer(@PathVariable("barContainerId") Long barContainerId,
                                                         @PathVariable("barSegmentId") Long barSegmentId) {
        return barContainerService.switchBarSegmentFromBarContainer(barContainerId, barSegmentId);
    }

    @PutMapping("/switchContainerCategoryFromBarContainer/{barContainerId}/{containerCategoryId}")
    public BarContainer switchContainerCategoryFromBarContainer(@PathVariable("barContainerId") Long barContainerId,
                                                         @PathVariable("containerCategoryId") Long containerCategoryId) {
        return barContainerService.switchContainerCategoryFromBarContainer(barContainerId, containerCategoryId);
    }
}
