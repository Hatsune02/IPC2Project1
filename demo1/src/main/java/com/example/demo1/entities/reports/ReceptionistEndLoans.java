package com.example.demo1.entities.reports;

public class ReceptionistEndLoans {
    private int id;
    private String name;
    private int endLoans;

    public ReceptionistEndLoans(int id, String name, int endLoans) {
        this.id = id;
        this.name = name;
        this.endLoans = endLoans;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEndLoans() {
        return endLoans;
    }

    public void setEndLoans(int endLoans) {
        this.endLoans = endLoans;
    }
}
