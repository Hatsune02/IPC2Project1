package com.example.demo1.entities.objects_process;

public class DetailsTransport {
    private int transportBetweenLibrary, bookIsbn;
    private String book;
    private int units;

    public DetailsTransport() {
    }
    public DetailsTransport(int transportBetweenLibrary, int bookIsbn) {
        this.transportBetweenLibrary = transportBetweenLibrary;
        this.bookIsbn = bookIsbn;
    }
    public DetailsTransport(int units) {
        this.units = units;
    }
    public DetailsTransport(int transportBetweenLibrary, int bookIsbn, int units) {
        this.transportBetweenLibrary = transportBetweenLibrary;
        this.bookIsbn = bookIsbn;
        this.units = units;
    }

    public DetailsTransport(int transportBetweenLibrary, int bookIsbn,String book, int units) {
        this.transportBetweenLibrary = transportBetweenLibrary;
        this.bookIsbn = bookIsbn;
        this.book = book;
        this.units = units;
    }
    public int getTransportBetweenLibrary() {return transportBetweenLibrary;}
    public int getBookIsbn() {return bookIsbn;}
    public int getUnits() {return units;}
    public void setUnits(int units) {this.units = units;}

    public void setTransportBetweenLibrary(int transportBetweenLibrary) {
        this.transportBetweenLibrary = transportBetweenLibrary;
    }

    public void setBookIsbn(int bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getBook() {
        return book;
    }

    @Override
    public String toString() {
        return "DetailsTransport{" +
                "transportBetweenLibrary=" + transportBetweenLibrary +
                ", bookIsbn=" + bookIsbn +
                ", book='" + book + '\'' +
                ", units=" + units +
                '}';
    }
}
