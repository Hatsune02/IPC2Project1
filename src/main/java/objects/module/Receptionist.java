package objects.module;

public class Receptionist extends User{
    private int libraryID;
    public Receptionist(int ID, String name, String username, String password, String email, int libraryID) {
        super(ID, name, username, password, email);
        this.libraryID = libraryID;
    }

    public int getLibraryID() {return libraryID;}
    public void setLibraryID(int libraryID) {this.libraryID = libraryID;}
}
