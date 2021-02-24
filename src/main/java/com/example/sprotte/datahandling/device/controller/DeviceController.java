package com.example.sprotte.datahandling.device.controller;

import com.example.sprotte.datahandling.device.service.DeviceService;
import com.example.sprotte.dto.device.SaveNewDeviceDto;
import com.example.sprotte.dto.device.UpdateDeviceDto;
import com.example.sprotte.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @GetMapping("/getDevices")
    public List<Device> getDevices() {return deviceService.getDevices();}

    @PostMapping("/saveDevice")
    public Device saveDevice(@RequestBody SaveNewDeviceDto dto) {
        return deviceService.saveDevice(dto);
    }

    @GetMapping("/getDevice/{deviceId}")
    public Device findDeviceById(@PathVariable("deviceId") Long deviceId) {
        return deviceService.findDeviceById(deviceId);
    }

    @GetMapping("/getDeviceByName/{device}")
    public Device findDeviceByName(@PathVariable("device") String device) {
        return deviceService.findByDeviceName(device);
    }

    @PutMapping("/updateDevice")
    public Device updateDevice(@RequestBody UpdateDeviceDto dto) {
        return deviceService.updateDevice(dto);
    }

    @DeleteMapping("deleteDeviceById/{id}")
    public String deleteDeviceById(@PathVariable("id") Long deviceId) {
        return deviceService.deleteDeviceById(deviceId);
    }

    @PutMapping("addBarToDevice/{deviceId}/{barId}")
    public Device addBarToDevice(@PathVariable("deviceId") Long deviceId, @PathVariable("barId") Long barId) {
        return deviceService.addBarToDevice(deviceId, barId);
    }

    @PutMapping("removeBarFromDevice/{deviceId}/{barId}")
    public Device removeBarFromDevice(@PathVariable("deviceId") Long deviceId, @PathVariable("barId") Long barId) {
        return deviceService.removeBarFromDevice(deviceId, barId);
    }
}
