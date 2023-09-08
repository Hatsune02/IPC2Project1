package com.example.demo1.entities.reports;

public class Transactions {
    private int id;
    private double moneySpent;
    private double moneyRecharge;
    private String detail;

    public Transactions(int id, double moneySpent, double moneyRecharge, String detail) {
        this.id = id;
        this.moneySpent = moneySpent;
        this.moneyRecharge = moneyRecharge;
        this.detail = detail;
    }
    public Transactions(double moneySpent, double moneyRecharge, String detail) {
        this.moneySpent = moneySpent;
        this.moneyRecharge = moneyRecharge;
        this.detail = detail;
    }
    public Transactions() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMoneySpent() {
        return moneySpent;
    }

    public void setMoneySpent(double moneySpent) {
        this.moneySpent = moneySpent;
    }

    public double getMoneyRecharge() {
        return moneyRecharge;
    }

    public void setMoneyRecharge(double moneyRecharge) {
        this.moneyRecharge = moneyRecharge;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
