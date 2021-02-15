package com.example.sprotte.datahandling.bar.services;

import com.example.sprotte.dto.bar.SaveNewBarSegmentDto;
import com.example.sprotte.dto.bar.UpdateBarSegmentDto;
import com.example.sprotte.entity.BarSegment;

import java.util.List;

public interface BarSegmentService {

    List<BarSegment> getBarSegments();

    BarSegment saveBarSegment(SaveNewBarSegmentDto dto);

    BarSegment findBarSegmentById(Long barSegmentId);

    BarSegment findBarSegmentByDescription(String barSegment);

    BarSegment updateBarSegment(UpdateBarSegmentDto dto);

    BarSegment updateBarSegmentDescription(Long barSegmentId, String description);

    String deleteBarSegmentById(Long barSegmentId);

    BarSegment switchBarFromBarSegment(Long barSegmentId, Long barId);

    BarSegment switchBarContainerToBarSegment(Long barSegmentId, Long barContainerId);
}
