package com.genmed.genmed.repository;

import com.genmed.genmed.model.Address;
import com.genmed.genmed.model.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public List<UserAddress> getAddressesByID(int user_id){
        String query = "select * from addressOfUser as au, address as a where a.address_id=au.address_id and au.user_id="+user_id;
        List<Map<String,Object>> rs = jt.queryForList(query);
        List<UserAddress> res = new ArrayList<UserAddress>();
        for ( Map<String,Object> r:rs) {
            UserAddress u = new UserAddress();
            u.setAddress_type((String) r.get("address_type"));
            Address a = new Address();
            a.setAddress_id((int) r.get("address_id"));
//            Add other fields here
            u.setAddress(a);
            res.add(u);
        }
        return res;
    }
}
