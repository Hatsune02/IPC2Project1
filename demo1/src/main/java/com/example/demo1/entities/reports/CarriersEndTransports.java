package com.example.demo1.entities.reports;

public class CarriersEndTransports {
    private int id;
    private String name;
    private int endTransports;

    public CarriersEndTransports(int id, String name, int endTransports) {
        this.id = id;
        this.name = name;
        this.endTransports = endTransports;
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

    public int getEndTransports() {
        return endTransports;
    }

    public void setEndTransports(int endTransports) {
        this.endTransports = endTransports;
    }
}
