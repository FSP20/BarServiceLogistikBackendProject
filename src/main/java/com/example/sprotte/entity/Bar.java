package com.example.sprotte.entity;

import com.example.sprotte.constants.DatabaseConstants;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= DatabaseConstants.BAR_TABLE)
public class Bar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name=DatabaseConstants.ID_BAR)
    private Long id;

    @Column(name=DatabaseConstants.BAR_NAME)
    private String name;


    @JsonManagedReference
    @OneToMany
    @JoinColumn(name = DatabaseConstants.ID_BAR, insertable = false, updatable = true)
    @Fetch(value = FetchMode.JOIN)
    private List<Device> devices = new ArrayList<>();


    @JsonManagedReference
    @OneToMany
    @JoinColumn(name = DatabaseConstants.ID_BAR, insertable = false, updatable = true)
    // Hibernate allows only one JOIN FETCH. But not affected the Use Case
    //@Fetch(value = FetchMode.JOIN)
    private List<BarSegment> barSegments = new ArrayList<>();


    public Bar() {

    }

    public Bar(Long id, String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public List<BarSegment> getBarSegments() {
        return barSegments;
    }

    public void setBarSegments(List<BarSegment> barSegments) {
        this.barSegments = barSegments;
    }
}
