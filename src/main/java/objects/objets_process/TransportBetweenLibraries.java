package objects.objets_process;

import java.util.*;

public class TransportBetweenLibraries {
    private final int ID;
    private int libraryID,receptionistID,carrierID;
    private Date transportDate;
    private String state;

    public TransportBetweenLibraries(int ID, int libraryID, int receptionistID, int carrierID, Date transportDate, String state) {
        this.ID = ID;
        this.libraryID = libraryID;
        this.receptionistID = receptionistID;
        this.carrierID = carrierID;
        this.transportDate = transportDate;
        this.state = state;
    }

    public int getID() {return ID;}
    public int getLibraryID() {return libraryID;}
    public void setLibraryID(int libraryID) {this.libraryID = libraryID;}
    public int getReceptionistID() {return receptionistID;}
    public void setReceptionistID(int receptionistID) {this.receptionistID = receptionistID;}
    public int getCarrierID() {return carrierID;}
    public void setCarrierID(int carrierID) {this.carrierID = carrierID;}
    public Date getTransportDate() {return transportDate;}
    public void setTransportDate(Date transportDate) {this.transportDate = transportDate;}
    public String getState() {return state;}
    public void setState(String state) {this.state = state;}
}
