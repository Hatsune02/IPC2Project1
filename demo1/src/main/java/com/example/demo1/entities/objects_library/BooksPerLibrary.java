package com.example.demo1.entities.objects_library;

public class BooksPerLibrary {
    private int libraryID, bookIsbn;
    private int existence=0;
    private String library,book,category,author;
    private double price;

    public BooksPerLibrary() {
    }

    public BooksPerLibrary(int bookIsbn, String book, double price, String category, String author, int existence) {
        this.bookIsbn = bookIsbn;
        this.existence = existence;
        this.book = book;
        this.category = category;
        this.author = author;
        this.price = price;
    }
    public BooksPerLibrary(int libraryID, String library, int bookIsbn, String book, double price, String category, String author, int existence) {
        this.libraryID = libraryID;
        this.bookIsbn = bookIsbn;
        this.existence = existence;
        this.library = library;
        this.book = book;
        this.category = category;
        this.author = author;
        this.price = price;
    }

    public int getLibraryID() {
        return libraryID;
    }

    public void setLibraryID(int libraryID) {
        this.libraryID = libraryID;
    }

    public int getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(int bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public int getExistence() {
        return existence;
    }

    public void setExistence(int existence) {
        this.existence = existence;
    }

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BooksPerLibrary{" +
                "libraryID=" + libraryID +
                ", bookIsbn=" + bookIsbn +
                ", existence=" + existence +
                ", library='" + library + '\'' +
                ", book='" + book + '\'' +
                ", category='" + category + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}
