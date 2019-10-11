package com.genmed.genmed.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional
@Repository
public class DrugDao {

    @Autowired
    JdbcTemplate jt;

    public Integer addDrug(String name, String mf_name, Boolean is_generic, Integer gen_id) {
        String query = "insert into drugs values (?,?,?,?)";
        jt.update(query, name, mf_name, is_generic, gen_id);

        String sql = "select drug_id from drugs where name=? and mf_name=? and is_generic=? and gen_id=?";
        return jt.queryForObject(sql, new Object[]{name, mf_name, is_generic, gen_id}, Integer.class);
    }

    public void addDrugBatch(String batch_no, Date mfg_date, Date exp_date, Double price, Integer drug_id) {
        String query = "insert into drugBatch values (?,?,?,?,?)";
        jt.update(query, batch_no, mfg_date, exp_date, price, drug_id);
    }
}
