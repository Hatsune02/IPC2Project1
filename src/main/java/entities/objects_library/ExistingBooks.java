package entities.objects_library;

public class ExistingBooks {
    private int libraryID, bookIsbn;
    private int existence=0;
    private String library,book;

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

    public ExistingBooks(int libraryID, String library, int bookIsbn, String book, int existence) {
        this.libraryID = libraryID;
        this.bookIsbn = bookIsbn;
        this.existence = existence;
        this.library = library;
        this.book = book;
    }

    public int getLibraryID() {return libraryID;}
    public int getBookIsbn() {return bookIsbn;}
    public int getExistence() {return existence;}
    public void setExistence(int existence) {this.existence = existence;}

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public void setLibraryID(int libraryID) {
        this.libraryID = libraryID;
    }

    public void setBookIsbn(int bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    @Override
    public String toString() {
        return "ExistingBooks{" +
                "libraryID=" + libraryID +
                ", bookISBN=" + bookIsbn +
                ", existence=" + existence +
                '}';
    }

    public String toStringA(){
        return "ExistingBooks{" +
                "libraryID=" + libraryID +
                ", library=" + library +
                ", bookISBN=" + bookIsbn +
                ", book=" + book +
                ", existence=" + existence +
                '}';
    }
}
