package com.example.sprotte.datahandling.bar.controller;

import com.example.sprotte.datahandling.bar.services.BarService;
import com.example.sprotte.dto.bar.SaveNewBarDto;
import com.example.sprotte.entity.Bar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/bar")
public class BarController {

    @Autowired
    BarService barService;

    @GetMapping("/getbars")
    public List<Bar> getBars() {
        return barService.getBars();
    }

    @PostMapping("/savebar")
    public Bar saveBar(@RequestBody SaveNewBarDto dto) {
        return barService.saveBar(dto);
    }

    @GetMapping("/getbar/{barId}")
    public Bar findBarById(@PathVariable("barId") Long barId) {
        return barService.findBarById(barId);
    }

    @GetMapping("/getbarbyname/{bar}")
    public Bar findBarByName(@PathVariable("bar") String barName) {
        return barService.findBarByName(barName);
    }

    @PutMapping("/updatebar/{barId}/{barName}")
    public Bar updateBarById(@PathVariable("barId") Long barId, @PathVariable("barName") String barName) {
        return barService.updateBarById(barId,barName);
    }

    @DeleteMapping("/deletebar/{id}")
    public String deleteBarById(@PathVariable("id") Long barId) {
        return barService.deleteBarById(barId);
    }

    @PutMapping("/adddevicetobar/{barId}/{deviceId}")
    public Bar addDeviceToBar(@PathVariable("barId") Long barId, @PathVariable("deviceId") Long deviceId) {
        return barService.addDeviceToBar(barId, deviceId);
    }

    @DeleteMapping("/removedevicefrombar/{barId}/{deviceId}")
    public Bar removeDeviceFromBar(@PathVariable("barId") Long barId, @PathVariable("deviceId") Long deviceId) {
        return barService.removeDeviceFromBar(barId, deviceId);
    }

    @PutMapping("/switchBarSegmentToBar/{barId}/{barSegmentId}")
    public Bar switchBarSegmentToBar(@PathVariable("barId") Long barId, @PathVariable("barSegmentId") Long barSegmentId) {
        return barService.switchBarSegmentToBar(barId, barSegmentId);
    }
}
