package com.example.sprotte.datahandling.storage.service;

import com.example.sprotte.constants.ResponseMessageConstants;
import com.example.sprotte.datahandling.storage.repository.StorageRepository;
import com.example.sprotte.entity.Storage;
import com.example.sprotte.errorhandling.storage.IllegalStorageException;
import com.example.sprotte.errorhandling.storage.StorageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    StorageRepository storageRepository;

    @Override
    public List<Storage> getStorages() {
        List<Storage> storages = storageRepository.findAll();
        if(storages.size() == 0)
            throw new StorageNotFoundException(ResponseMessageConstants.STORAGE_NOT_FOUND);

        return storages;
    }

    @Override
    public Storage saveStorage(String storageDescription) {
        if(storageDescription == null)
            throw new RuntimeException(ResponseMessageConstants.STORAGE_IS_EMPTY);

        Storage storage = findStorageByDescription(storageDescription);
        if(storage != null)
            throw new IllegalStorageException(ResponseMessageConstants.STORAGE_ALREADY_EXIST);

        storage = new Storage();
        storage.setDescription(storageDescription);

        return storageRepository.save(storage);
    }

    @Override
    public Storage findStorageById(Long storageId) {
        Storage storage = findById(storageId);

        return storage;
    }

    @Override
    public Storage updateStorageById(Long storageId, String storageDescription) {
        if(storageDescription == null)
            throw new RuntimeException(ResponseMessageConstants.STORAGE_IS_EMPTY);

        Storage storage = findById(storageId);

        storage.setDescription(storageDescription);

        return storageRepository.save(storage);
    }

    @Override
    public String deleteStorageById(Long storageId) {
        Storage storage = findById(storageId);

        storageRepository.deleteById(storageId);

        return ResponseMessageConstants.STORAGE_SUCCESSFULLY_DELETE;
    }

    public Storage findById(Long storageId) {
        Storage storage = storageRepository.findById(storageId).orElse(null);
        if(storage == null)
            throw new StorageNotFoundException(ResponseMessageConstants.STORAGE_NOT_FOUND);

        return storage;
    }

    public Storage findStorageByDescription(String storage) {
        return storageRepository.findByDescription(storage);
    }
}
