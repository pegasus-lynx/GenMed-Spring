package com.genmed.genmed.model;

public class UserAddress {

    enum Address_Type {
        Home, Work, Other
    }

    private Address_Type type;
    private Address address;

    public Address_Type getType() {
        return type;
    }

    public void setType(Address_Type type) {
        this.type = type;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
