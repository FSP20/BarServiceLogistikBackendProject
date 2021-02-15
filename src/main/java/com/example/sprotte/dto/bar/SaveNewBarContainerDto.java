package com.example.sprotte.dto.bar;

public class SaveNewBarContainerDto {

    private String barContainerDescription;
    private Long barSegmentId;
    private Long containerCategoryId;

    public SaveNewBarContainerDto(String barContainerDescription, Long barSegmentId, Long containerCategoryId) {
        this.barContainerDescription = barContainerDescription;
        this.barSegmentId = barSegmentId;
        this.containerCategoryId = containerCategoryId;
    }

    public String getBarContainerDescription() {
        return barContainerDescription;
    }

    public void setBarContainerDescription(String barContainerDescription) {
        this.barContainerDescription = barContainerDescription;
    }

    public Long getBarSegmentId() {
        return barSegmentId;
    }

    public void setBarSegmentId(Long barSegmentId) {
        this.barSegmentId = barSegmentId;
    }

    public Long getContainerCategoryId() {
        return containerCategoryId;
    }

    public void setContainerCategoryId(Long containerCategoryId) {
        this.containerCategoryId = containerCategoryId;
    }
}
