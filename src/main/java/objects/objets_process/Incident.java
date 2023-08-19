package objects.objets_process;

public class Incident {
    private final int ID;
    private int loanID;
    private String incidentType;
    private double amountPaid;

    public Incident(int ID, int loanID, String incidentType, double amountPaid) {
        this.ID = ID;
        this.loanID = loanID;
        this.incidentType = incidentType;
        this.amountPaid = amountPaid;
    }

    public int getID() {return ID;}
    public int getLoanID() {return loanID;}
    public void setLoanID(int loanID) {this.loanID = loanID;}
    public String getIncidentType() {return incidentType;}
    public void setIncidentType(String incidentType) {this.incidentType = incidentType;}
    public double getAmountPaid() {return amountPaid;}
    public void setAmountPaid(double amountPaid) {this.amountPaid = amountPaid;}
}
