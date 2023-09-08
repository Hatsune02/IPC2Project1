package com.example.demo1.entities.reports;

import java.sql.Date;

public class Loans30Days {
    private int id;
    private String library;
    private String receptionist;
    private String user;
    private String book;
    private Date date;
    private String state;

    public Loans30Days(int id, String library, String receptionist, String user, String book, Date date, String state) {
        this.id = id;
        this.library = library;
        this.receptionist = receptionist;
        this.user = user;
        this.book = book;
        this.date = date;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }

    public String getReceptionist() {
        return receptionist;
    }

    public void setReceptionist(String receptionist) {
        this.receptionist = receptionist;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
