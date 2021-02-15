package com.example.sprotte.device.service;

import com.example.sprotte.datahandling.device.repository.DeviceRepository;
import com.example.sprotte.datahandling.device.service.DeviceServiceImpl;
import com.example.sprotte.entity.Device;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class DeviceServiceImplTest {

    @Mock
    DeviceRepository deviceRepository;

    @InjectMocks
    DeviceServiceImpl service;

    @Test
    void findByDeviceNumber() {
        int deviceNumber = 1234;
        Device device = new Device();
        given(deviceRepository.findByDeviceNumber(deviceNumber)).willReturn(device);

        Device foundDevice = service.findByDeviceNumber(deviceNumber);

        assertThat(foundDevice).isNotNull();
        then(deviceRepository).should().findByDeviceNumber(anyInt());
    }

    @Test
    void findDeviceByDescription() {
        String deviceDescription = "Android Tablet";
        Device device = new Device();
        given(deviceRepository.findByDescription(deviceDescription)).willReturn(device);

        Device foundDevice = service.findDeviceByDescription(deviceDescription);

        assertThat(foundDevice).isNotNull();
        then(deviceRepository).should().findByDescription(anyString());
    }
}