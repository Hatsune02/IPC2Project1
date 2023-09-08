package com.example.demo1.entities.objects_process;

public class Incident {
    private int id;
    private int loanId;
    private String incidentType;
    private double amountPaid;

    public Incident() {

    }
    public Incident(int id) {
        this.id = id;
    }
    public Incident(int loanId, String incidentType, double amountPaid) {
        this.loanId = loanId;
        this.incidentType = incidentType;
        this.amountPaid = amountPaid;
    }
    public Incident(int id, int loanId, String incidentType, double amountPaid) {
        this.id = id;
        this.loanId = loanId;
        this.incidentType = incidentType;
        this.amountPaid = amountPaid;
    }

    public int getId() {return id;}
    public int getLoanId() {return loanId;}
    public void setLoanId(int loanId) {this.loanId = loanId;}
    public String getIncidentType() {return incidentType;}
    public void setIncidentType(String incidentType) {this.incidentType = incidentType;}
    public double getAmountPaid() {return amountPaid;}
    public void setAmountPaid(double amountPaid) {this.amountPaid = amountPaid;}

    @Override
    public String toString() {
        return "Incident{" +
                "id=" + id +
                ", loanId=" + loanId +
                ", incidentType=" + incidentType +
                ", amountPaid=" + amountPaid +
                '}';
    }
}
