package com.helpers;

public class AccountBalanceResult {
    private int status;
    private String reason;
    private String message;
    private String subscriberNumber;
    private String accountState;
    private String accountType;

    private AccountBalance account_balance;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubscriberNumber() {
        return subscriberNumber;
    }

    public void setSubscriberNumber(String subscriberNumber) {
        this.subscriberNumber = subscriberNumber;
    }

    public String getAccountState() {
        return accountState;
    }

    public void setAccountState(String accountState) {
        this.accountState = accountState;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public AccountBalance getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(AccountBalance account_balance) {
        this.account_balance = account_balance;
    }
}
