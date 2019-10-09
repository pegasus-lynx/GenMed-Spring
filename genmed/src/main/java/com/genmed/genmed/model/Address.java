package com.genmed.genmed.model;

public class Address {

    private class ZipMap {
        String pincode;
        String locality;
        String city;
        String district;
        String state;
    }

    private Integer address_id;
    private String plot_no;
    private String street;
    private Double latitude;
    private Double longitude;

}
