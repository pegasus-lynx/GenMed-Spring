package com.genmed.genmed.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

public class DrugComposition {

    private int comp_id;
    private String comp_name;
    private BigDecimal percent;
    private int gen_id;

    public int getComp_id() {
        return comp_id;
    }

    public void setComp_id(int comp_id) {
        this.comp_id = comp_id;
    }

    public String getComp_name() {
        return comp_name;
    }

    public void setComp_name(String comp_name) {
        this.comp_name = comp_name;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public int getGen_id() {
        return gen_id;
    }

    public void setGen_id(int gen_id) {
        this.gen_id = gen_id;
    }
}
