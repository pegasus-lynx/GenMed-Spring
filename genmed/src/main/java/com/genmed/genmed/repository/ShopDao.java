package com.genmed.genmed.repository;


import com.genmed.genmed.model.Shop;
import com.genmed.genmed.model.ShopInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Transactional
@Repository
public class ShopDao {

    @Autowired
    JdbcTemplate jt;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jt = jdbcTemplate;
    }

    public int save(String shop_name,String email_id,String license, Integer address_id) {
        String query = "insert into shop(shop_name, email_id, license, address_id) values (?,?,?,?)";
        jt.update(query, shop_name, email_id, license, address_id);

        String sql = "select shop_id from shop where shop_name=? and email_id=? and license=? and address_id=?";
        return jt.queryForObject(sql, new Object[]{shop_name, email_id, license, address_id}, Integer.class);
    }

    public void saveShopPhone(String phone_no, Integer shop_id) {
        String query = "insert into userPhone values (?,?)";
        jt.update(query, phone_no, shop_id);
    }

    public void changeOrderStatus(Integer order_id, String status) {
        String query = "update orders set order_status = ? where order_id = ?";
        jt.update(query, status, order_id);
    }

    public void addItem(Integer quantity, Integer shop_id, Integer batch_id) {
        String query = "insert into shopInventory values (?,?,?)";
        jt.update(query, quantity, shop_id, batch_id);
    }

    public void addOwner(int user_id, int shop_id){
        String query = "insert into owner(user_id,shop_id) values (?,?)";
        jt.update(query, user_id, shop_id);
    }

    public List<Shop> getAllShops() {
        String query = "select * from shop";
        return jt.query(query, new RowMapper<Shop>() {
            @Override
            public Shop mapRow(ResultSet r, int i) throws SQLException {
                Shop s = new Shop();
                s.setShop_name(r.getString("shop_name"));
                s.setShop_id(r.getInt("shop_id"));
                return s;
            }
        });
    }

    public Shop getShopByID(int shop_id) {
        String query = "select * from shop where shop_id="+shop_id;
        return jt.queryForObject(query, new RowMapper<Shop>() {
            @Override
            public Shop mapRow(ResultSet r, int i) throws SQLException {
                Shop s = new Shop();
                s.setShop_name(r.getString("shop_name"));
                s.setShop_id(r.getInt("shop_id"));
                s.setEmail_id(r.getString("email_id"));
                s.setLicense(r.getString("license"));
                s.setAddress_id(r.getInt("address_id"));
                return s;
            }
        });
    }

    public List<ShopInventory> getInventoryByID(int shop_id) {
        return null;
    }
}
