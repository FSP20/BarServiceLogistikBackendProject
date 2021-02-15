package com.example.sprotte.dto.bar;

public class SaveNewBarSegmentDto {

    private String barSegmentDescription;
    private String barSegmentCategory;
    private Long barId;

    public SaveNewBarSegmentDto(String barSegmentDescription, String barSegmentCategory, Long barId) {
        this.barSegmentDescription = barSegmentDescription;
        this.barSegmentCategory = barSegmentCategory;
        this.barId = barId;
    }

    public String getBarSegmentDescription() {
        return barSegmentDescription;
    }

    public void setBarSegmentDescription(String barSegmentDescription) {
        this.barSegmentDescription = barSegmentDescription;
    }

    public String getBarSegmentCategory() {
        return barSegmentCategory;
    }

    public void setBarSegmentCategory(String barSegmentCategory) {
        this.barSegmentCategory = barSegmentCategory;
    }

    public Long getBarId() {
        return barId;
    }

    public void setBarId(Long barId) {
        this.barId = barId;
    }
}
