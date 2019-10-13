package com.genmed.genmed.model;

import java.math.BigDecimal;

public class ShopInventory {

    private int item_id;
    private int quantity;
    private BigDecimal price;

    private int shop_id;
    private Shop shop;

    private int batch_id;
    private DrugBatch drugBatch;

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public int getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(int batch_id) {
        this.batch_id = batch_id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public DrugBatch getDrugBatch() {
        return drugBatch;
    }

    public void setDrugBatch(DrugBatch drugBatch) {
        this.drugBatch = drugBatch;
    }
}
