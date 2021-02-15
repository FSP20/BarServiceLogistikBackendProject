package com.example.sprotte.datahandling.bar.services;

import com.example.sprotte.entity.ContainerCategory;

import java.util.List;

public interface ContainerCategoryService {

    List<ContainerCategory> findContainerCategories();

    ContainerCategory saveContainerCategory(String containerCategory);

    ContainerCategory updateContainerCategoryById(Long containerCategoryId, String containerCategory);

    String deleteContainerCategoryById(Long containerCategoryId);

    ContainerCategory findContainerCategoryById(Long containerCategoryId);

    ContainerCategory findContainerCategoryByDescription(String containerCategory);
}
