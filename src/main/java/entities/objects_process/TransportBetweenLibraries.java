package entities.objects_process;

import java.sql.Date;

public class TransportBetweenLibraries {
    private int id;
    private int libraryId, receptionistId, carrierId;
    private Date transportDate;
    private String state;

    public TransportBetweenLibraries() {
    }
    public TransportBetweenLibraries(int id) {
        this.id = id;

    }
    public TransportBetweenLibraries(int libraryId, int receptionistId, int carrierId, Date transportDate, String state) {
        this.libraryId = libraryId;
        this.receptionistId = receptionistId;
        this.carrierId = carrierId;
        this.transportDate = transportDate;
        this.state = state;
    }
    public TransportBetweenLibraries(int id, int libraryId, int receptionistId, int carrierId, Date transportDate, String state) {
        this.id = id;
        this.libraryId = libraryId;
        this.receptionistId = receptionistId;
        this.carrierId = carrierId;
        this.transportDate = transportDate;
        this.state = state;
    }

    public int getId() {return id;}
    public int getLibraryId() {return libraryId;}
    public void setLibraryId(int libraryId) {this.libraryId = libraryId;}
    public int getReceptionistId() {return receptionistId;}
    public void setReceptionistId(int receptionistId) {this.receptionistId = receptionistId;}
    public int getCarrierId() {return carrierId;}
    public void setCarrierId(int carrierId) {this.carrierId = carrierId;}
    public Date getTransportDate() {return transportDate;}
    public void setTransportDate(Date transportDate) {this.transportDate = transportDate;}
    public String getState() {return state;}
    public void setState(String state) {this.state = state;}

    @Override
    public String toString() {
        return "TransportBetweenLibraries{" +
                "id=" + id +
                ", libraryId=" + libraryId +
                ", receptionistId=" + receptionistId +
                ", carrierId=" + carrierId +
                ", transportDate=" + transportDate +
                ", state=" + state +
                '}';
    }
}
