package objects.objets_process;

import java.util.Date;

public class LoanApplication {
    private final int ID;
    private int libraryID,receptionistID,finalUserID,bookISBN,carrierID;
    private Date loanApplicationDate;
    private String state,deliveryType;

    public LoanApplication(int ID, int libraryID, int receptionistID, int finalUserID, int bookISBN, int carrierID, Date loanApplicationDate, String state, String deliveryType) {
        this.ID = ID;
        this.libraryID = libraryID;
        this.receptionistID = receptionistID;
        this.finalUserID = finalUserID;
        this.bookISBN = bookISBN;
        this.carrierID = carrierID;
        this.loanApplicationDate = loanApplicationDate;
        this.state = state;
        this.deliveryType = deliveryType;
    }

    public int getID() {return ID;}
    public int getLibraryID() {return libraryID;}
    public void setLibraryID(int libraryID) {this.libraryID = libraryID;}
    public int getReceptionistID() {return receptionistID;}
    public void setReceptionistID(int receptionistID) {this.receptionistID = receptionistID;}
    public int getFinalUserID() {return finalUserID;}
    public void setFinalUserID(int finalUserID) {this.finalUserID = finalUserID;}
    public int getBookISBN() {return bookISBN;}
    public void setBookISBN(int bookISBN) {this.bookISBN = bookISBN;}
    public int getCarrierID() {return carrierID;}
    public void setCarrierID(int carrierID) {this.carrierID = carrierID;}
    public Date getLoanApplicationDate() {return loanApplicationDate;}
    public void setLoanApplicationDate(Date loanApplicationDate) {this.loanApplicationDate = loanApplicationDate;}
    public String getState() {return state;}
    public void setState(String state) {this.state = state;}
    public String getDeliveryType() {return deliveryType;}
    public void setDeliveryType(String deliveryType) {this.deliveryType = deliveryType;}
}
