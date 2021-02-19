package com.example.sprotte.entity.contentratio;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ContentRatioProductBarContainerId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id_product")
    private Long productId;

    @Column(name = "id_bar_container")
    private Long barContainerId;

    public ContentRatioProductBarContainerId () {

    }

    public ContentRatioProductBarContainerId(Long productId, Long barContainerId) {
        super();
        this.productId = productId;
        this.barContainerId = barContainerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getBarContainerId() {
        return barContainerId;
    }

    public void setBarContainerId(Long barContainerId) {
        this.barContainerId = barContainerId;
    }
}
