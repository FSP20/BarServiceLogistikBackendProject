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
        if(dto.getName() != null) {
            // Proof Bar Name already exist
            Bar testBar = findByName(dto.getName());
            if(testBar == null) {
                return barRepository.save(mapNewBarDtoToEntityObject(dto));
            } else {
                throw new IllegalBarException(ResponseMessageConstants.BAR_ALREADY_EXIST);
            }
        } else {
            throw new RuntimeException(ResponseMessageConstants.BAR_DTO_IS_EMPTY);
        }
    }

    @Override
    public Bar findBarById(Long barId) {
        Bar bar = findById(barId);
        if(bar == null)
            throw new BarNotFoundException(ResponseMessageConstants.BAR_NOT_FOUND);

        return bar;
    }

    @Override
    public Bar findBarByName(String barName) {
        Bar bar = findByName(barName);
        if(bar == null)
            throw new BarNotFoundException(ResponseMessageConstants.BAR_NOT_FOUND);

        return bar;
    }

    @Override
    public Bar updateBarById(Long barId, String barName) {
        if (barName != null) {
            Bar bar = findById(barId);
            if(bar == null)
                throw new BarNotFoundException(ResponseMessageConstants.BAR_NOT_FOUND);

            bar.setName(barName);

            return barRepository.save(bar);
        } else {
            throw new RuntimeException(ResponseMessageConstants.BAR_IS_EMPTY);
        }
    }

    @Override
    public String deleteBarById(Long barId) {
        Bar bar = findById(barId);
        if(bar != null) {
            barRepository.deleteById(barId);
            return ResponseMessageConstants.BAR_SUCCESSFULLY_DELETE;
        } else {
            throw new BarNotFoundException(ResponseMessageConstants.BAR_NOT_FOUND);
        }
    }

    @Override
    public Bar addDeviceToBar(Long barId, Long deviceId) {
        Bar bar = findById(barId);
        if (bar == null)
            throw new BarNotFoundException(ResponseMessageConstants.BAR_NOT_FOUND);

        Device device = findDeviceById(deviceId);
        if(device == null)
            throw new DeviceNotFoundException(ResponseMessageConstants.DEVICE_NOT_FOUND);

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
        if (bar == null)
            throw new BarNotFoundException(ResponseMessageConstants.BAR_NOT_FOUND);

        Device device = findDeviceById(deviceId);
        if(device == null)
            throw new DeviceNotFoundException(ResponseMessageConstants.DEVICE_NOT_FOUND);

        // Update Parent
        bar.getDevices().removeIf(tempDevice -> tempDevice.getId() == device.getId());

        // Update Child
        device.setBar(null);

        return barRepository.save(bar);
    }

    @Override
    public Bar switchBarSegmentToBar(Long barId, Long barSegmentId) {
        Bar bar = findById(barId);
        if (bar == null)
            throw new BarNotFoundException(ResponseMessageConstants.BAR_NOT_FOUND);

        BarSegment barSegment = findBarSegmentById(barSegmentId);
        if (barSegment == null)
            throw new BarSegmentNotFoundException(ResponseMessageConstants.BAR_SEGMENT_NOT_FOUND);

        // Set Child in Parent
        if (!bar.getBarSegments().contains(barSegment)) {
            bar.getBarSegments().add(barSegment);
        }

        // Set Parent in Child Entity
        barSegment.setBar(bar);

        return barRepository.save(bar);
    }


    public Bar findById(Long barId) {
        return barRepository.findById(barId).orElse(null);
    }

    public Bar findByName(String barName) {
        return barRepository.findByName(barName);
    }

    public Device findDeviceById(Long deviceId) {
        return deviceRepository.findById(deviceId).orElse(null);
    }

    public BarSegment findBarSegmentById(Long barSegmentId){
        return barSegmentRepository.findById(barSegmentId).orElse(null);
    }

    public Bar mapNewBarDtoToEntityObject(SaveNewBarDto dto) {
        Bar bar = new Bar();

        bar.setName(dto.getName());

        if(dto.getDeviceId().size() > 0) {
            for(Long deviceId : dto.getDeviceId()){
                Device device = findDeviceById(deviceId);

                if (device == null)
                    throw new DeviceNotFoundException(ResponseMessageConstants.DEVICE_NOT_FOUND);

                bar.getDevices().add(device);

                device.setBar(bar);
            }

            return bar;
        }

        return bar;
    }
}
