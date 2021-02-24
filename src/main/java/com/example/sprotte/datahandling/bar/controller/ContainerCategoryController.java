package com.example.sprotte.datahandling.bar.controller;

import com.example.sprotte.datahandling.bar.services.ContainerCategoryService;
import com.example.sprotte.entity.ContainerCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/containerCategory")
public class ContainerCategoryController {

    @Autowired
    ContainerCategoryService containerCategoryService;

    @GetMapping("/getContainerCategories")
    public List<ContainerCategory> findContainerCategories() {
        return containerCategoryService.findContainerCategories();
    }

    @PostMapping("/saveContainerCategory/{containerCategory}")
    public ContainerCategory saveContainerCategory(@PathVariable("containerCategory") String containerCategory) {
        return containerCategoryService.saveContainerCategory(containerCategory);
    }

    @PutMapping("/updateContainerCategoryById/{id}")
    public ContainerCategory updateContainerCategoryById(@PathVariable("id") Long containerCategoryId, String containerCategory) {
        return containerCategoryService.updateContainerCategoryById(containerCategoryId, containerCategory);
    }

    @DeleteMapping("/deleteContainerCategoryById/{id}")
    public String deleteContainerCategoryById(@PathVariable("id") Long containerCategoryId) {
        return containerCategoryService.deleteContainerCategoryById(containerCategoryId);
    }

    @GetMapping("/findContainerCategoryById/{id}")
    public ContainerCategory findContainerCategoryById(@PathVariable("id") Long containerCategoryId) {
        return containerCategoryService.findContainerCategoryById(containerCategoryId);
    }

    @GetMapping("/findContainerCategoryByDescription/{description}")
    public ContainerCategory findContainerCategoryByDescription(@PathVariable("description") String containerCategory) {
        return containerCategoryService.findContainerCategoryByDescription(containerCategory);
    }
}
