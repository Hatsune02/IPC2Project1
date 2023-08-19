package objects.objects_library;

public class Category {
    private final int ID;
    private String name,description;

    public Category(int ID, String name, String description) {
        this.ID = ID;
        this.name = name;
        this.description = description;
    }

    public int getID() {return ID;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
}
