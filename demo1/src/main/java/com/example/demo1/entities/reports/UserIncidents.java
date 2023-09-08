package com.example.demo1.entities.reports;

public class UserIncidents {
    private int id;
    private String name;
    private int incidents;

    public UserIncidents(int id, String name, int incidents) {
        this.id = id;
        this.name = name;
        this.incidents = incidents;
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

    public int getIncidents() {
        return incidents;
    }

    public void setIncidents(int incidents) {
        this.incidents = incidents;
    }
}
