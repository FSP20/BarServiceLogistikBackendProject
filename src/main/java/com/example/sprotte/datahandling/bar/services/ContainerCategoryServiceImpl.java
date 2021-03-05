package com.example.sprotte.datahandling.bar.services;

import com.example.sprotte.constants.ResponseMessageConstants;
import com.example.sprotte.datahandling.bar.repository.ContainerCategoryRepository;
import com.example.sprotte.entity.ContainerCategory;
import com.example.sprotte.errorhandling.Bar.ContainerCategoryNotFoundException;
import com.example.sprotte.errorhandling.Bar.IllegalContainerCategoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContainerCategoryServiceImpl implements ContainerCategoryService{

    @Autowired
    ContainerCategoryRepository containerCategoryRepository;


    @Override
    public List<ContainerCategory> findContainerCategories() {
        List<ContainerCategory> containerCategories = containerCategoryRepository.findAll();
        if (containerCategories.size() == 0)
            throw new ContainerCategoryNotFoundException(ResponseMessageConstants.CONTAINER_CATEGORY_NOT_FOUND);

        return containerCategories;
    }

    @Override
    public ContainerCategory saveContainerCategory(String containerCategoryDescription) {
        ContainerCategory containerCategory = containerCategoryRepository.findByDescription(containerCategoryDescription);
        if(containerCategory != null)
            throw new IllegalContainerCategoryException(ResponseMessageConstants.CONTAINER_CATEGORY_ALREADY_EXIST);

        return containerCategoryRepository.save(new ContainerCategory(containerCategoryDescription));
    }

    @Override
    public ContainerCategory updateContainerCategoryById(Long containerCategoryId, String containerCategoryDescription) {
        if(containerCategoryDescription == null)
            throw new ContainerCategoryNotFoundException(ResponseMessageConstants.CONTAINER_CATEGORY_IS_EMPTY);

        ContainerCategory containerCategory = findById(containerCategoryId);

        containerCategory.setDescription(containerCategoryDescription);

        return containerCategoryRepository.save(containerCategory);
    }

    @Override
    public String deleteContainerCategoryById(Long containerCategoryId) {
        ContainerCategory containerCategory = findById(containerCategoryId);

        containerCategoryRepository.deleteById(containerCategoryId);

        return ResponseMessageConstants.CONTAINER_CATEGORY_SUCCESSFULLY_DELETE;
    }

    @Override
    public ContainerCategory findContainerCategoryById(Long containerCategoryId) {
        ContainerCategory containerCategory = findById(containerCategoryId);

        return containerCategory;
    }

    @Override
    public ContainerCategory findContainerCategoryByDescription(String containerCategoryDescription) {
        ContainerCategory containerCategory = containerCategoryRepository.findByDescription(containerCategoryDescription);
        if(containerCategory == null)
            throw new ContainerCategoryNotFoundException(ResponseMessageConstants.CONTAINER_CATEGORY_NOT_FOUND);

        return containerCategory;
    }

    public ContainerCategory findById(Long containerCategoryId) {
        ContainerCategory containerCategory = containerCategoryRepository.findById(containerCategoryId).orElse(null);
        if (containerCategory == null)
            throw new ContainerCategoryNotFoundException(ResponseMessageConstants.CONTAINER_CATEGORY_NOT_FOUND);

        return containerCategory;
    }
}
