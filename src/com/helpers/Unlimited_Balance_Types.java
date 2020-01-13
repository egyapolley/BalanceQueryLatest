package com.helpers;

import javax.json.bind.annotation.JsonbNillable;
import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbNillable
@JsonbPropertyOrder({"name","status","expiry"})
public class Unlimited_Balance_Types {

    private String name;
    private String status;
    private String expiry;

    public Unlimited_Balance_Types() {
    }

    public Unlimited_Balance_Types(String name, String status, String expiry) {
        this.name = name;
        this.status = status;
        this.expiry = expiry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }
}
