package objects.objects_library;

public class RelationLibraryBook {
    private final int libraryID,bookISBN;
    private int existence=0;
    public RelationLibraryBook(int libraryID, int bookISBN, int existence) {
        this.libraryID = libraryID;
        this.bookISBN = bookISBN;
        this.existence = existence;
    }
    public RelationLibraryBook(int libraryID, int bookISBN) {
        this.libraryID = libraryID;
        this.bookISBN = bookISBN;
    }
    public int getLibraryID() {return libraryID;}
    public int getBookISBN() {return bookISBN;}
    public int getExistence() {return existence;}
    public void setExistence(int existence) {this.existence = existence;}
}
