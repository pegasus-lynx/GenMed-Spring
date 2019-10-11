package com.genmed.genmed.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class AddressDao {

    @Autowired
    JdbcTemplate jt;

    public Integer saveAddress(String plot_no, String street, String locality, String pin_code, Double latitude, Double longitude) {
        String query = "insert into Address(plot_no, street, locality, latitude, longitude, pin_code) values (?,?,?,?,?,?)";
        jt.update(query, plot_no, street, locality, latitude, longitude, pin_code);

        String sql = "select address_id from Address where plot_no=? and street=? and locality=? and latitude=? and longitude=? and pincode=?";
        return jt.queryForObject(sql, new Object[]{plot_no, street, locality, latitude, longitude, pin_code}, Integer.class);
    }

    public String savePinCode(String pin_code, String area, String city, String district, String state) {
        String query = "insert into pincodeTable(pincode, area, city, district, state) values (?,?,?,?,?)";
        jt.update(query, pin_code, area, city, district, state);

        return pin_code;
    }
}
