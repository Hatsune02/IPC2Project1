package entities.objects_process;

public class DetailsTransport {
    private int transportBetweenLibrary, bookIsbn;
    private int units;

    public DetailsTransport() {
    }
    public DetailsTransport(int transportBetweenLibrary, int bookIsbn) {
        this.transportBetweenLibrary = transportBetweenLibrary;
        this.bookIsbn = bookIsbn;
    }
    public DetailsTransport(int units) {
        this.units = units;
    }
    public DetailsTransport(int transportBetweenLibrary, int bookIsbn, int units) {
        this.transportBetweenLibrary = transportBetweenLibrary;
        this.bookIsbn = bookIsbn;
        this.units = units;
    }

    public int getTransportBetweenLibrary() {return transportBetweenLibrary;}
    public int getBookIsbn() {return bookIsbn;}
    public int getUnits() {return units;}
    public void setUnits(int units) {this.units = units;}

    @Override
    public String toString() {
        return "DetailsTransport{" +
                "transportBetweenLibrary=" + transportBetweenLibrary +
                ", bookIsbn=" + bookIsbn +
                ", units=" + units +
                '}';
    }
}
