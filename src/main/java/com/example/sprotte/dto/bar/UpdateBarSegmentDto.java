package com.example.sprotte.dto.bar;

public class UpdateBarSegmentDto {

    private Long barSegmentId;
    private String barSegmentDescription;
    private String barSegmentCategory;
    private Long barId;

    public UpdateBarSegmentDto(Long barSegmentId, String barSegmentDescription, String barSegmentCategory, Long barId) {
        this.barSegmentId = barSegmentId;
        this.barSegmentDescription = barSegmentDescription;
        this.barSegmentCategory = barSegmentCategory;
        this.barId = barId;
    }

    public Long getBarSegmentId() {
        return barSegmentId;
    }

    public void setBarSegmentId(Long barSegmentId) {
        this.barSegmentId = barSegmentId;
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
