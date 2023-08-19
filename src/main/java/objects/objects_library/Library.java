package objects.objects_library;

public class Library {
    private final int ID;
    private String name,address;

    public Library(int ID, String name, String address) {
        this.ID = ID;
        this.name = name;
        this.address = address;
    }

    public int getID() {return ID;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}
}
