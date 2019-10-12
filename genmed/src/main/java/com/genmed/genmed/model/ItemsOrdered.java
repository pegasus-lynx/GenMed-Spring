package com.genmed.genmed.model;

import java.math.BigDecimal;

public class ItemsOrdered {
    private Integer quantity;
    private BigDecimal price;
    private String item_name;
    private Integer batch_id;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Integer getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(Integer batch_id) {
        this.batch_id = batch_id;
    }
}
