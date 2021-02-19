package com.example.sprotte.entity;

import com.example.sprotte.constants.DatabaseConstants;

import javax.persistence.*;

@Entity
@Table(name = DatabaseConstants.CONTAINER_CATEGORY_TABLE)
public class ContainerCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = DatabaseConstants.ID_CONTAINER_CATEGORY)
    private Long id;

    @Column(name = DatabaseConstants.CONTAINER_CATEGORY_DESCRIPTION)
    private String description;


    public ContainerCategory() {

    }

    public ContainerCategory(String description) {
        this.description = description;
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
}
