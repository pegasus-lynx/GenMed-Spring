package com.genmed.genmed.repository;


import com.genmed.genmed.model.DrugComp;
import com.genmed.genmed.model.DrugComposition;
import com.genmed.genmed.model.Drugs;
import com.genmed.genmed.model.GenericDrug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Transactional
@Repository
public class DrugDao {

    @Autowired
    JdbcTemplate jt;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jt = jdbcTemplate;
    }

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

    public List<Drugs> listAllDrugs() {
        String query = "select * from drugs";
        return jt.query(query, new RowMapper<Drugs>() {
            @Override
            public Drugs mapRow(ResultSet row, int i) throws SQLException {
                Drugs u = new Drugs();
                u.setDrug_id(row.getInt("drug_id"));
                u.setGen_id(row.getInt("gen_id"));
                u.setMf_name(row.getString("mf_name"));
                u.setName(row.getString("name"));
                u.setIs_generic(row.getInt("is_generic"));
                return u;
            }
        });
    }

    public List<Drugs> listDrugsWithNameLike(String name){
        String query = "select * from drugs where name like '%"+name+"%'";
        return jt.query(query, new RowMapper<Drugs>() {
            @Override
            public Drugs mapRow(ResultSet row, int i) throws SQLException {
                Drugs u = new Drugs();
                u.setDrug_id(row.getInt("drug_id"));
                u.setGen_id(row.getInt("gen_id"));
                u.setMf_name(row.getString("mf_name"));
                u.setName(row.getString("name"));
                u.setIs_generic(row.getInt("is_generic"));
                return u;
            }
        });
    }

    public List<GenericDrug> listAllGenDrugs() {
        String query = "select * from genericDrugs";
        return jt.query(query, new RowMapper<GenericDrug>() {
            @Override
            public GenericDrug mapRow(ResultSet row, int i) throws SQLException {
                GenericDrug g = new GenericDrug();
                g.setGen_id(row.getInt("gen_id"));
                g.setName(row.getString("name"));
                return g;
            }
        });
    }

    public List<GenericDrug> listGenDrugsWithNameLike(String name) {
        String query = "select * from genericDrugs where name like '%"+name+"%'";
        return jt.query(query, new RowMapper<GenericDrug>() {
            @Override
            public GenericDrug mapRow(ResultSet row, int i) throws SQLException {
                GenericDrug g = new GenericDrug();
                g.setGen_id(row.getInt("gen_id"));
                g.setName(row.getString("name"));
                return g;
            }
        });
    }

    public Drugs getDrugByID(int drug_id) {
        String query = "select * from drugs where drug_id="+drug_id;
        return jt.queryForObject(query, new RowMapper<Drugs>() {
            @Override
            public Drugs mapRow(ResultSet row, int i) throws SQLException {
                Drugs u = new Drugs();
                u.setDrug_id(row.getInt("drug_id"));
                u.setGen_id(row.getInt("gen_id"));
                u.setName(row.getString("name"));
                u.setIs_generic(row.getInt("is_generic"));
                return u;
            }
        });
    }

    public GenericDrug getGenDrugByID(int gen_id) {
        String query = "select * from genericDrugs where gen_id="+gen_id;
        return jt.queryForObject(query, new RowMapper<GenericDrug>() {
            @Override
            public GenericDrug mapRow(ResultSet row, int i) throws SQLException {
                GenericDrug g = new GenericDrug();
                g.setGen_id(row.getInt("gen_id"));
                g.setName(row.getString("name"));
                return g;
            }
        });
    }

    public List<DrugComposition> getGenDrugCompositionByID(int gen_id) {
        String query = "select * from genericDrugComposition as g, drugComponents as d where g.comp_id=d.comp_id and g.gen_id="+gen_id;
        List<Map<String,Object>> rs = jt.queryForList(query);
        List<DrugComposition> res = new ArrayList<DrugComposition>();
        for ( Map<String,Object> r:rs) {
            DrugComposition d = new DrugComposition();
            d.setComp_name((String) r.get("comp_name"));
            d.setComp_id((int) r.get("comp_id"));
            d.setPercent((BigDecimal) r.get("percent"));
            res.add(d);
        }
        return res;
    }

    public void saveDrug(String name, String mf_name, int is_generic, int gen_id){
        String query = "insert into drugs(name, mf_name, is_generic, gen_id) values (?,?,?,?)";
        jt.update(query, name, mf_name, is_generic, gen_id);
    }

    public List<String> getMfNameByIsGen(int is_gen){
        String query = "select distinct mf_name from drugs where is_generic="+is_gen;
        List<String> res = new ArrayList<String>();
        List<Map<String,Object>> rs = jt.queryForList(query);
        for( Map<String,Object> r:rs ) {
            String s = (String) r.get("mf_name");
            res.add(s);
        }
        return res;
    }

    public List<String> getDrugNameByOther(int is_gen, String mf_name){
        String query = "select distinct name from drugs where is_generic=? and mf_name=?";
        List<String> res = new ArrayList<String>();
        List<Map<String,Object>> rs = jt.queryForList(query, is_gen, mf_name);
        for( Map<String,Object> r:rs ) {
            String s = (String) r.get("name");
            res.add(s);
        }
        return res;
    }

    public int getDrugIDByOther(int is_gen, String mf_name, String drug_name){
        String query = "select drug_id from drugs where is_generic=? and mf_name=? and name=?";
        return jt.queryForObject(query, new Object[]{is_gen, mf_name, drug_name}, Integer.class);
    }

}
