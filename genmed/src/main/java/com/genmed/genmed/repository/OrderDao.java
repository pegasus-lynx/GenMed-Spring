package com.genmed.genmed.repository;


import com.genmed.genmed.model.ItemsOrdered;
import com.genmed.genmed.model.Orders;
import com.genmed.genmed.model.Reviews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Transactional
@Repository
public class OrderDao {

    @Autowired
    JdbcTemplate jt;

    private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    private DateFormat tf = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jt = jdbcTemplate;
    }

    public Orders getOrderByID(int order_id){

        String query = "select * from orders where order_id="+order_id;
        Orders p = jt.queryForObject(query, new RowMapper<Orders>() {
            @Override
            public Orders mapRow(ResultSet r, int i) throws SQLException {
                Orders o = new Orders();
                o.setOrder_id(r.getInt("order_id"));
                o.setOrder_date(df.format(r.getDate("order_date")));
                o.setOrder_time(tf.format(r.getTime("order_time")));
                o.setBill_amount(r.getBigDecimal("bill_amount"));
                o.setStatus(r.getString("order_status"));
                o.setShop_id(r.getInt("shop_id"));
                o.setUser_id(r.getInt("user_id"));
                return o;
            }
        });

        String sql = "select * from itemsOrdered where order_id="+order_id;
        List<ItemsOrdered> io = jt.query(sql, new RowMapper<ItemsOrdered>() {
            @Override
            public ItemsOrdered mapRow(ResultSet r, int i) throws SQLException {
                ItemsOrdered k = new ItemsOrdered();
                k.setPrice(r.getBigDecimal("price"));
                k.setQuantity(r.getInt("quantity"));
                k.setBatch_id(r.getInt("item_id"));
                return k;
            }
        });
        p.setItems(io);
        return p;
    }

    public List<ItemsOrdered> getItemsOrderedByOrderID(int order_id){
        return null;
    }

    public List<Reviews> getReviewsByOrderID(int order_id){
        return null;
    }

    public List<Reviews> getReviewsForShopID(int shop_id){
        String query = "select * from reviews where order_id in ( select order_id from orders where shop_id="+shop_id+')';
        return jt.query(query, new RowMapper<Reviews>() {
            @Override
            public Reviews mapRow(ResultSet row, int i) throws SQLException {
                Reviews r = new Reviews();
                r.setComment(row.getString("comment"));
                r.setRating(row.getBigDecimal("rating"));
                r.setOrder_id(row.getInt("order_id"));
                return r;
            }
        });
    }

    public List<Orders> getOrdersByShopID(int shop_id){
        String query = "select * from orders where shop_id="+shop_id;
        return jt.query(query, new RowMapper<Orders>() {
            @Override
            public Orders mapRow(ResultSet r, int i) throws SQLException {
                Orders o = new Orders();
                o.setOrder_id(r.getInt("order_id"));
                o.setOrder_date(df.format(r.getDate("order_date")));
                o.setOrder_time(tf.format(r.getTime("order_time")));
                o.setBill_amount(r.getBigDecimal("bill_amount"));
                o.setStatus(r.getString("order_status"));
                o.setShop_id(r.getInt("shop_id"));
                o.setUser_id(r.getInt("user_id"));
                return o;
            }
        });
    }

    public int save(String order_date, String order_time, BigDecimal bill_amount, String status,int shop_id, int user_id){
        String query = "insert into orders(order_date, order_time, bill_amount, order_status, shop_id, user_id) values (?,?,?,?,?,?)";
        jt.update(query, order_date, order_time, bill_amount, status, shop_id, user_id);

        String sql = "select order_id from orders where order_date=? and order_time=? and bill_amount=? and order_status=? and shop_id=? and user_id=?";
        return jt.queryForObject(sql, new Object[]{order_date, order_time, bill_amount, status, shop_id, user_id}, Integer.class);
    }

    public void addItemToOrder(int order_id,int quantity, BigDecimal price, int item_id, int shop_id){
        String query = "insert into itemsOrdered values (?,?,?,?)";
        jt.update(query,quantity,price,order_id,item_id);

        String sql = "update orders set bill_amount = ? + (select bill_amount from orders where order_id = ?) where order_id="+order_id;
        jt.update(sql, price, order_id);

        String seq = "update shopInventory set quantity = (select quantity from shopInventory where shop_id=? and item_id=?) - ? where shop_id=? and item_id=?";
        jt.update(seq, shop_id, item_id, quantity, shop_id, item_id);
    }

    public void removeOrder(int order_id){
        String query = "delete from orders where order_id="+order_id;
        jt.update(query);
    }

    public void changeOrderStatus(int order_id, String order_status){
        String query = "update orders set order_status=? where order_id="+order_id;
        jt.update(query, order_status);
    }

    public void deleteReview(int review_id){
        String query = "delete from reviews where review_id="+review_id;
        jt.update(query);
    }


}
