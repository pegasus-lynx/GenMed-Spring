package com.genmed.genmed.model;

import java.util.List;

public class GenericDrug {

    private Integer gen_id;
    private String name;

    private List<DrugComposition> composition;

    public Integer getGen_id() {
        return gen_id;
    }

    public void setGen_id(Integer gen_id) {
        this.gen_id = gen_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DrugComposition> getComposition() {
        return composition;
    }

    public void setComposition(List<DrugComposition> composition) {
        this.composition = composition;
    }
}
