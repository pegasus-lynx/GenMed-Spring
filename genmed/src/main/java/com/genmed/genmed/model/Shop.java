package com.genmed.genmed.model;

import java.util.Date;
import java.util.List;

public class Shop {

    private Integer shop_id;
    private String shop_name;
    private String email_id;
    private String license;
    private String shop_login_pin;
    private Integer address_id;
    private List<String> phone_nos;

    public Integer getShop_id() {
        return shop_id;
    }

    public void setShop_id(Integer shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getShop_login_pin() {
        return shop_login_pin;
    }

    public void setShop_login_pin(String shop_login_pin) {
        this.shop_login_pin = shop_login_pin;
    }

    public Integer getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Integer address_id) {
        this.address_id = address_id;
    }

    public List<String> getPhone_nos() {
        return phone_nos;
    }

    public void setPhone_nos(List<String> phone_nos) {
        this.phone_nos = phone_nos;
    }
}
