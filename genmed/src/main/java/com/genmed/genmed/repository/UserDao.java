package com.genmed.genmed.repository;

import com.genmed.genmed.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;

@Transactional
@Repository
public class UserDao {

    @Autowired
    JdbcTemplate jt;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jt = jdbcTemplate;
    }

    private enum AddressType {
        Home, Work, Other
    }

    public boolean userExists(String email_id) {
        String query = "select count(*) from user where email_id=?";
        int cnt = jt.queryForObject(query, Integer.class, email_id);
        if(cnt == 0) return false;
        else return true;
    }

    public User findByUsername(String email_id) {
        String query = "select * from user where email_id='"+email_id+"'";
        return jt.queryForObject(query, new RowMapper<User>() {

            public User mapRow(ResultSet row, int i) throws SQLException {
                User u = new User();
                u.setEmail_id(row.getString("email_id"));
                u.setFirst_name(row.getString("first_name"));
                u.setLast_name(row.getString("last_name"));
                u.setEmail_id(row.getString("email_id"));
                u.setPassword(row.getString("password"));
                return u;
            }
        });
    }

    public void save(String f_name,String l_name,String email_id,String password) {
        String query = "insert into user(first_name, last_name, email_id, password) values (?,?,?,?)";
        jt.update(query, f_name, l_name, email_id, password);
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

    public int getUserIDByEmailID(String email_id) {
        String query = "select user_id from user where email_id=?";
        return jt.queryForObject(query, Integer.class, email_id);
    }

    public User getUserDetailByID(int user_id) {
        String query = "select * from user where user_id="+user_id;
        return jt.queryForObject(query, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet row, int i) throws SQLException {
                User u = new User();
                u.setEmail_id(row.getString("email_id"));
                u.setFirst_name(row.getString("first_name"));
                u.setLast_name(row.getString("last_name"));
                u.setEmail_id(row.getString("email_id"));
                u.setPassword(row.getString("password"));
                return u;
            }
        });
    }
}
