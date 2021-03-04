package com.example.sprotte.entity;

import com.example.sprotte.constants.DatabaseConstants;
import com.example.sprotte.entity.storageContentRatio.ContentRatioProductStorage;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = DatabaseConstants.STORAGE_TABLE)
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DatabaseConstants.ID_STORAGE)
    private Long id;

    @Column(name = DatabaseConstants.STORAGE_DESCRIPTION)
    private String storageDescription;

    @JsonManagedReference
    @OneToMany(mappedBy = "storage")
    private List<ContentRatioProductStorage> ratioProductStorages = new ArrayList<>();

    public Storage() {

    }

    public Storage(String storageDescription) {
        this.storageDescription = storageDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStorageDescription() {
        return storageDescription;
    }

    public void setStorageDescription(String storageDescription) {
        this.storageDescription = storageDescription;
    }

    public List<ContentRatioProductStorage> getRatioProductStorages() {
        return ratioProductStorages;
    }

    public void setRatioProductStorages(List<ContentRatioProductStorage> ratioProductStorages) {
        this.ratioProductStorages = ratioProductStorages;
    }
}
