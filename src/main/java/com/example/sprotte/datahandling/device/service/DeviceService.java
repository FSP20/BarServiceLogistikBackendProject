package com.example.sprotte.datahandling.device.service;

import com.example.sprotte.dto.device.SaveNewDeviceDto;
import com.example.sprotte.dto.device.UpdateDeviceDto;
import com.example.sprotte.entity.Device;

import java.util.List;

public interface DeviceService {

    List<Device> getDevices();

    Device saveDevice(SaveNewDeviceDto dto);

    Device findDeviceById(Long deviceId);

    Device findByDeviceName(String device);

    Device updateDevice(UpdateDeviceDto dto);

    String deleteDeviceById(Long deviceId);

    Device addBarToDevice(Long deviceId, Long barId);

    Device removeBarFromDevice(Long deviceId, Long barId);
}
