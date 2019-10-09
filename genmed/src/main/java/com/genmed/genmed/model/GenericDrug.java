package com.genmed.genmed.model;


import java.util.List;

public class GenericDrug {

    private class DrugComposition {
        DrugComp component;
        Double percent;
    }

    private Integer gen_id;
    private String name;

    private List<DrugComposition> composition;
}
