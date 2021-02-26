package com.example.sprotte.datahandling.employee.services;

import com.example.sprotte.datahandling.device.repository.DeviceRepository;
import com.example.sprotte.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeHelperClass {

    @Autowired
    DeviceRepository deviceRepository;

    // Es muss geprüft werden ob Employee schon auf einem anderen Device active ist. Falls ja wird er auf diesem Gerät
    // als inactive gesetzt. Da ein Employee nur auf einem Device active sein kann
    public void setEmployeeInactiveOnOtherDevice(Long employeeId) {
        List<Device> devices = deviceRepository.findByEmployeeId(employeeId);

        if(devices.size() > 0) {
            for(Device device : devices) {
                if (device.isActive()){
                    device.setActive(false);
                    deviceRepository.save(device);
                }
            }
        }
    }
}
