package com.example.sprotte.entity.storageContentRatio;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ContentRatioProductStorageId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id_product")
    private Long productId;

    @Column(name = "id_storage")
    private Long storageId;

    public ContentRatioProductStorageId () {

    }

    public ContentRatioProductStorageId(Long productId, Long storageId) {
        super();
        this.productId = productId;
        this.storageId = storageId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }
}
