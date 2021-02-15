package com.example.sprotte.entity;

import com.example.sprotte.constants.DatabaseConstants;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= DatabaseConstants.WORK_SHIFT_TABLE)
public class WorkShift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name=DatabaseConstants.ID_WORK_SHIFT)
    private Long id;

    @Column(name=DatabaseConstants.WORK_SHIFT_DESCRIPTION)
    private String description;

    @Column(name=DatabaseConstants.START_TIME)
    private Time startTime;

    @Column(name = DatabaseConstants.END_TIME)
    private Time endTime;

    @Column(name = DatabaseConstants.DAY)
    private int day;

    //Bi-Directional
    @JsonBackReference
    @ManyToMany(mappedBy = "workShifts")
    private List<Employee> employees = new ArrayList<>();


    public WorkShift() {

    }

    public WorkShift(Long id, String description, Time startTime, Time endTime,int calendarWeek, int day) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
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

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
