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
        if (dto.getBarContainerDescription() == null)
            throw new RuntimeException(ResponseMessageConstants.BAR_CONTAINER_IS_EMPTY);

        // Proof Bar Container Description already exist
        findByDescription(dto.getBarContainerDescription());

        return barContainerRepository.save(mapNewBarContainerDtoToBarContainer(dto));
    }

    @Override
    public BarContainer findBarContainerById(Long barContainerId) {
        BarContainer barContainer = findById(barContainerId);

        return barContainer;
    }

    @Override
    public BarContainer findBarContainerByDescription(String barContainerDescription) {
        BarContainer barContainer = barContainerRepository.findByDescription(barContainerDescription);
        if (barContainer == null)
            throw new BarContainerNotFoundException(ResponseMessageConstants.BAR_CONTAINER_NOT_FOUND);

        return barContainer;
    }

    @Override
    public BarContainer updateBarContainer(UpdateBarContainerDto dto) {
        if(dto.getBarContainerDescription() == null)
            throw new RuntimeException(ResponseMessageConstants.BAR_CONTAINER_IS_EMPTY);

        return barContainerRepository.save(mapUpdateBarContainerDtoToBarContainer(dto));
    }

    @Override
    public BarContainer updateBarContainerDescription(Long barContainerId, String description) {
        BarContainer barContainer = findById(barContainerId);

        barContainer.setDescription(description);

        return barContainerRepository.save(barContainer);
    }

    @Override
    public String deleteBarContainerById(Long barContainerId) {
        BarContainer barContainer = findById(barContainerId);

        barContainerRepository.deleteById(barContainerId);

        return ResponseMessageConstants.BAR_CONTAINER_SUCCESSFULLY_DELETE;
    }

    @Override
    public BarContainer switchBarSegmentFromBarContainer(Long barContainerId, Long barSegmentId) {
        BarSegment barSegment = findBarSegmentById(barSegmentId);

        BarContainer barContainer = findById(barContainerId);

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

        BarContainer barContainer = findById(barContainerId);

        // Set Parent in Child (Uni-directional)  
        barContainer.setContainerCategory(containerCategory);

        return barContainerRepository.save(barContainer);
    }

    public void findByDescription(String barContainerDescription) {
        BarContainer barContainer = barContainerRepository.findByDescription(barContainerDescription);
        if (barContainer != null)
            throw new IllegalBarContainerException(ResponseMessageConstants.BAR_CONTAINER_ALREADY_EXIST);
    }

    public BarContainer findById(Long barContainerId) {
        BarContainer barContainer = barContainerRepository.findById(barContainerId).orElse(null);
        if (barContainer == null)
            throw new BarContainerNotFoundException(ResponseMessageConstants.BAR_CONTAINER_NOT_FOUND);

        return barContainer;
    }

    public BarSegment findBarSegmentById(Long barSegmentId) {
        BarSegment barSegment = barSegmentRepository.findById(barSegmentId).orElse(null);
        if(barSegment == null)
            throw new BarSegmentNotFoundException(ResponseMessageConstants.BAR_SEGMENT_NOT_FOUND);

        return barSegment;
    }

    public ContainerCategory findContainerCategoryById(Long containerCategoryId) {
        ContainerCategory containerCategory = containerCategoryRepository.findById(containerCategoryId).orElse(null);
        if (containerCategory == null)
            throw new ContainerCategoryNotFoundException(ResponseMessageConstants.CONTAINER_CATEGORY_NOT_FOUND);

        return containerCategory;
    }


    public BarContainer mapNewBarContainerDtoToBarContainer(SaveNewBarContainerDto dto) {
        BarContainer barContainer = new BarContainer();

        barContainer.setDescription(dto.getBarContainerDescription());

        if(dto.getBarSegmentId() == 0)
            throw new IllegalBarContainerException(ResponseMessageConstants.BAR_CONTAINER_MUST_ASSIGNED_TO_BAR_SEGMENT);

        BarSegment barSegment = findBarSegmentById(dto.getBarSegmentId());

        // Set Parent in Child
        barContainer.setBarSegment(barSegment);

        // Set Child in Parent
        barSegment.getBarContainers().add(barContainer);


        if(dto.getContainerCategoryId() == 0)
            throw new IllegalBarContainerException(ResponseMessageConstants.BAR_CONTAINER_MUST_ASSIGNED_TO_CONTAINER_CATEGORY);

        ContainerCategory containerCategory = findContainerCategoryById(dto.getContainerCategoryId());

        // Set Parent in Child (Uni-Directional)
        barContainer.setContainerCategory(containerCategory);

        return barContainer;
    }


    public BarContainer mapUpdateBarContainerDtoToBarContainer(UpdateBarContainerDto dto) {
        BarContainer barContainer = findById(dto.getBarContainerId());

        barContainer.setDescription(dto.getBarContainerDescription());

        if (dto.getBarSegmentId() != 0) {
            BarSegment barSegment = findBarSegmentById(dto.getBarSegmentId());

            // Set Parent in Child
            barContainer.setBarSegment(barSegment);
        }

        if (dto.getContainerCategoryId() != 0) {
            ContainerCategory containerCategory = findContainerCategoryById(dto.getContainerCategoryId());

            // Set Parent in Child
            barContainer.setContainerCategory(containerCategory);
        }

        return barContainer;
    }
}
