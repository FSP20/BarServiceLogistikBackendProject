package com.example.sprotte.entity;

import com.example.sprotte.constants.DatabaseConstants;
import com.example.sprotte.entity.contentratio.ContentRatioProductBarContainer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = DatabaseConstants.BAR_CONTAINER_TABLE)
public class BarContainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DatabaseConstants.ID_BAR_CONTAINER)
    private Long id;

    @Column(name = DatabaseConstants.BAR_CONTAINER_DESCRIPTION)
    private String description;

    //Uni-Directional
    @ManyToOne(fetch = FetchType.LAZY, cascade= {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = DatabaseConstants.ID_CONTAINER_CATEGORY)
    private ContainerCategory containerCategory;

    // Bi-Directional
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade= {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = DatabaseConstants.ID_BAR_SEGMENT)
    private BarSegment barSegment;

    @JsonManagedReference
    @OneToMany(mappedBy = "barContainer")
    private List<ContentRatioProductBarContainer> ratioProductBarContainers = new ArrayList<>();


    public BarContainer() {

    }

    public BarContainer(String description) {
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

    public ContainerCategory getContainerCategory() {
        return containerCategory;
    }

    public void setContainerCategory(ContainerCategory containerCategory) {
        this.containerCategory = containerCategory;
    }

    public BarSegment getBarSegment() {
        return barSegment;
    }

    public void setBarSegment(BarSegment barSegment) {
        this.barSegment = barSegment;
    }

    public List<ContentRatioProductBarContainer> getRatioProductBarContainers() {
        return ratioProductBarContainers;
    }

    public void setRatioProductBarContainers(List<ContentRatioProductBarContainer> ratioProductBarContainers) {
        this.ratioProductBarContainers = ratioProductBarContainers;
    }
}
