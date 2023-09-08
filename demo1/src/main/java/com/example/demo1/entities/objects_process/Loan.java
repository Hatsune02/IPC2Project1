package com.example.demo1.entities.objects_process;
import java.sql.*;

public class Loan {
    private int id, libraryId, receptionistId, finalUserId, bookIsbn;
    private Date loanDate;
    private String state;
    private double penaltyFee = 0;
    private String library, receptionist,finalUser,book;

    public Loan() {
    }
    public Loan(int id) {
        this.id = id;
    }
    public Loan(int libraryId, int receptionistId, int finalUserId, int bookIsbn, Date loanDate, String state, double penaltyFee) {
        this.libraryId = libraryId;
        this.receptionistId = receptionistId;
        this.finalUserId = finalUserId;
        this.bookIsbn = bookIsbn;
        this.loanDate = loanDate;
        this.state = state;
        this.penaltyFee = penaltyFee;
    }
    public Loan(int id, int libraryId, int receptionistId, int finalUserId, int bookIsbn,Date loanDate, String state, double penaltyFee) {
        this.id = id;
        this.libraryId = libraryId;
        this.receptionistId = receptionistId;
        this.finalUserId = finalUserId;
        this.bookIsbn = bookIsbn;
        this.loanDate = loanDate;
        this.state = state;
        this.penaltyFee = penaltyFee;
    }

    public int getId() {return id;}
    public int getLibraryId() {return libraryId;}
    public void setLibraryId(int libraryId) {this.libraryId = libraryId;}
    public int getReceptionistId() {return receptionistId;}
    public void setReceptionistId(int receptionistId) {this.receptionistId = receptionistId;}
    public int getFinalUserId() {return finalUserId;}
    public void setFinalUserId(int finalUserId) {this.finalUserId = finalUserId;}
    public int getBookIsbn() {return bookIsbn;}
    public void setBookIsbn(int bookIsbn) {this.bookIsbn = bookIsbn;}
    public Date getLoanDate() {return loanDate;}
    public void setLoanDate(Date loanDate) {this.loanDate = loanDate;}
    public String getState() {return state;}
    public void setState(String state) {this.state = state;}
    public double getPenaltyFee() {return penaltyFee;}
    public void setPenaltyFee(double penaltyFee) {this.penaltyFee = penaltyFee;}

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

    public String getFinalUser() {
        return finalUser;
    }

    public void setFinalUser(String finalUser) {
        this.finalUser = finalUser;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", libraryId=" + libraryId +
                ", receptionistId=" + receptionistId +
                ", finalUserId=" + finalUserId +
                ", bookIsbn=" + bookIsbn +
                ", loanDate=" + loanDate +
                ", state=" + state +
                ", penaltyFee=" + penaltyFee +
                '}';
    }
}
