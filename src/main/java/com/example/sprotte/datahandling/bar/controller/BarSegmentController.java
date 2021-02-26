package com.example.sprotte.datahandling.bar.controller;

import com.example.sprotte.datahandling.bar.services.BarSegmentService;
import com.example.sprotte.dto.bar.SaveNewBarSegmentDto;
import com.example.sprotte.dto.bar.UpdateBarSegmentDto;
import com.example.sprotte.entity.BarSegment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barsegment")
public class BarSegmentController {

    @Autowired
    BarSegmentService barSegmentService;

    @GetMapping("/getBarSegments")
    public List<BarSegment> getBarSegments() {
        return barSegmentService.getBarSegments();
    }

    @PostMapping("/saveBarSegment")
    public BarSegment saveBarSegment(@RequestBody SaveNewBarSegmentDto dto) {
        return barSegmentService.saveBarSegment(dto);
    }

    @GetMapping("/getBarSegment/{id}")
    public BarSegment findBarSegmentById(@PathVariable("id") Long barSegmentId) {
        return barSegmentService.findBarSegmentById(barSegmentId);
    }

    @GetMapping("/getBarSegmentByDescription/{name}")
    public BarSegment findBarSegmentByDescription(@PathVariable("name") String barSegmentDescription){
        return barSegmentService.findBarSegmentByDescription(barSegmentDescription);
    }

    @PutMapping("/updateBarSegment")
    public BarSegment updateBarSegment(@RequestBody UpdateBarSegmentDto dto) {
        return barSegmentService.updateBarSegment(dto);
    }

    @PutMapping("/updateBarSegmentDescription/{id}/{description}")
    public BarSegment updateBarSegmentDescription(@PathVariable("id") Long barSegmentId,
                                                  @PathVariable("description") String description) {
        return barSegmentService.updateBarSegmentDescription(barSegmentId, description);
    }

    @DeleteMapping("/deleteBarSegmentById/{id}")
    public String deleteBarSegmentById(@PathVariable("id") Long barSegmentId) {
        return barSegmentService.deleteBarSegmentById(barSegmentId);
    }

    @PutMapping("/switchBarFromBarSegment/{barSegmentId}/{barId}")
    public BarSegment switchBarFromBarSegment(@PathVariable("barSegmentId") Long barSegmentId,
                                         @PathVariable("barId") Long barId) {
        return barSegmentService.switchBarFromBarSegment(barSegmentId, barId);
    }

    @PutMapping("/switchBarContainerToBarSegment/{barSegmentId}/{barContainerId}")
    public BarSegment switchBarContainerToBarSegment(@PathVariable("barSegmentId") Long barSegmentId,
                                                     @PathVariable("barContainerId") Long barContainerId) {
        return barSegmentService.switchBarContainerToBarSegment(barSegmentId, barContainerId);
    }
}
