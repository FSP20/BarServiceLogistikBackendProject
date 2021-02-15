package com.example.sprotte.datahandling.device.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sprotte.entity.Device;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long>{

    Device findByDeviceNumber(int deviceNumber);

    Device findByDescription(String description);

    List<Device> findByEmployeeId(Long employeeId);

    Device findByIdAndEmployeeId(Long deviceId, Long employeeId);
}
