package com.example.sprotte.datahandling.storage.controller;

import com.example.sprotte.datahandling.storage.service.StorageService;
import com.example.sprotte.entity.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    StorageService storageService;

    @GetMapping("/getStorages")
    public List<Storage> getStorages(){
        return storageService.getStorages();
    }

    @PostMapping("/saveStorage/{storage}")
    public Storage saveStorage(@PathVariable("storage") String storage) {
        return storageService.saveStorage(storage);
    }

    @GetMapping("/getStorage/{storageid}")
    public Storage findStorageById(@PathVariable("storageid") Long storageId) {
        return storageService.findStorageById(storageId);
    }

    @PutMapping("/updateStorage/{id}/{storageName}")
    public Storage updateStorage(@PathVariable("id") Long storageId, @PathVariable("storageName") String storageName) {
        return storageService.updateStorageById(storageId, storageName);
    }

    @DeleteMapping("/deleteStorage/{id}")
    public String deleteStorageById(@PathVariable("id") Long storageId) {
        return storageService.deleteStorageById(storageId);
    }
}
