package objects.objects_library;

public class Book {
    private final int ISBN;
    private String name,author;
    private double price;
    private int categoryID;

    public Book(int ISBN, String name, String author, double price, int categoryID) {
        this.ISBN = ISBN;
        this.name = name;
        this.author = author;
        this.price = price;
        this.categoryID = categoryID;
    }

    public int getISBN() {return ISBN;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getAuthor() {return author;}
    public void setAuthor(String author) {this.author = author;}
    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}
    public int getCategoryID() {return categoryID;}
    public void setCategoryID(int categoryID) {this.categoryID = categoryID;}
}
