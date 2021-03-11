package com.example.sprotte.datahandling.bar.services;

import com.example.sprotte.constants.ResponseMessageConstants;
import com.example.sprotte.datahandling.bar.repository.BarRepository;
import com.example.sprotte.datahandling.bar.repository.BarSegmentRepository;
import com.example.sprotte.datahandling.device.repository.DeviceRepository;
import com.example.sprotte.dto.bar.SaveNewBarDto;
import com.example.sprotte.entity.Bar;
import com.example.sprotte.entity.BarSegment;
import com.example.sprotte.entity.Device;
import com.example.sprotte.errorhandling.Bar.BarNotFoundException;
import com.example.sprotte.errorhandling.Bar.BarSegmentNotFoundException;
import com.example.sprotte.errorhandling.Bar.IllegalBarException;
import com.example.sprotte.errorhandling.Employee.DeviceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarServiceImpl implements BarService{

    @Autowired
    BarRepository barRepository;

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    BarSegmentRepository barSegmentRepository;

    @Override
    public List<Bar> getBars() {
        List<Bar> bars = barRepository.findAll();
        if(bars.size() == 0)
            throw new BarNotFoundException(ResponseMessageConstants.BAR_NOT_FOUND);

        return bars;
    }

    @Override
    public Bar saveBar(SaveNewBarDto dto) {
        if(dto.getName() == null)
            throw new RuntimeException(ResponseMessageConstants.BAR_DTO_IS_EMPTY);

        // Proof Bar Name already exist
        Bar testBar = barRepository.findByName(dto.getName());
        if(testBar != null)
            throw new IllegalBarException(ResponseMessageConstants.BAR_ALREADY_EXIST);

        return barRepository.save(mapNewBarDtoToEntityObject(dto));
    }

    @Override
    public Bar findBarById(Long barId) {
        Bar bar = findById(barId);

        return bar;
    }

    @Override
    public Bar findBarByName(String barName) {
        Bar bar = barRepository.findByName(barName);
        if(bar == null)
            throw new BarNotFoundException(ResponseMessageConstants.BAR_NOT_FOUND);

        return bar;
    }

    @Override
    public Bar updateBarById(Long barId, String barName) {
        if (barName == null)
            throw new RuntimeException(ResponseMessageConstants.BAR_IS_EMPTY);

        Bar bar = findById(barId);

        bar.setName(barName);

        return barRepository.save(bar);
    }

    @Override
    public String deleteBarById(Long barId) {
        findById(barId);

        barRepository.deleteById(barId);

        return ResponseMessageConstants.BAR_SUCCESSFULLY_DELETE;
    }

    @Override
    public Bar addDeviceToBar(Long barId, Long deviceId) {
        Bar bar = findById(barId);

        Device device = findDeviceById(deviceId);

        //Set Child in Parent
        if (!bar.getDevices().contains(device)) {
            bar.getDevices().add(device);
        }

        //Set Parent in Child Entity
        device.setBar(bar);

        return barRepository.save(bar);
    }

    @Override
    public Bar removeDeviceFromBar(Long barId, Long deviceId) {
        Bar bar = findById(barId);

        Device device = findDeviceById(deviceId);

        // Update Parent
        bar.getDevices().removeIf(tempDevice -> tempDevice.getId() == device.getId());

        // Update Child
        device.setBar(null);

        return barRepository.save(bar);
    }

    @Override
    public Bar switchBarSegmentToBar(Long barId, Long barSegmentId) {
        Bar bar = findById(barId);

        BarSegment barSegment = findBarSegmentById(barSegmentId);

        // Set Child in Parent
        if (!bar.getBarSegments().contains(barSegment)) {
            bar.getBarSegments().add(barSegment);
        }

        // Set Parent in Child Entity
        barSegment.setBar(bar);

        return barRepository.save(bar);
    }


    public Bar findById(Long barId) {
        Bar bar = barRepository.findById(barId).orElse(null);
        if (bar == null)
            throw new BarNotFoundException(ResponseMessageConstants.BAR_NOT_FOUND);

        return bar;
    }

    public Device findDeviceById(Long deviceId) {
        Device device = deviceRepository.findById(deviceId).orElse(null);
        if (device == null)
            throw new DeviceNotFoundException(ResponseMessageConstants.DEVICE_NOT_FOUND);

        return device;
    }

    public BarSegment findBarSegmentById(Long barSegmentId){
        BarSegment barSegment = barSegmentRepository.findById(barSegmentId).orElse(null);
        if (barSegment == null)
            throw new BarSegmentNotFoundException(ResponseMessageConstants.BAR_SEGMENT_NOT_FOUND);

        return barSegment;
    }

    public Bar mapNewBarDtoToEntityObject(SaveNewBarDto dto) {
        Bar bar = new Bar();

        bar.setName(dto.getName());

        if(dto.getDeviceId().size() > 0) {
            for(Long deviceId : dto.getDeviceId()){
                Device device = findDeviceById(deviceId);

                bar.getDevices().add(device);

                device.setBar(bar);
            }

            return bar;
        }

        return bar;
    }
}
