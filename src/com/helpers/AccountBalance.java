package com.helpers;

import java.util.ArrayList;


public class AccountBalance {
    private ArrayList<AccountBalanceItem> cash_balance;
    private ArrayList<AccountBalanceItem> data_balance;
    private ArrayList<AccountBalanceItem> unlimited_balance;

    public ArrayList<AccountBalanceItem> getCash_balance() {
        return cash_balance;
    }

    public void setCash_balance(ArrayList<AccountBalanceItem> cash_balance) {
        this.cash_balance = cash_balance;
    }

    public ArrayList<AccountBalanceItem> getData_balance() {
        return data_balance;
    }

    public void setData_balance(ArrayList<AccountBalanceItem> data_balance) {
        this.data_balance = data_balance;
    }

    public ArrayList<AccountBalanceItem> getUnlimited_balance() {
        return unlimited_balance;
    }

    public void setUnlimited_balance(ArrayList<AccountBalanceItem> unlimited_balance) {
        this.unlimited_balance = unlimited_balance;
    }
}
