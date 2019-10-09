package com.genmed.genmed.model;

import java.util.Date;
import java.util.List;

public class Orders {

    enum OrderStatus {
        Ordered, Dispatched, Delivered, Returned, Cancelled;
    }

    private class Item {
        Integer quantity;
        Double amount;
        String item_name;
        Integer batch_id;
    }

    private Integer order_id;
    private OrderStatus status;
    private Date order_date;
    private Double bill_amount;
    private Integer shop_id;
    private Integer user_id;

    private List<Item> items;

}
