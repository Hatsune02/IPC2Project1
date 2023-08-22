package entities.module;

import entities.OBJECTS;
import java.sql.*;

public class Receptionist extends User{
    private int libraryID;

    public Receptionist() {}

    public Receptionist(int id) {
        super(id);
    }

    public Receptionist(String name, String username, String password, String email, int libraryID) {
        super(name, username, password, email);
        this.libraryID = libraryID;
    }

    public Receptionist(int id, String name, String username, String password, String email, int libraryID) {
        super(id, name, username, password, email);
        this.libraryID = libraryID;
    }

    public int getLibraryID() {return libraryID;}
    public void setLibraryID(int libraryID) {this.libraryID = libraryID;}

    public String toString() {
        return "Receptionist{"+
                "id=" + getId() +
                ", name=" + getName() + '\'' +
                ", username=" + getUsername() +
                ", password=" + getPassword() +
                ", email=" + getEmail() +
                ", libraryID=" + libraryID +
                '}';
    }
}
