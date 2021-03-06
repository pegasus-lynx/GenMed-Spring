package com.genmed.genmed.repository;


import com.genmed.genmed.model.DrugBatch;
import com.genmed.genmed.model.Drugs;
import com.genmed.genmed.model.Shop;
import com.genmed.genmed.model.ShopInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        String query = "insert into shopPhone values (?,?)";
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

    public List<Shop> getShopsWithNameLike(String name) {
        String query = "select * from shop where shop_name like '%"+name+"%'";
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

    public List<Shop> getShopsHavingDrug(Integer drug_id) {
        String query = "select * from shop where shop_id in (" +
                "select b.shop_id from drugBatch b, shopInventory s where s.batch_id=b.batch_id and s.quantity > 0 and b.drug_id="+drug_id+")";
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

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        String query = "select s.item_id,mfg_date,exp_date,b.batch_id,b.drug_id,name,mf_name,quantity,price from ( drugBatch b inner join drugs d on d.drug_id=b.drug_id) inner join shopInventory s on b.batch_id=s.batch_id where s.shop_id="+shop_id;
        List<ShopInventory> res = new ArrayList<ShopInventory>();
        List<Map<String,Object>> rs = jt.queryForList(query);
        for(Map<String,Object> r:rs) {
            ShopInventory s = new ShopInventory();
            DrugBatch b = new DrugBatch();
            Drugs d = new Drugs();

            d.setName((String)r.get("name"));
            d.setMf_name((String)r.get("mf_name"));
            d.setDrug_id((int)r.get("drug_id"));

            b.setBatch_id((int)r.get("batch_id"));
            b.setExp_date(df.format((Date) r.get("exp_date")));
            b.setMfg_date(df.format((Date) r.get("mfg_date")));
            b.setDrug(d);

            s.setPrice((BigDecimal) r.get("price"));
            s.setQuantity((int)r.get("quantity"));
            s.setBatch_id((int) r.get("batch_id"));
            s.setItem_id((int) r.get("item_id"));
            s.setDrugBatch(b);

            res.add(s);
        }
        return res;
    }

    public void saveInventoryItem(int quantity, BigDecimal price, int batch_id, int shop_id){
        String query = "insert into shopInventory(quantity, price, batch_id, shop_id) values (?,?,?,?)";
        jt.update(query, quantity, price, batch_id, shop_id);
    }

    public boolean isOwner(int user_id, int shop_id) {
        String query = "select count(*) from owner where shop_id=? and user_id=?";
        int cnt = jt.queryForObject(query, new Object[]{shop_id, user_id}, Integer.class);
        if(cnt == 0) return false;
        return true;
    }

    public void saveDrugBatch( String batch_no, String mfg_date, String exp_date, int drug_id ) {
        String query = "insert into drugBatch(batch_no, mfg_date, exp_date, drug_id) values (?,?,?,?)";
        jt.update(query, batch_no, mfg_date, exp_date, drug_id);
    }

    public List<String> getBatchNo(int drug_id){
        String query = "select distinct batch_no from drugBatch where drug_id="+drug_id;
        List<String> res = new ArrayList<String>();
        List<Map<String,Object>> rs = jt.queryForList(query);
        for( Map<String,Object> r:rs ) {
            String s = (String) r.get("batch_no");
            res.add(s);
        }
        return res;
    }

    public List<String> getPhoneByID(int shop_id){
        String query = "select phone_no from shopPhone where shop_id="+shop_id;
        List<String> res = new ArrayList<String>();
        List<Map<String,Object>> rs = jt.queryForList(query);
        for( Map<String,Object> r:rs ) {
            String s = (String) r.get("phone_no");
            res.add(s);
        }
        return res;
    }

    public int getBatchID(int drug_id, String batch_no){
        String query = "select batch_id from drugBatch where drug_id=? and batch_no=?";
        return jt.queryForObject(query, new Object[]{drug_id, batch_no}, Integer.class);
    }

    public List<Shop> getShopsByUserID(int user_id){
        String query = "select * from shop where shop_id in (select shop_id from owner where user_id="+user_id+")";
        return jt.query(query, new RowMapper<Shop>() {
            @Override
            public Shop mapRow(ResultSet r, int i) throws SQLException {
                Shop s = new Shop();
                s.setShop_id(r.getInt("shop_id"));
                s.setShop_name((r.getString("shop_name")));
                return s;
            }
        });
    }

    // Implement this later
    public void refillItems(int order_id){
    }

}
