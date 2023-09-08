package com.example.demo1.entities.objects_process;

import java.sql.Date;

public class LoanApplication {
    private int id;
    private int libraryId, receptionistId, finalUserId, bookIsbn;
    private Date loanApplicationDate;
    private String state,deliveryType;
    private int carrierId;
    private String library,receptionist,finalUser,book,carrier;

    public LoanApplication() {
    }
    public LoanApplication(int id) {
        this.id = id;
    }
    public LoanApplication(int libraryId, int receptionistId, int finalUserId, int bookIsbn, Date loanApplicationDate, String state, String deliveryType, int carrierId) {
        this.libraryId = libraryId;
        this.receptionistId = receptionistId;
        this.finalUserId = finalUserId;
        this.bookIsbn = bookIsbn;
        this.loanApplicationDate = loanApplicationDate;
        this.state = state;
        this.deliveryType = deliveryType;
        this.carrierId = carrierId;
    }
    public LoanApplication(int id, int libraryId, int receptionistId, int finalUserId, int bookIsbn, Date loanApplicationDate, String state, String deliveryType, int carrierId) {
        this.id = id;
        this.libraryId = libraryId;
        this.receptionistId = receptionistId;
        this.finalUserId = finalUserId;
        this.bookIsbn = bookIsbn;
        this.loanApplicationDate = loanApplicationDate;
        this.state = state;
        this.deliveryType = deliveryType;
        this.carrierId = carrierId;
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
    public int getCarrierId() {return carrierId;}
    public void setCarrierId(int carrierId) {this.carrierId = carrierId;}
    public Date getLoanApplicationDate() {return loanApplicationDate;}
    public void setLoanApplicationDate(Date loanApplicationDate) {this.loanApplicationDate = loanApplicationDate;}
    public String getState() {return state;}
    public void setState(String state) {this.state = state;}
    public String getDeliveryType() {return deliveryType;}
    public void setDeliveryType(String deliveryType) {this.deliveryType = deliveryType;}

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

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    @Override
    public String toString() {
        return "LoanApplication{" +
                "id=" + id +
                ", libraryId=" + libraryId +
                ", receptionistId=" + receptionistId +
                ", finalUserId=" + finalUserId +
                ", bookIsbn=" + bookIsbn +
                ", loanApplicationDate=" + loanApplicationDate +
                ", state=" + state +
                ", deliveryType=" + deliveryType +
                ", carrierId=" + carrierId +
                '}';
    }
}
