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
    private String description;

    @JsonManagedReference
    @OneToMany(mappedBy = "storage")
    private List<ContentRatioProductStorage> ratioProductStorages = new ArrayList<>();

    public Storage() {

    }

    public Storage(String storageDescription) {
        this.description = storageDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ContentRatioProductStorage> getRatioProductStorages() {
        return ratioProductStorages;
    }

    public void setRatioProductStorages(List<ContentRatioProductStorage> ratioProductStorages) {
        this.ratioProductStorages = ratioProductStorages;
    }
}
