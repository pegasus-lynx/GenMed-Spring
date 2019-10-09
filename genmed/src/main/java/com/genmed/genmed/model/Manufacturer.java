package com.genmed.genmed.model;

public class Manufacturer {

    enum MakeType {
        Generic, Branded, Both;
    }

    private String name;
    private String mfg_license;
    private MakeType type;
    private Integer brand_id;
    private Integer address_id;
}
