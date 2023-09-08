package com.example.demo1.entities.objects_library;

public class Book {
    private int isbn;
    private String name;
    private double price;
    private int categoryID;
    private String author, category;

    public Book() {
    }
    public Book(int isbn) {
        this.isbn = isbn;
    }
    public Book(String name, double price, int categoryID, String author) {
        this.name = name;
        this.price = price;
        this.categoryID = categoryID;
        this.author = author;
    }
    public Book(int isbn, String name, double price, int categoryID, String author) {
        this.isbn = isbn;
        this.name = name;
        this.price = price;
        this.categoryID = categoryID;
        this.author = author;
    }
    public Book(int isbn, String name, double price, int categoryID,String category, String author) {
        this.isbn = isbn;
        this.name = name;
        this.price = price;
        this.categoryID = categoryID;
        this.category = category;
        this.author = author;
    }

    public int getIsbn() {return isbn;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getAuthor() {return author;}
    public void setAuthor(String author) {this.author = author;}
    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}
    public int getCategoryID() {return categoryID;}
    public void setCategoryID(int categoryID) {this.categoryID = categoryID;}

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn +
                ", name=" + name +
                ", price=" + price +
                ", categoryID=" + categoryID +
                ", author=" + author +
                '}';
    }
}
