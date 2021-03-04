package com.example.sprotte.entity.barcontainerContentratio;

import com.example.sprotte.constants.DatabaseConstants;
import com.example.sprotte.entity.BarContainer;
import com.example.sprotte.entity.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = DatabaseConstants.PRODUCT_BAR_CONTAINER_CONTENT_RATIO)
public class ContentRatioProductBarContainer {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ContentRatioProductBarContainerId id = new ContentRatioProductBarContainerId();

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade= {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = DatabaseConstants.ID_PRODUCT, insertable = false, updatable = false)
    private Product product;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade= {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = DatabaseConstants.ID_BAR_CONTAINER, insertable = false, updatable = false)
    private BarContainer barContainer;

    @Column(name = DatabaseConstants.PRODUCT_BAR_CONTAINER_CONTENT_RATIO_MAX_QUANTITY)
    private int maxQuantity;

    @Column(name = DatabaseConstants.PRODUCT_BAR_CONTAINER_CONTENT_RATIO_ACTUAL_QUANTITY)
    private int actualQuantity;

    @Column(name = DatabaseConstants.PRODUCT_BAR_CONTAINER_CONTENT_RATIO_THRESHOLD)
    private int threshold;

    public ContentRatioProductBarContainer () {

    }

    public ContentRatioProductBarContainer(int maxQuantity, int actualQuantity, int threshold) {
        this.maxQuantity = maxQuantity;
        this.actualQuantity = actualQuantity;
        this.threshold = threshold;
    }

    public ContentRatioProductBarContainerId getId() {
        return id;
    }

    public void setId(ContentRatioProductBarContainerId id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BarContainer getBarContainer() {
        return barContainer;
    }

    public void setBarContainer(BarContainer barContainer) {
        this.barContainer = barContainer;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public int getActualQuantity() {
        return actualQuantity;
    }

    public void setActualQuantity(int actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
