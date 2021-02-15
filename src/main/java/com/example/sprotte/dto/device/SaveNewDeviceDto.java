package com.example.sprotte.dto.device;

public class SaveNewDeviceDto {

    private int deviceNumber;
    private String description;
    private boolean active;
    private Long barId;

    public SaveNewDeviceDto(int deviceNumber, String description, boolean active, Long barId) {
        this.deviceNumber = deviceNumber;
        this.description = description;
        this.active = active;
        this.barId = barId;
    }

    public int getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(int deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getBarId() {
        return barId;
    }

    public void setBarId(Long barId) {
        this.barId = barId;
    }
}
