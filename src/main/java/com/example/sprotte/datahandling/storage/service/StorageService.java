package com.example.sprotte.datahandling.storage.service;

import com.example.sprotte.entity.Storage;

import java.util.List;

public interface StorageService {

    List<Storage> getStorages();

    Storage saveStorage(String storage);

    Storage findStorageById(Long storageId);

    Storage updateStorageById(Long storageId, String storage);

    String deleteStorageById(Long storageId);
}
