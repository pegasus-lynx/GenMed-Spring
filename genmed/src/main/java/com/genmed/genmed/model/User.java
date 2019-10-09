package com.genmed.genmed.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

public class User {

    enum Email_Type {
        Primary, Other
    };

    enum Address_Type {
        Home, Work, Other
    }

    private class Email {
        String email_id;
        Email_Type type;
    };

    private class UserAddress {
        Address_Type type;
        Address address;
    }

    private Integer user_id;
    private String first_name;
    private String last_name;
    private List<Email> email_ids;
    private List<String> phone_nos;

    private List<UserAddress> user_addresses;
}
