package entities.module;

import java.sql.*;

public class Receptionist extends User{
    private int libraryID;
    String library;

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
    public Receptionist(int id, String name, String username, String password, String email, int libraryID, String library) {
        super(id, name, username, password, email);
        this.libraryID = libraryID;
        this.library = library;
    }

    public int getLibraryID() {return libraryID;}
    public void setLibraryID(int libraryID) {this.libraryID = libraryID;}

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }

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
