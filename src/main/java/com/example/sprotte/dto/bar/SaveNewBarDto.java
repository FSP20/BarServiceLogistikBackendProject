package com.example.sprotte.dto.bar;

import java.util.List;

public class SaveNewBarDto {

    private String name;
    private List<Long> deviceId;

    public SaveNewBarDto(String name, List<Long> deviceId) {
        this.name = name;
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(List<Long> deviceId) {
        this.deviceId = deviceId;
    }
}
