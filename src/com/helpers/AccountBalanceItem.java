package com.helpers;


import javax.json.bind.annotation.JsonbProperty;

public class AccountBalanceItem {

    private String balance_type;
    private String value;
    private String expiry_date;


    public String getBalance_type() {
        return balance_type;
    }

    public void setBalance_type(String balance_type) {
        this.balance_type = balance_type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }
}
