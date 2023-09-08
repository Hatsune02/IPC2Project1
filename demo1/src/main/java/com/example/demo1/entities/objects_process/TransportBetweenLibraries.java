package com.example.demo1.entities.objects_process;

import java.sql.Date;

public class TransportBetweenLibraries {
    private int id;
    private int libraryOId,libraryDId, receptionistId, carrierId;
    private Date transportDate;
    private String state;
    private String libraryO,libraryD, receptionist, carrier;

    public TransportBetweenLibraries() {
    }
    public TransportBetweenLibraries(int id) {
        this.id = id;

    }
    public TransportBetweenLibraries(int libraryId,int libraryDId, int receptionistId, int carrierId, Date transportDate, String state) {
        this.libraryOId = libraryId;
        this.libraryDId = libraryDId;
        this.receptionistId = receptionistId;
        this.carrierId = carrierId;
        this.transportDate = transportDate;
        this.state = state;
    }
    public TransportBetweenLibraries(int id, int libraryId,int libraryDId, int receptionistId, int carrierId, Date transportDate, String state) {
        this.id = id;
        this.libraryOId = libraryId;
        this.libraryDId = libraryDId;
        this.receptionistId = receptionistId;
        this.carrierId = carrierId;
        this.transportDate = transportDate;
        this.state = state;
    }

    public int getId() {return id;}
    public int getLibraryOId() {return libraryOId;}
    public void setLibraryOId(int libraryOId) {this.libraryOId = libraryOId;}
    public int getReceptionistId() {return receptionistId;}
    public void setReceptionistId(int receptionistId) {this.receptionistId = receptionistId;}
    public int getCarrierId() {return carrierId;}
    public void setCarrierId(int carrierId) {this.carrierId = carrierId;}
    public Date getTransportDate() {return transportDate;}
    public void setTransportDate(Date transportDate) {this.transportDate = transportDate;}
    public String getState() {return state;}
    public void setState(String state) {this.state = state;}

    public void setId(int id) {
        this.id = id;
    }


    public String getReceptionist() {
        return receptionist;
    }

    public void setReceptionist(String receptionist) {
        this.receptionist = receptionist;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public int getLibraryDId() {
        return libraryDId;
    }

    public void setLibraryDId(int libraryDId) {
        this.libraryDId = libraryDId;
    }

    public String getLibraryO() {
        return libraryO;
    }

    public void setLibraryO(String libraryO) {
        this.libraryO = libraryO;
    }

    public String getLibraryD() {
        return libraryD;
    }

    public void setLibraryD(String libraryD) {
        this.libraryD = libraryD;
    }

    @Override
    public String toString() {
        return "TransportBetweenLibraries{" +
                "id=" + id +
                ", libraryOId=" + libraryOId +
                ", libraryDId=" + libraryDId +
                ", receptionistId=" + receptionistId +
                ", carrierId=" + carrierId +
                ", transportDate=" + transportDate +
                ", state='" + state + '\'' +
                ", libraryO='" + libraryO + '\'' +
                ", libraryD='" + libraryD + '\'' +
                ", receptionist='" + receptionist + '\'' +
                ", carrier='" + carrier + '\'' +
                '}';
    }
}
