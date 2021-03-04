package com.example.sprotte.entity.storageContentRatio;

import com.example.sprotte.constants.DatabaseConstants;
import com.example.sprotte.entity.Product;
import com.example.sprotte.entity.Storage;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = DatabaseConstants.PRODUCT_STORAGE_CONTENT_RATIO)
public class ContentRatioProductStorage {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ContentRatioProductStorageId id = new ContentRatioProductStorageId();

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = DatabaseConstants.ID_PRODUCT, insertable = false, updatable = false)
    private Product product;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = DatabaseConstants.ID_STORAGE, insertable = false, updatable = false)
    private Storage storage;

    @Column(name = DatabaseConstants.PRODUCT_STORAGE_CONTENT_RATIO_MAX_QUANTITY)
    private int maxQuantity;

    @Column(name = DatabaseConstants.PRODUCT_STORAGE_CONTENT_RATIO_ACTUAL_QUANTITY)
    private int actualQuantity;

    @Column(name = DatabaseConstants.PRODUCT_STORAGE_CONTENT_RATIO_THRESHOLD)
    private int threshold;

    public ContentRatioProductStorage () {

    }

    public ContentRatioProductStorage(int maxQuantity, int actualQuantity, int threshold) {
        this.maxQuantity = maxQuantity;
        this.actualQuantity = actualQuantity;
        this.threshold = threshold;
    }

    public ContentRatioProductStorageId getId() {
        return id;
    }

    public void setId(ContentRatioProductStorageId id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
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
