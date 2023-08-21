package jdbc;
import static jdbc.Conexion.*;
import entities.objets_process.*;
import java.sql.*;
import java.util.*;

public class DetailsTransportDAO {
    private static final String SQL_SELECT = "select * from details_transport";
    private static final String SQL_INSERT = "insert into details_transport(transport_between_library, book, units) value(?,?,?)";
    private static final String SQL_UPDATE = "update details_transport set units=? where transport_between_library=? and book=?";
    private static final String SQL_DELETE = "delete from details_transport where transport_between_library=? and book=?";

    public List<DetailsTransport> select(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DetailsTransport> detailsTransports = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()){
                int transportBetweenLibrary = rs.getInt("transport_between_library");
                int bookIsbn = rs.getInt("book");
                int units = rs.getInt("units");
                detailsTransports.add(new DetailsTransport(transportBetweenLibrary,bookIsbn,units));

            }

        }catch (SQLException e){
            e.printStackTrace(System.out);
        }
        finally {
            try{
                close(rs);
                close(ps);
                close(con);
            }  catch (SQLException e){
                e.printStackTrace(System.out);
            }

        }
        return detailsTransports;
    }
    public int insert(DetailsTransport detailsTransport){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setInt(1,detailsTransport.getTransportBetweenLibrary());
            ps.setInt(2,detailsTransport.getBookIsbn());
            ps.setInt(3,detailsTransport.getUnits());
            records = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        finally {
            try {
                close(ps);
                close(con);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return records;
    }
    public int update(DetailsTransport detailsTransport){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setInt(1,detailsTransport.getUnits());
            ps.setInt(2,detailsTransport.getTransportBetweenLibrary());
            ps.setInt(3,detailsTransport.getBookIsbn());
            records = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        finally {
            try {
                close(ps);
                close(con);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return records;
    }
    public int delete(DetailsTransport detailsTransport){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_DELETE);
            ps.setInt(1,detailsTransport.getTransportBetweenLibrary());
            ps.setInt(2,detailsTransport.getBookIsbn());
            records = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        finally {
            try {
                close(ps);
                close(con);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return records;
    }
}
