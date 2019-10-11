package com.genmed.genmed.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class UserDao {

    @Autowired
    JdbcTemplate jt;

    private enum AddressType {
        Home, Work, Other
    }

    public Integer save(String f_name,String l_name,String email_id,String password) {
        String query = "insert into User(first_name, last_name, email_id, password) values (?,?,?,?)";
        jt.update(query, f_name, l_name, email_id, password);

        String sql = "select user_id from User where email_id = ?";
        return jt.queryForObject(sql, new Object[]{email_id}, Integer.class);
    }

    public void saveAddressOfUser(String address_type, Integer user_id, Integer address_id) {
        String query = "insert into addressOfUser values (?,?,?)";

        AddressType at;
        if(address_type.equals("Home")) { at = AddressType.Home; }
        else if(address_type == "Work") { at = AddressType.Work; }
        else { at = AddressType.Other; }
        jt.update(query, at, user_id, address_id);
    }

    public void saveUserPhone(String phone_no, Integer user_id) {
        String query = "insert into userPhone values (?,?)";
        jt.update(query, phone_no, user_id);
    }

    public void deleteUserPhone(String phone_no, Integer user_id) {
        String query = "delete from userPhone where phone_no=? and user_id=?";
        jt.update(query, phone_no, user_id);
    }

    public void saveUserReview(String comment, Double rating, Integer order_id) {
        String query = "insert into Reviews(comment, rating, order_id) values (?,?,?)";
        jt.update(query, comment, rating, order_id);
    }

    public void deleteUserReview(Integer review_id) {
        String query = "delete from Review where review_id=?";
        jt.update(query, review_id);
    }
}
