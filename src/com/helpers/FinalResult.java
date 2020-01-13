package com.helpers;

import javax.json.bind.annotation.JsonbNillable;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import java.util.List;

@JsonbNillable
@JsonbPropertyOrder({"subscriberNumber","status_code","status_description","dataBalance_types","unlimited_balance_types"})
public class FinalResult {
    private String subscriberNumber;

    public FinalResult(String subscriberNumber, String status_code, String status_description, List<Unlimited_Balance_Types> unlimited_balance_types, List<Data_Balance_Type> dataBalance_types) {
        this.subscriberNumber = subscriberNumber;
        this.status_code = status_code;
        this.status_description = status_description;
        this.unlimited_balance_types = unlimited_balance_types;
        this.dataBalance_types = dataBalance_types;
    }

    private String status_code;

    public String getStatus_description() {
        return status_description;
    }

    public void setStatus_description(String status_description) {
        this.status_description = status_description;
    }

    private String status_description;

    public List<Unlimited_Balance_Types> getUnlimited_balance_types() {
        return unlimited_balance_types;
    }

    public void setUnlimited_balance_types(List<Unlimited_Balance_Types> unlimited_balance_types) {
        this.unlimited_balance_types = unlimited_balance_types;
    }

    @JsonbProperty(value = "unlimited_balances")
    private List<Unlimited_Balance_Types> unlimited_balance_types;


    @JsonbProperty(value = "data_balances")
    private List<Data_Balance_Type> dataBalance_types;

    public FinalResult() {
    }

    public FinalResult(String subscriberNumber, String status_code, List<Data_Balance_Type> dataBalance_types) {
        this.subscriberNumber = subscriberNumber;
        this.status_code = status_code;
        this.dataBalance_types = dataBalance_types;
    }

    public String getSubscriberNumber() {
        return subscriberNumber;
    }

    public void setSubscriberNumber(String subscriberNumber) {
        this.subscriberNumber = subscriberNumber;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public List<Data_Balance_Type> getDataBalance_types() {
        return dataBalance_types;
    }

    public void setDataBalance_types(List<Data_Balance_Type> dataBalance_types) {
        this.dataBalance_types = dataBalance_types;
    }
}
