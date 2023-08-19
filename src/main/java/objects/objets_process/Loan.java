package objects.objets_process;

public class Loan {
    private final int ID;
    private int libraryID,receptionistID,finalUserID,bookISBN;
    private String state;
    private double penaltyFee = 0;

    public Loan(int ID, int libraryID, int receptionistID, int finalUserID, int bookISBN, String state, double penaltyFee) {
        this.ID = ID;
        this.libraryID = libraryID;
        this.receptionistID = receptionistID;
        this.finalUserID = finalUserID;
        this.bookISBN = bookISBN;
        this.state = state;
        this.penaltyFee = penaltyFee;
    }
    public Loan(int ID, int libraryID, int receptionistID, int finalUserID, int bookISBN, String state) {
        this.ID = ID;
        this.libraryID = libraryID;
        this.receptionistID = receptionistID;
        this.finalUserID = finalUserID;
        this.bookISBN = bookISBN;
        this.state = state;
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
    public String getState() {return state;}
    public void setState(String state) {this.state = state;}
    public double getPenaltyFee() {return penaltyFee;}
    public void setPenaltyFee(double penaltyFee) {this.penaltyFee = penaltyFee;}
}
