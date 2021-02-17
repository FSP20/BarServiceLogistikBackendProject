package com.example.sprotte.datahandling.bar.services;

import com.example.sprotte.constants.ResponseMessageConstants;
import com.example.sprotte.datahandling.bar.repository.BarContainerRepository;
import com.example.sprotte.datahandling.bar.repository.BarSegmentRepository;
import com.example.sprotte.datahandling.bar.repository.ContainerCategoryRepository;
import com.example.sprotte.dto.bar.SaveNewBarContainerDto;
import com.example.sprotte.dto.bar.UpdateBarContainerDto;
import com.example.sprotte.entity.BarContainer;
import com.example.sprotte.entity.BarSegment;
import com.example.sprotte.entity.ContainerCategory;
import com.example.sprotte.errorhandling.Bar.BarContainerNotFoundException;
import com.example.sprotte.errorhandling.Bar.BarSegmentNotFoundException;
import com.example.sprotte.errorhandling.Bar.ContainerCategoryNotFoundException;
import com.example.sprotte.errorhandling.Bar.IllegalBarContainerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarContainerServiceImpl implements BarContainerService{

    @Autowired
    BarContainerRepository barContainerRepository;

    @Autowired
    BarSegmentRepository barSegmentRepository;

    @Autowired
    ContainerCategoryRepository containerCategoryRepository;


    @Override
    public List<BarContainer> getBarContainer() {
        List<BarContainer> barContainers = barContainerRepository.findAll();
        if(barContainers.size() == 0)
            throw new BarContainerNotFoundException(ResponseMessageConstants.BAR_CONTAINER_NOT_FOUND);

        return barContainers;
    }

    @Override
    public BarContainer saveBarContainer(SaveNewBarContainerDto dto) {
        if (dto.getBarContainerDescription() != null) {
            // Proof Bar Container Description already exist
            BarContainer barContainer = findBarContainerByDescription(dto.getBarContainerDescription());
            if (barContainer == null) {
                return barContainerRepository.save(mapNewBarContainerDtoToBarContainer(dto));
            } else {
                throw new IllegalBarContainerException(ResponseMessageConstants.BAR_CONTAINER_ALREADY_EXIST);
            }
        } else {
            throw new RuntimeException(ResponseMessageConstants.BAR_CONTAINER_IS_EMPTY);
        }
    }

    @Override
    public BarContainer findBarContainerById(Long barContainerId) {
        BarContainer barContainer = findById(barContainerId);
        if (barContainer == null)
            throw new BarContainerNotFoundException(ResponseMessageConstants.BAR_CONTAINER_NOT_FOUND);

        return barContainer;
    }

    @Override
    public BarContainer findBarContainerByDescription(String barContainerDescription) {
        BarContainer barContainer = findBarContainerByDescription(barContainerDescription);
        if (barContainer == null)
            throw new BarContainerNotFoundException(ResponseMessageConstants.BAR_CONTAINER_NOT_FOUND);

        return null;
    }

    @Override
    public BarContainer updateBarContainer(UpdateBarContainerDto dto) {
        if(dto.getBarContainerDescription() != null) {
            return barContainerRepository.save(mapUpdateBarContainerDtoToBarContainer(dto));
        } else {
            throw new RuntimeException(ResponseMessageConstants.BAR_CONTAINER_IS_EMPTY);
        }
    }

    @Override
    public BarContainer updateBarContainerDescription(Long barContainerId, String description) {
        BarContainer barContainer = findById(barContainerId);
        if (barContainer == null)
            throw new BarContainerNotFoundException(ResponseMessageConstants.BAR_CONTAINER_NOT_FOUND);
        barContainer.setDescription(description);

        return barContainerRepository.save(barContainer);
    }

    @Override
    public String deleteBarContainerById(Long barContainerId) {
        BarContainer barContainer = findById(barContainerId);
        if (barContainer == null)
            throw new BarContainerNotFoundException(ResponseMessageConstants.BAR_CONTAINER_NOT_FOUND);

        barContainerRepository.deleteById(barContainerId);
        return ResponseMessageConstants.BAR_CONTAINER_SUCCESSFULLY_DELETE;
    }

    @Override
    public BarContainer switchBarSegmentFromBarContainer(Long barContainerId, Long barSegmentId) {
        BarSegment barSegment = findBarSegmentById(barSegmentId);
        if(barSegment == null)
            throw new BarSegmentNotFoundException(ResponseMessageConstants.BAR_SEGMENT_NOT_FOUND);

        BarContainer barContainer = findById(barContainerId);
        if (barContainer == null)
            throw new BarContainerNotFoundException(ResponseMessageConstants.BAR_CONTAINER_NOT_FOUND);

        // Set Parent in Child
        barContainer.setBarSegment(barSegment);

        // Set Child in Parent
        if (!barSegment.getBarContainers().contains(barContainer)) {
            barSegment.getBarContainers().add(barContainer);
        }

        return barContainerRepository.save(barContainer);
    }

    @Override
    public BarContainer switchContainerCategoryFromBarContainer(Long barContainerId, Long containerCategoryId) {
        ContainerCategory containerCategory  = findContainerCategoryById(containerCategoryId);
        if(containerCategory == null)
            throw new ContainerCategoryNotFoundException(ResponseMessageConstants.CONTAINER_CATEGORY_NOT_FOUND);

        BarContainer barContainer = findById(barContainerId);
        if (barContainer == null)
            throw new BarContainerNotFoundException(ResponseMessageConstants.BAR_CONTAINER_NOT_FOUND);

        // Set Parent in Child (Uni-directional)  
        barContainer.setContainerCategory(containerCategory);

        return barContainerRepository.save(barContainer);
    }

    public BarContainer findByDescription(String barContainerDescription) {
        return barContainerRepository.findByDescription(barContainerDescription);
    }

    public BarContainer findById(Long barContainerId) {
        return barContainerRepository.findById(barContainerId).orElse(null);
    }

    public BarSegment findBarSegmentById(Long barSegmentId) {
        return barSegmentRepository.findById(barSegmentId).orElse(null);
    }

    public ContainerCategory findContainerCategoryById(Long containerCategoryId) {
        return containerCategoryRepository.findById(containerCategoryId).orElse(null);
    }


    public BarContainer mapNewBarContainerDtoToBarContainer(SaveNewBarContainerDto dto) {
        BarContainer barContainer = new BarContainer();

        barContainer.setDescription(dto.getBarContainerDescription());

        if(dto.getBarSegmentId() != 0) {
            BarSegment barSegment = findBarSegmentById(dto.getBarSegmentId());
            if(barSegment == null)
                throw new BarSegmentNotFoundException(ResponseMessageConstants.BAR_SEGMENT_NOT_FOUND);

            // Set Parent in Child
            barContainer.setBarSegment(barSegment);

            // Set Child in Parent
            barSegment.getBarContainers().add(barContainer);
        } else {
            throw new IllegalBarContainerException(ResponseMessageConstants.BAR_CONTAINER_MUST_ASSIGNED_TO_BAR_SEGMENT);
        }

        if(dto.getContainerCategoryId() != 0) {
            ContainerCategory containerCategory = findContainerCategoryById(dto.getContainerCategoryId());
            if (containerCategory == null)
                throw new ContainerCategoryNotFoundException(ResponseMessageConstants.CONTAINER_CATEGORY_NOT_FOUND);

            // Set Parent in Child (Uni-Directional)
            barContainer.setContainerCategory(containerCategory);
        } else {
            throw new IllegalBarContainerException(ResponseMessageConstants.BAR_CONTAINER_MUST_ASSIGNED_TO_CONTAINER_CATEGORY);
        }

        return barContainer;
    }


    public BarContainer mapUpdateBarContainerDtoToBarContainer(UpdateBarContainerDto dto) {
        BarContainer barContainer = findById(dto.getBarContainerId());
        if (barContainer == null)
            throw new BarContainerNotFoundException(ResponseMessageConstants.BAR_CONTAINER_NOT_FOUND);

        barContainer.setDescription(dto.getBarContainerDescription());

        if (dto.getBarSegmentId() != 0) {
            BarSegment barSegment = findBarSegmentById(dto.getBarSegmentId());
            if (barSegment == null)
                throw new BarSegmentNotFoundException(ResponseMessageConstants.BAR_SEGMENT_NOT_FOUND);

            // Set Parent in Child
            barContainer.setBarSegment(barSegment);
        }

        if (dto.getContainerCategoryId() != 0) {
            ContainerCategory containerCategory = findContainerCategoryById(dto.getContainerCategoryId());
            if (containerCategory == null)
                throw new ContainerCategoryNotFoundException(ResponseMessageConstants.CONTAINER_CATEGORY_NOT_FOUND);

            // Set Parent in Child
            barContainer.setContainerCategory(containerCategory);
        }

        return barContainer;
    }
}
