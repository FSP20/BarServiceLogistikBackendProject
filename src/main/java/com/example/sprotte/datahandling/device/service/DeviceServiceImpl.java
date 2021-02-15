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
public class DeviceServiceImpl implements DeviceService{

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
        if(dto.getDeviceNumber() != 0) {
            // Proof deviceNumber already exist
            Device testDevice = findByDeviceNumber(dto.getDeviceNumber());
            if(testDevice == null) {
                return deviceRepository.save(mapNewDeviceDtoToDevice(dto));
            } else {
                throw new IllegalDeviceException(ResponseMessageConstants.DEVICE_ALREADY_EXIST);
            }
        } else {
            throw new RuntimeException(ResponseMessageConstants.DEVICE_IS_EMPTY);
        }
    }

    @Override
    public Device findDeviceById(Long deviceId) {
        Device device = findById(deviceId);
        if(device == null)
            throw new DeviceNotFoundException(ResponseMessageConstants.DEVICE_NOT_FOUND);
        return device;
    }

    @Override
    public Device findByDeviceName(String deviceDescription) {
        Device device = findDeviceByDescription(deviceDescription);
        if(device == null)
            throw new DeviceNotFoundException(ResponseMessageConstants.DEVICE_NOT_FOUND);
        return device;
    }

    @Override
    public Device updateDevice(UpdateDeviceDto dto) {
        if(dto.getDeviceNumber() != 0) {
            return deviceRepository.save(mapUpdateDeviceDtoToDevice(dto));
        } else {
            throw new RuntimeException(ResponseMessageConstants.DEVICE_IS_EMPTY);
        }
    }

    @Override
    public String deleteDeviceById(Long deviceId) {
        Device device = findById(deviceId);
        if(device != null) {
            deviceRepository.deleteById(deviceId);
            return ResponseMessageConstants.DEVICE_SUCCESSFULLY_DELETE;
        } else {
            throw new DeviceNotFoundException(ResponseMessageConstants.DEVICE_NOT_FOUND);
        }
    }

    @Override
    public Device addBarToDevice(Long deviceId, Long barId) {
        Bar bar = findBarById(barId);
        if (bar == null)
            throw new BarNotFoundException(ResponseMessageConstants.BAR_NOT_FOUND);

        Device device = findById(deviceId);
        if(device == null)
            throw new DeviceNotFoundException(ResponseMessageConstants.DEVICE_NOT_FOUND);

        device.setBar(bar);

        if (!bar.getDevices().contains(device)) {
            bar.getDevices().add(device);
        }

        return deviceRepository.save(device);
    }

    @Override
    public Device removeBarFromDevice(Long deviceId, Long barId) {
        Bar bar = findBarById(barId);
        if (bar == null)
            throw new BarNotFoundException(ResponseMessageConstants.BAR_NOT_FOUND);

        Device device = findById(deviceId);
        if(device == null)
            throw new DeviceNotFoundException(ResponseMessageConstants.DEVICE_NOT_FOUND);

        device.setBar(null);

        bar.getDevices().removeIf(tempDevice -> tempDevice.getId() == device.getId());

        return deviceRepository.save(device);
    }

    public Device findByDeviceNumber(int deviceNumber) {
        return deviceRepository.findByDeviceNumber(deviceNumber);
    }

    public Device findById(Long deviceId) {
        return deviceRepository.findById(deviceId).orElse(null);
    }

    public Device findDeviceByDescription(String deviceDescription) {
        return deviceRepository.findByDescription(deviceDescription);
    }

    public Bar findBarById(Long barId) {
        return barRepository.findById(barId).orElse(null);
    }

    public Device mapNewDeviceDtoToDevice(SaveNewDeviceDto dto) {
        Device device = new Device();

        device.setDeviceNumber(dto.getDeviceNumber());
        device.setActive(dto.isActive());
        device.setDescription(dto.getDescription());

        if(dto.getBarId() != 0) {
            Bar bar = findBarById(dto.getBarId());
            if(bar == null)
                throw new BarNotFoundException(ResponseMessageConstants.BAR_NOT_FOUND);

            device.setBar(bar);

            bar.getDevices().add(device);
        }

        return device;
    }

    public Device mapUpdateDeviceDtoToDevice(UpdateDeviceDto dto) {
        Device device = findById(dto.getDeviceId());
        if(device == null)
            throw new DeviceNotFoundException(ResponseMessageConstants.DEVICE_NOT_FOUND);

        device.setDeviceNumber(dto.getDeviceNumber());
        device.setActive(dto.isActive());
        device.setDescription(dto.getDescription());

        return device;
    }
}
