package com.loopy.domain.parkinglot;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String zipcode;

    private String city;

    private String town;

    private String street;

    private String detail;

    private String latitude;

    private String longtitude;
}
