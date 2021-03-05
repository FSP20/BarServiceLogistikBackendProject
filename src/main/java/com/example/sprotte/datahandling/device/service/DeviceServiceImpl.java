package com.example.sprotte.datahandling.device.service;

import com.example.sprotte.constants.ResponseMessageConstants;
import com.example.sprotte.datahandling.bar.repository.BarRepository;
import com.example.sprotte.datahandling.device.repository.DeviceRepository;
import com.example.sprotte.dto.device.SaveNewDeviceDto;
import com.example.sprotte.dto.device.UpdateDeviceDto;
import com.example.sprotte.entity.Bar;
import com.example.sprotte.entity.Device;
import com.example.sprotte.errorhandling.Bar.BarNotFoundException;
import com.example.sprotte.errorhandling.Employee.DeviceNotFoundException;
import com.example.sprotte.errorhandling.Employee.IllegalDeviceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  DeviceServiceImpl implements DeviceService{

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    BarRepository barRepository;

    @Override
    public List<Device> getDevices() {
        List<Device> devices = deviceRepository.findAll();
        if(devices.size() == 0)
            throw new DeviceNotFoundException(ResponseMessageConstants.DEVICE_NOT_FOUND);

        return devices;
    }

    @Override
    public Device saveDevice(SaveNewDeviceDto dto) {
        if(dto.getDeviceNumber() == 0)
            throw new RuntimeException(ResponseMessageConstants.DEVICE_IS_EMPTY);

        // Proof deviceNumber already exist
        findByDeviceNumber(dto.getDeviceNumber());

        return deviceRepository.save(mapNewDeviceDtoToDevice(dto));
    }

    @Override
    public Device findDeviceById(Long deviceId) {
        Device device = findById(deviceId);

        return device;
    }

    @Override
    public Device findByDeviceName(String deviceDescription) {
        Device device = findDeviceByDescription(deviceDescription);

        return device;
    }

    @Override
    public Device updateDevice(UpdateDeviceDto dto) {
        if(dto.getDeviceNumber() == 0)
            throw new RuntimeException(ResponseMessageConstants.DEVICE_IS_EMPTY);

        return deviceRepository.save(mapUpdateDeviceDtoToDevice(dto));
    }

    @Override
    public String deleteDeviceById(Long deviceId) {
        Device device = findById(deviceId);

        deviceRepository.deleteById(deviceId);

        return ResponseMessageConstants.DEVICE_SUCCESSFULLY_DELETE;
    }

    @Override
    public Device addBarToDevice(Long deviceId, Long barId) {
        Bar bar = findBarById(barId);

        Device device = findById(deviceId);

        device.setBar(bar);

        if (!bar.getDevices().contains(device)) {
            bar.getDevices().add(device);
        }

        return deviceRepository.save(device);
    }

    @Override
    public Device removeBarFromDevice(Long deviceId, Long barId) {
        Bar bar = findBarById(barId);

        Device device = findById(deviceId);

        device.setBar(null);

        bar.getDevices().removeIf(tempDevice -> tempDevice.getId() == device.getId());

        return deviceRepository.save(device);
    }

    public void findByDeviceNumber(int deviceNumber) {
        Device testDevice = deviceRepository.findByDeviceNumber(deviceNumber);
        if(testDevice != null)
            throw new IllegalDeviceException(ResponseMessageConstants.DEVICE_ALREADY_EXIST);
    }

    public Device findById(Long deviceId) {
        Device device = deviceRepository.findById(deviceId).orElse(null);
        if(device == null)
            throw new DeviceNotFoundException(ResponseMessageConstants.DEVICE_NOT_FOUND);

        return device;
    }

    public Device findDeviceByDescription(String deviceDescription) {
        Device device = deviceRepository.findByDescription(deviceDescription);
        if(device == null)
            throw new DeviceNotFoundException(ResponseMessageConstants.DEVICE_NOT_FOUND);

        return device;
    }

    public Bar findBarById(Long barId) {
        Bar bar = barRepository.findById(barId).orElse(null);
        if(bar == null)
            throw new BarNotFoundException(ResponseMessageConstants.BAR_NOT_FOUND);

        return bar;
    }

    public Device mapNewDeviceDtoToDevice(SaveNewDeviceDto dto) {
        Device device = new Device();

        device.setDeviceNumber(dto.getDeviceNumber());
        device.setActive(dto.isActive());
        device.setDescription(dto.getDescription());

        if(dto.getBarId() != 0) {
            Bar bar = findBarById(dto.getBarId());

            device.setBar(bar);

            bar.getDevices().add(device);
        }

        return device;
    }

    public Device mapUpdateDeviceDtoToDevice(UpdateDeviceDto dto) {
        Device device = findById(dto.getDeviceId());

        device.setDeviceNumber(dto.getDeviceNumber());
        device.setActive(dto.isActive());
        device.setDescription(dto.getDescription());

        return device;
    }
}
