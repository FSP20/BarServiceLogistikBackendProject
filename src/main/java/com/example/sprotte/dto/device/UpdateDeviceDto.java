package com.example.sprotte.dto.device;

public class UpdateDeviceDto {

    private Long deviceId;
    private int deviceNumber;
    private String description;
    private boolean active;

    public UpdateDeviceDto(Long deviceId, int deviceNumber, String description, boolean active) {
        this.deviceId = deviceId;
        this.deviceNumber = deviceNumber;
        this.description = description;
        this.active = active;
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

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }
}
