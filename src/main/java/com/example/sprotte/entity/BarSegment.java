package com.example.sprotte.entity;

import com.example.sprotte.constants.DatabaseConstants;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = DatabaseConstants.BAR_SEGMENT_TABLE)
public class BarSegment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DatabaseConstants.ID_BAR_SEGMENT)
    private Long id;

    @Column(name = DatabaseConstants.BAR_SEGMENT_DESCRIPTION)
    private String description;

    @Column(name = DatabaseConstants.BAR_SEGMENT_CATEGORY)
    private String category;

    //Bi-Directional
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name=DatabaseConstants.ID_BAR)
    private Bar bar;

    //Bi-Directional
    @JsonManagedReference
    @OneToMany
    @JoinColumn(name = DatabaseConstants.ID_BAR_SEGMENT, insertable = false, updatable = true)
    @Fetch(value = FetchMode.JOIN)
    private List<BarContainer> barContainers = new ArrayList<>();

    public BarSegment() {

    }

    public BarSegment(String description, String category) {
        this.description = description;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

    public List<BarContainer> getBarContainers() {
        return barContainers;
    }

    public void setBarContainers(List<BarContainer> barContainers) {
        this.barContainers = barContainers;
    }
}
