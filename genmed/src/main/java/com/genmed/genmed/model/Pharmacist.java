package com.genmed.genmed.model;

import java.util.Date;
import java.util.List;

public class Pharmacist {

    enum Degree {
        BPharm, MPharm;
    }

    private class Qualifications {
        Degree degree;
        String college_name;
        String year_of_passing;
    }

    private String registration_no;
    private Date date_of_first_reg;
    private String registration_council;
    private Integer pharmacist_id;
    private Integer shop_id;
    private Integer user_id;
    private List<Qualifications> quals;
}
