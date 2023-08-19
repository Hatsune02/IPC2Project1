package objects.objets_process;

import java.util.*;
//import java.sql.*;

public class TransportToUser {
    private final int ID;
    private int libraryID,finalUserID,carrierID,bookISBN;
    private Date transportDate;
    private String state;

    public TransportToUser(int ID, int libraryID, int finalUserID, int carrierID, int bookISBN, Date transportDate, String state) {
        this.ID = ID;
        this.libraryID = libraryID;
        this.finalUserID = finalUserID;
        this.carrierID = carrierID;
        this.bookISBN = bookISBN;
        this.transportDate = transportDate;
        this.state = state;
    }

    public int getID() {return ID;}
    public int getLibraryID() {return libraryID;}
    public void setLibraryID(int libraryID) {this.libraryID = libraryID;}
    public int getFinalUserID() {return finalUserID;}
    public void setFinalUserID(int finalUserID) {this.finalUserID = finalUserID;}
    public int getCarrierID() {return carrierID;}
    public void setCarrierID(int carrierID) {this.carrierID = carrierID;}
    public int getBookISBN() {return bookISBN;}
    public void setBookISBN(int bookISBN) {this.bookISBN = bookISBN;}
    public Date getTransportDate() {return transportDate;}
    public void setTransportDate(Date transportDate) {this.transportDate = transportDate;}
    public String getState() {return state;}
    public void setState(String state) {this.state = state;}
}
