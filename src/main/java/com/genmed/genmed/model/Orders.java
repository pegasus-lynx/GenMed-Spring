package com.genmed.genmed.model;

import java.math.BigDecimal;
import java.util.List;

public class Orders {

    private Integer order_id;
    private String status;
    private String order_date;
    private BigDecimal bill_amount;
    private Integer shop_id;
    private Integer user_id;
    private String order_time;

    private List<ItemsOrdered> items;

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public BigDecimal getBill_amount() {
        return bill_amount;
    }

    public void setBill_amount(BigDecimal bill_amount) {
        this.bill_amount = bill_amount;
    }

    public Integer getShop_id() {
        return shop_id;
    }

    public void setShop_id(Integer shop_id) {
        this.shop_id = shop_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public List<ItemsOrdered> getItems() {
        return items;
    }

    public void setItems(List<ItemsOrdered> items) {
        this.items = items;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }
}
