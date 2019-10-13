package com.genmed.genmed.model;

import java.util.Date;

public class DrugBatch {

    private int batch_id;
    private String batch_no;
    private Date mfg_date;
    private Date exp_date;

    private int drug_id;
    private Drugs drug;

    public int getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(int batch_id) {
        this.batch_id = batch_id;
    }

    public String getBatch_no() {
        return batch_no;
    }

    public void setBatch_no(String batch_no) {
        this.batch_no = batch_no;
    }

    public Date getMfg_date() {
        return mfg_date;
    }

    public void setMfg_date(Date mfg_date) {
        this.mfg_date = mfg_date;
    }

    public Date getExp_date() {
        return exp_date;
    }

    public void setExp_date(Date exp_date) {
        this.exp_date = exp_date;
    }

    public int getDrug_id() {
        return drug_id;
    }

    public void setDrug_id(int drug_id) {
        this.drug_id = drug_id;
    }

    public Drugs getDrug() {
        return drug;
    }

    public void setDrug(Drugs drug) {
        this.drug = drug;
    }
}
