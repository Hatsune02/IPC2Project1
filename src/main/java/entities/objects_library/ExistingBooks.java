package entities.objects_library;

public class ExistingBooks {
    private int libraryID, bookIsbn;
    private int existence=0;

    public ExistingBooks() {
    }
    public ExistingBooks(int libraryID, int bookIsbn) {
        this.libraryID = libraryID;
        this.bookIsbn = bookIsbn;
    }
    public ExistingBooks(int existence) {
        this.existence = existence;
    }
    public ExistingBooks(int libraryID, int bookIsbn, int existence) {
        this.libraryID = libraryID;
        this.bookIsbn = bookIsbn;
        this.existence = existence;
    }

    public int getLibraryID() {return libraryID;}
    public int getBookIsbn() {return bookIsbn;}
    public int getExistence() {return existence;}
    public void setExistence(int existence) {this.existence = existence;}

    @Override
    public String toString() {
        return "ExistingBooks{" +
                "libraryID=" + libraryID +
                ", bookISBN=" + bookIsbn +
                ", existence=" + existence +
                '}';
    }
}
