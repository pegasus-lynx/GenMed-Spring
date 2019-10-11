package com.genmed.genmed.model;

import java.util.Date;
import java.util.List;

public class Orders {

    enum OrderStatus {
        Ordered, Dispatched, Delivered, Returned, Cancelled;
    }

    private Integer order_id;
    private OrderStatus status;
    private Date order_date;
    private Double bill_amount;
    private Integer shop_id;
    private Integer user_id;

    private List<ItemsOrdered> items;

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public Double getBill_amount() {
        return bill_amount;
    }

    public void setBill_amount(Double bill_amount) {
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
}
