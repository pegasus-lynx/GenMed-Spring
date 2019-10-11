package com.genmed.genmed.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class ShopDao {

    @Autowired
    JdbcTemplate jt;

    enum OrderStatus {
        Ordered, Shipped, Delivered, Cancelled, Returned
    }

    public Integer save(String shop_name,String email_id,String license,String shop_login_pin, Integer address_id) {
        String query = "insert into Shop(shop_name, email_id, license, shop_login_pin, address_id) values (?,?,?,?,?)";
        jt.update(query, shop_name, email_id, license, shop_login_pin, address_id);

        String sql = "select user_id from Shop where email_id = ? and shop_login_pin = ?";
        return jt.queryForObject(sql, new Object[]{email_id, shop_login_pin}, Integer.class);
    }

    public void saveShopPhone(String phone_no, Integer shop_id) {
        String query = "insert into userPhone values (?,?)";
        jt.update(query, phone_no, shop_id);
    }

    public void changeOrderStatus(Integer order_id, OrderStatus status) {
        String query = "update Orders set order_status = ? where order_id = ?";
        jt.update(query, status, order_id);
    }

    public void addItem(Integer quantity, Integer shop_id, Integer batch_id) {
        String query = "insert into shopInventory values (?,?,?)";
        jt.update(query, quantity, shop_id, batch_id);
    }
}
