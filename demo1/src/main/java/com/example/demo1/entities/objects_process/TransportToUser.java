package com.example.demo1.entities.objects_process;

import java.sql.Date;

public class TransportToUser {
    private int id;
    private int libraryId, finalUserId, carrierId;
    private Date transportDate;
    private String state;
    private int bookIsbn;
    String library,finalUser,carrier,book;

    public TransportToUser() {
    }
    public TransportToUser(int id) {
        this.id = id;
    }
    public TransportToUser(int libraryId, int finalUserId, int carrierId, Date transportDate, String state, int bookIsbn) {
        this.libraryId = libraryId;
        this.finalUserId = finalUserId;
        this.bookIsbn = bookIsbn;
        this.transportDate = transportDate;
        this.state = state;
        this.carrierId = carrierId;
    }
    public TransportToUser(int id, int libraryId, int finalUserId, int bookIsbn, Date transportDate, String state, int carrierId) {
        this.id = id;
        this.libraryId = libraryId;
        this.finalUserId = finalUserId;
        this.bookIsbn = bookIsbn;
        this.transportDate = transportDate;
        this.state = state;
        this.carrierId = carrierId;
    }

    public int getId() {return id;}
    public int getLibraryId() {return libraryId;}
    public void setLibraryId(int libraryId) {this.libraryId = libraryId;}
    public int getFinalUserId() {return finalUserId;}
    public void setFinalUserId(int finalUserId) {this.finalUserId = finalUserId;}
    public int getCarrierId() {return carrierId;}
    public void setCarrierId(int carrierId) {this.carrierId = carrierId;}
    public int getBookIsbn() {return bookIsbn;}
    public void setBookIsbn(int bookIsbn) {this.bookIsbn = bookIsbn;}
    public Date getTransportDate() {return transportDate;}
    public void setTransportDate(Date transportDate) {this.transportDate = transportDate;}
    public String getState() {return state;}
    public void setState(String state) {this.state = state;}

    public void setId(int id) {
        this.id = id;
    }

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }

    public String getFinalUser() {
        return finalUser;
    }

    public void setFinalUser(String finalUser) {
        this.finalUser = finalUser;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "TransportToUser{" +
                "id=" + id +
                ", libraryId=" + libraryId +
                ", finalUserId=" + finalUserId +
                ", carrierId=" + carrierId +
                ", transportDate=" + transportDate +
                ", state='" + state + '\'' +
                ", bookIsbn=" + bookIsbn +
                ", library='" + library + '\'' +
                ", finalUser='" + finalUser + '\'' +
                ", carrier='" + carrier + '\'' +
                ", book='" + book + '\'' +
                '}';
    }
}
