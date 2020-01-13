package com.helpers;

import javax.json.bind.annotation.JsonbNillable;
import javax.json.bind.annotation.JsonbNumberFormat;
import javax.json.bind.annotation.JsonbPropertyOrder;


@JsonbNillable()
@JsonbPropertyOrder({"name","balance","expiry"})
public class Data_Balance_Type {

    private String name;

    @JsonbNumberFormat("#.###")
    private double balance;
    private String expiry_date;

    public Data_Balance_Type() {
    }

    public Data_Balance_Type(String name, double balance, String expiry_date) {
        this.name = name;
        this.balance = balance;
        this.expiry_date = expiry_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }
}
