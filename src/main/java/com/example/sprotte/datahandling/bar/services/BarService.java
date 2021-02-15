package com.example.sprotte.datahandling.bar.services;

import com.example.sprotte.dto.bar.SaveNewBarDto;
import com.example.sprotte.entity.Bar;

import java.util.List;

public interface BarService {

    List<Bar> getBars();

    Bar saveBar(SaveNewBarDto dto);

    Bar findBarById(Long barId);

    Bar findBarByName(String barName);

    Bar updateBarById(Long barId, String barName);

    String deleteBarById(Long barId);

    Bar addDeviceToBar(Long barId, Long deviceId);

    Bar removeDeviceFromBar(Long barId, Long deviceId);

    Bar switchBarSegmentToBar(Long barId, Long barSegmentId);
}
