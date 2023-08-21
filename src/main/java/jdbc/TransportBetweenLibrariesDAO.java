package jdbc;
import static jdbc.Conexion.*;
import entities.objets_process.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class TransportBetweenLibrariesDAO {
    private static final String SQL_SELECT = "select * from transport_between_libraries";
    private static final String SQL_INSERT = "insert into transport_between_libraries(library,receptionist,carrier,transport_date,state) value(?,?,?,?,?)";
    private static final String SQL_UPDATE = "update transport_between_libraries set library=?,receptionist=?,carrier=?,transport_date=?,state=? where id=?";
    private static final String SQL_DELETE = "delete from transport_between_libraries where id=?";

    public List<TransportBetweenLibraries> select(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TransportBetweenLibraries> transportBetweenLibraries = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                int library = rs.getInt("library");
                int receptionist = rs.getInt("receptionist");
                int carrier = rs.getInt("carrier");
                Date transportDate = rs.getDate("transport_date");
                String state = rs.getString("state");
                transportBetweenLibraries.add(new TransportBetweenLibraries(id,library,receptionist,carrier,transportDate,state));

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
        return transportBetweenLibraries;
    }
    public int insert(TransportBetweenLibraries transportBetweenLibraries){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setInt(1,transportBetweenLibraries.getLibraryId());
            ps.setInt(2,transportBetweenLibraries.getReceptionistId());
            ps.setInt(3,transportBetweenLibraries.getCarrierId());
            ps.setDate(4,transportBetweenLibraries.getTransportDate());
            ps.setString(5,transportBetweenLibraries.getState());
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
    public int update(TransportBetweenLibraries transportBetweenLibraries){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setInt(1,transportBetweenLibraries.getLibraryId());
            ps.setInt(2,transportBetweenLibraries.getReceptionistId());
            ps.setInt(3,transportBetweenLibraries.getCarrierId());
            ps.setDate(4,transportBetweenLibraries.getTransportDate());
            ps.setString(5,transportBetweenLibraries.getState());
            ps.setInt(6,transportBetweenLibraries.getId());
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
    public int delete(TransportBetweenLibraries transportBetweenLibraries){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_DELETE);
            ps.setInt(1,transportBetweenLibraries.getId());
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
