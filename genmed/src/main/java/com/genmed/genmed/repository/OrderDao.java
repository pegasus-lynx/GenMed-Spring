package com.genmed.genmed.repository;


import com.genmed.genmed.model.ItemsOrdered;
import com.genmed.genmed.model.Orders;
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
public class OrderDao {

    @Autowired
    JdbcTemplate jt;

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
                o.setOrder_date(r.getDate("order_date"));
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
}
