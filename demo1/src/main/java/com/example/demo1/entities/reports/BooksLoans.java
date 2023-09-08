package com.example.demo1.entities.reports;

public class BooksLoans {
    private int isbn;
    private String name;
    private int loans;

    public BooksLoans(int isbn, String name, int loans) {
        this.isbn = isbn;
        this.name = name;
        this.loans = loans;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLoans() {
        return loans;
    }

    public void setLoans(int loans) {
        this.loans = loans;
    }
}
