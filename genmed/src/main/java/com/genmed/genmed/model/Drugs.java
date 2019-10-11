package com.genmed.genmed.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Drugs {

    @Autowired
    JdbcTemplate jt;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jt = jdbcTemplate;
    }

    private int drug_id;
    private String name;
    private String mf_name;
    private int gen_id;
    private int is_generic;

    private GenericDrug gen;

    public int getDrug_id() {
        return drug_id;
    }

    public void setDrug_id(int drug_id) {
        this.drug_id = drug_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGen_id() {
        return gen_id;
    }

    public void setGen_id(int gen_id) {
        this.gen_id = gen_id;
    }

    public GenericDrug getGen() {
        return gen;
    }

    public void setGen(int gen_id) {
        String query = "select * from genericDrug where gen_id=?";
        this.gen = jt.queryForObject(query, new RowMapper<GenericDrug>() {
            @Override
            public GenericDrug mapRow(ResultSet row, int i) throws SQLException {
                GenericDrug g = new GenericDrug();
                g.setGen_id(row.getInt("gen_id"));
                g.setName(row.getString("name"));
                return g;
            }
        });
    }

    public String getMf_name() {
        return mf_name;
    }

    public void setMf_name(String mf_name) {
        this.mf_name = mf_name;
    }

    public int getIs_generic() {
        return is_generic;
    }

    public void setIs_generic(int is_generic) {
        this.is_generic = is_generic;
    }
}
