package objects.objets_process;

public class DetailsTransport {
    private final int transportBetweenLibrary,bookISBN;
    private int units;

    public DetailsTransport(int transportBetweenLibrary, int bookISBN, int units) {
        this.transportBetweenLibrary = transportBetweenLibrary;
        this.bookISBN = bookISBN;
        this.units = units;
    }

    public int getTransportBetweenLibrary() {return transportBetweenLibrary;}
    public int getBookISBN() {return bookISBN;}
    public int getUnits() {return units;}
    public void setUnits(int units) {this.units = units;}
}
