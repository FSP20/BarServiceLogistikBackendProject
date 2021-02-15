package com.example.sprotte.datahandling.bar.services;

import com.example.sprotte.constants.ResponseMessageConstants;
import com.example.sprotte.datahandling.bar.repository.BarContainerRepository;
import com.example.sprotte.datahandling.bar.repository.BarRepository;
import com.example.sprotte.datahandling.bar.repository.BarSegmentRepository;
import com.example.sprotte.dto.bar.SaveNewBarSegmentDto;
import com.example.sprotte.dto.bar.UpdateBarSegmentDto;
import com.example.sprotte.entity.Bar;
import com.example.sprotte.entity.BarContainer;
import com.example.sprotte.entity.BarSegment;
import com.example.sprotte.errorhandling.Bar.BarContainerNotFoundException;
import com.example.sprotte.errorhandling.Bar.BarNotFoundException;
import com.example.sprotte.errorhandling.Bar.BarSegmentNotFoundException;
import com.example.sprotte.errorhandling.Bar.IllegalBarSegmentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarSegmentServiceImpl implements BarSegmentService {

    @Autowired
    BarSegmentRepository barSegmentRepository;

    @Autowired
    BarRepository barRepository;

    @Autowired
    BarContainerRepository barContainerRepository;

    @Override
    public List<BarSegment> getBarSegments() {
        List<BarSegment> barSegments = barSegmentRepository.findAll();
        if (barSegments.size() == 0)
            throw new BarSegmentNotFoundException(ResponseMessageConstants.BAR_SEGMENT_NOT_FOUND);

        return barSegments;
    }

    @Override
    public BarSegment saveBarSegment(SaveNewBarSegmentDto dto) {
        if(dto.getBarSegmentDescription() != null) {
            // Proof Bar Segment Description already exist
            BarSegment barSegment = findByDescription(dto.getBarSegmentDescription());
            if(barSegment == null) {
                return barSegmentRepository.save(mapNewBarSegmentDtoToBarSegment(dto));
            } else {
                throw new IllegalBarSegmentException(ResponseMessageConstants.BAR_SEGMENT_ALREADY_EXIST);
            }
        } else {
            throw new RuntimeException(ResponseMessageConstants.BAR_SEGMENT_IS_EMPTY);
        }
    }

    @Override
    public BarSegment findBarSegmentById(Long barSegmentId) {
        BarSegment barSegment = findById(barSegmentId);
        if(barSegment == null)
            throw new BarSegmentNotFoundException(ResponseMessageConstants.BAR_SEGMENT_NOT_FOUND);

        return barSegment;
    }

    @Override
    public BarSegment findBarSegmentByDescription(String barSegmentDescription) {
        BarSegment barSegment = findByDescription(barSegmentDescription);
        if(barSegment == null)
            throw new BarSegmentNotFoundException(ResponseMessageConstants.BAR_SEGMENT_NOT_FOUND);

        return barSegment;
    }

    @Override
    public BarSegment updateBarSegment(UpdateBarSegmentDto dto) {
        if(dto.getBarSegmentDescription() != null) {
            return barSegmentRepository.save(mapUpdateBarSegmentDtoToBarSegment(dto));
        } else {
            throw new RuntimeException(ResponseMessageConstants.BAR_SEGMENT_IS_EMPTY);
        }
    }

    @Override
    public BarSegment updateBarSegmentDescription(Long barSegmentId, String description) {
        BarSegment barSegment = findById(barSegmentId);
        if(barSegment == null)
            throw new BarSegmentNotFoundException(ResponseMessageConstants.BAR_SEGMENT_NOT_FOUND);

        if(description != null) {
            barSegment.setDescription(description);
            return barSegmentRepository.save(barSegment);
        } else {
            throw new BarSegmentNotFoundException(ResponseMessageConstants.BAR_SEGMENT_IS_EMPTY);
        }
    }

    @Override
    public String deleteBarSegmentById(Long barSegmentId) {
        BarSegment barSegment = findById(barSegmentId);
        if(barSegment != null) {
            barSegmentRepository.deleteById(barSegmentId);
            return ResponseMessageConstants.BAR_SEGMENT_SUCCESSFULLY_DELETE;
        } else {
            throw new BarSegmentNotFoundException(ResponseMessageConstants.BAR_SEGMENT_NOT_FOUND);
        }
    }

    @Override
    public BarSegment switchBarFromBarSegment(Long barSegmentId, Long barId) {
        Bar bar = findBarById(barId);
        if (bar == null)
            throw new BarNotFoundException(ResponseMessageConstants.BAR_NOT_FOUND);

        BarSegment barSegment = findById(barSegmentId);
        if(barSegment == null)
            throw new BarSegmentNotFoundException(ResponseMessageConstants.BAR_SEGMENT_NOT_FOUND);

        // Set Parent in Child
        barSegment.setBar(bar);

        // Set Child in Parent
        if (!bar.getBarSegments().contains(barSegment)){
            bar.getBarSegments().add(barSegment);
        }

        return barSegmentRepository.save(barSegment);
    }

    @Override
    public BarSegment switchBarContainerToBarSegment(Long barSegmentId, Long barContainerId) {
        BarSegment barSegment = findById(barSegmentId);
        if (barSegment == null)
            throw new BarSegmentNotFoundException(ResponseMessageConstants.BAR_SEGMENT_NOT_FOUND);

        BarContainer barContainer = findBarContainerById(barContainerId);
        if (barContainer == null)
            throw new BarContainerNotFoundException(ResponseMessageConstants.BAR_CONTAINER_NOT_FOUND);

        // Set Child in Parent
        if (!barSegment.getBarContainers().contains(barContainer)) {
            barSegment.getBarContainers().add(barContainer);
        }

        // Set Parent in Child entity
        barContainer.setBarSegment(barSegment);

        return barSegmentRepository.save(barSegment);
    }

    public BarSegment findByDescription(String barSegmentDescription) {
        return barSegmentRepository.findByDescription(barSegmentDescription);
    }

    public BarSegment findById(Long barSegmentId) {
        return barSegmentRepository.findById(barSegmentId).orElse(null);
    }

    public Bar findBarById(Long barId) {
        return barRepository.findById(barId).orElse(null);
    }

    public BarContainer findBarContainerById(Long barContainerId) {
        return barContainerRepository.findById(barContainerId).orElse(null);
    }

    public BarSegment mapNewBarSegmentDtoToBarSegment(SaveNewBarSegmentDto dto) {
        BarSegment barSegment = new BarSegment();

        barSegment.setDescription(dto.getBarSegmentDescription());
        barSegment.setCategory(dto.getBarSegmentCategory());

        if(dto.getBarId() != 0) {
            Bar bar = findBarById(dto.getBarId());
            if(bar == null)
                throw new BarNotFoundException(ResponseMessageConstants.BAR_NOT_FOUND);

            // Set Parent in Child
            barSegment.setBar(bar);

            // Set Child in Parent
            bar.getBarSegments().add(barSegment);
        } else {
            throw new IllegalBarSegmentException(ResponseMessageConstants.BAR_SEGMENT_MUST_ASSIGNED_TO_BAR);
        }

        return barSegment;
    }

    public BarSegment mapUpdateBarSegmentDtoToBarSegment(UpdateBarSegmentDto dto) {
        BarSegment barSegment = findById(dto.getBarSegmentId());
        if(barSegment == null)
            throw new BarSegmentNotFoundException(ResponseMessageConstants.BAR_SEGMENT_NOT_FOUND);

        barSegment.setDescription(dto.getBarSegmentDescription());
        barSegment.setCategory(dto.getBarSegmentCategory());

        if(dto.getBarId() != 0) {
            Bar bar = findBarById(dto.getBarId());
            if(bar == null)
                throw new BarNotFoundException(ResponseMessageConstants.BAR_NOT_FOUND);

            // Set Parent in Child
            barSegment.setBar(bar);
        }

        return barSegment;
    }
}
