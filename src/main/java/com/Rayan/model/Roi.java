package com.Rayan.model;
import jakarta.persistence.Embeddable;

@Embeddable
public class Roi {

    private Double times;
    private String currency;
    private Double percentage;

    // getters & setters
}
