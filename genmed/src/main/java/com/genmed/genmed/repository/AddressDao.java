package com.genmed.genmed.repository;

import com.genmed.genmed.model.Address;
import com.genmed.genmed.model.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Transactional
@Repository
public class AddressDao {

    @Autowired
    JdbcTemplate jt;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jt = jdbcTemplate;
    }

    public int saveAddress(String plot_no, String street, String city, String district, String state, BigDecimal latitude, BigDecimal longitude) {
        String query = "insert into address(plot_no, street, city, district, state, latitude, longitude) values (?,?,?,?,?,?,?)";
        jt.update(query, plot_no, street, city, district, state, latitude, longitude);

        String sql = "select address_id from address where plot_no=? and street=? and city=? and district=? and state=? and latitude=? and longitude=?";
        return jt.queryForObject(sql, new Object[]{plot_no, street, city, district,state, latitude, longitude}, Integer.class);
    }
    
    public void saveAddressOfUser(int address_id, String address_type, int user_id){
        String query = "insert into addressOfUser() values (?,?,?)";
        jt.update(query, address_type,user_id,address_id);
    }

    public List<UserAddress> getAddressesByID(int user_id){
        String query = "select * from addressOfUser as au, address as a where a.address_id=au.address_id and au.user_id="+user_id;
        List<Map<String,Object>> rs = jt.queryForList(query);
        List<UserAddress> res = new ArrayList<UserAddress>();
        for ( Map<String,Object> r:rs) {
            UserAddress u = new UserAddress();
            u.setAddress_type((String) r.get("address_type"));
            Address a = new Address();
            a.setAddress_id((int) r.get("address_id"));
            a.setLatitude((BigDecimal) r.get("latitude"));
            a.setLongitude((BigDecimal) r.get("longitude"));
            a.setPlot_no((String) r.get("plot_no"));
            a.setStreet((String) r.get("street"));
            a.setCity((String) r.get("city"));
            a.setDistrict((String) r.get("district"));
            a.setState((String) r.get("state"));
            u.setAddress(a);
            res.add(u);
        }
        return res;
    }
}
