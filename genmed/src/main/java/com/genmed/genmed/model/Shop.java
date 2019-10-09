package com.genmed.genmed.model;

import java.util.Date;
import java.util.List;

public class Shop {

    private class Staff {
        Double salary;
        Date date_of_joining;
        Integer user_id;
    }

    private Integer shop_id;
    private String shop_name;
    private String email_id;
    private String license;
    private String shop_login_pin;
    private Integer address_id;
    private List<String> phone_nos;

    private Integer owner_id;
    private List<Staff> shop_staffs;
}
