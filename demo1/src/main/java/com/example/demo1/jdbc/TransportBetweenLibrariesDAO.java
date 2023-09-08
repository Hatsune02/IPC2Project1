package com.example.demo1.jdbc;
import static com.example.demo1.jdbc.Conexion.*;
import com.example.demo1.entities.objects_process.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class TransportBetweenLibrariesDAO {
    private static final String SQL_SELECT = "select t.id,t.libraryO,l.library_name,t.libraryD,ll.library_name,t.receptionist,r.receptionist_name,t.carrier,c.carrier_name,transport_date,state from transport_between_libraries as t\n" +
            "join libraries as l on l.id = t.libraryO join receptionists as r on t.receptionist = r.id join carriers as c on c.id = t.carrier join libraries as ll on ll.id = t.libraryD;";
    private static final String SQL_INSERT = "insert into transport_between_libraries(libraryO,libraryD,receptionist,carrier,transport_date,state) value(?,?,?,?,?,?)";
    private static final String SQL_INSERT1 = "insert into transport_between_libraries(id,libraryO,libraryD,receptionist,carrier,transport_date,state) value(?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "update transport_between_libraries set libraryO=?,libraryD=?,receptionist=?,carrier=?,transport_date=?,state=? where id=?";
    private static final String SQL_UPDATE_STATE = "update transport_between_libraries set state=? where id=?";
    private static final String SQL_DELETE = "delete from transport_between_libraries where id=?";
    private static final String SQL_SELECT_WAIT = "select t.id,t.libraryO,l.library_name,t.libraryD,ll.library_name,t.receptionist,r.receptionist_name,t.carrier,c.carrier_name,transport_date,state from transport_between_libraries as t\n" +
            "join libraries as l on l.id = t.libraryO join receptionists as r on t.receptionist = r.id join carriers as c on c.id = t.carrier join libraries as ll on ll.id = t.libraryD where t.libraryD = ? and state = 'EN ESPERA';";
    private static final String SQL_SEARCH_WAIT = "select t.id,t.libraryO,l.library_name,t.libraryD,ll.library_name,t.receptionist,r.receptionist_name,t.carrier,c.carrier_name,transport_date,state from transport_between_libraries as t\n" +
            "join libraries as l on l.id = t.libraryO join receptionists as r on t.receptionist = r.id join carriers as c on c.id = t.carrier join libraries as ll on ll.id = t.libraryD where t.libraryD = ? and state = 'EN ESPERA' " +
            "and (t.id like ? or l.library_name like ? or r.receptionist_name like ? or c.carrier_name like ? or transport_date like ?);";
    private static final String SQL_SELECT_PER_CARRIER = "select t.id,t.libraryO,l.library_name,t.libraryD,ll.library_name,t.receptionist,r.receptionist_name,t.carrier,c.carrier_name,transport_date,state from transport_between_libraries as t\n" +
            "join libraries as l on l.id = t.libraryO join receptionists as r on t.receptionist = r.id join carriers as c on c.id = t.carrier join libraries as ll on ll.id = t.libraryD where t.carrier = ? and state = 'PENDIENTE' ;";
    private static final String SQL_SEARCH_PER_CARRIER = "select t.id,t.libraryO,l.library_name,t.libraryD,ll.library_name,t.receptionist,r.receptionist_name,t.carrier,c.carrier_name,transport_date,state from transport_between_libraries as t\n" +
            "join libraries as l on l.id = t.libraryO join receptionists as r on t.receptionist = r.id join carriers as c on c.id = t.carrier join libraries as ll on ll.id = t.libraryD where t.carrier = ? and state = 'PENDIENTE' " +
            "and (t.id like ? or l.library_name like ? or ll.library_name like ? or transport_date like ?);";
    private static final String SQL_SELECT_PER_CARRIER_OTW = "select t.id,t.libraryO,l.library_name,t.libraryD,ll.library_name,t.receptionist,r.receptionist_name,t.carrier,c.carrier_name,transport_date,state from transport_between_libraries as t\n" +
            "join libraries as l on l.id = t.libraryO join receptionists as r on t.receptionist = r.id join carriers as c on c.id = t.carrier join libraries as ll on ll.id = t.libraryD where t.carrier = ? and state = 'EN CAMINO' ;";
    private static final String SQL_SEARCH_PER_CARRIER_OTW = "select t.id,t.libraryO,l.library_name,t.libraryD,ll.library_name,t.receptionist,r.receptionist_name,t.carrier,c.carrier_name,transport_date,state from transport_between_libraries as t\n" +
            "join libraries as l on l.id = t.libraryO join receptionists as r on t.receptionist = r.id join carriers as c on c.id = t.carrier join libraries as ll on ll.id = t.libraryD where t.carrier = ? and state = 'EN CAMINO' " +
            "and (t.id like ? or l.library_name like ? or ll.library_name like ? or transport_date like ?);";


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
                int id = rs.getInt("t.id");
                int libraryO = rs.getInt("t.libraryO");
                int libraryD = rs.getInt("t.libraryD");
                int receptionist = rs.getInt("t.receptionist");
                int carrier = rs.getInt("t.carrier");
                Date transportDate = rs.getDate("transport_date");
                String state = rs.getString("state");
                TransportBetweenLibraries transport = new TransportBetweenLibraries(id,libraryO,libraryD,receptionist,carrier,transportDate,state);
                transport.setLibraryO(rs.getString("l.library_name"));
                transport.setLibraryD(rs.getString("ll.library_name"));
                transport.setReceptionist(rs.getString("r.receptionist_name"));
                transport.setCarrier(rs.getString("c.carrier_name"));
                transportBetweenLibraries.add(transport);

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
            ps.setInt(1,transportBetweenLibraries.getLibraryOId());
            ps.setInt(2,transportBetweenLibraries.getLibraryDId());
            ps.setInt(3,transportBetweenLibraries.getReceptionistId());
            ps.setInt(4,transportBetweenLibraries.getCarrierId());
            ps.setDate(5,transportBetweenLibraries.getTransportDate());
            ps.setString(6,transportBetweenLibraries.getState());
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
    public int insertId(TransportBetweenLibraries transportBetweenLibraries){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT1);
            ps.setInt(1,transportBetweenLibraries.getId());
            ps.setInt(2,transportBetweenLibraries.getLibraryOId());
            ps.setInt(3,transportBetweenLibraries.getLibraryDId());
            ps.setInt(4,transportBetweenLibraries.getReceptionistId());
            ps.setInt(5,transportBetweenLibraries.getCarrierId());
            ps.setDate(6,transportBetweenLibraries.getTransportDate());
            ps.setString(7,transportBetweenLibraries.getState());
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
            ps.setInt(1,transportBetweenLibraries.getLibraryOId());
            ps.setInt(2,transportBetweenLibraries.getLibraryDId());
            ps.setInt(3,transportBetweenLibraries.getReceptionistId());
            ps.setInt(4,transportBetweenLibraries.getCarrierId());
            ps.setDate(5,transportBetweenLibraries.getTransportDate());
            ps.setString(6,transportBetweenLibraries.getState());
            ps.setInt(7,transportBetweenLibraries.getId());
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
    public int updateState(TransportBetweenLibraries transportBetweenLibraries){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE_STATE);
            ps.setString(1,transportBetweenLibraries.getState());
            ps.setInt(2,transportBetweenLibraries.getId());
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
    public TransportBetweenLibraries selectRecent(){
        TransportBetweenLibraries transportB = new TransportBetweenLibraries();
        int id = 1;
        for(TransportBetweenLibraries transportB1 : select()){
            if(transportB1.getId() > id) id = transportB1.getId();
        }
        for(TransportBetweenLibraries transportB1 : select()){
            if(transportB1.getId() == id) transportB = transportB1;
        }
        return transportB;
    }
    public List<TransportBetweenLibraries> selectWait(int libId){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TransportBetweenLibraries> transportBetweenLibraries = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_WAIT);
            ps.setInt(1,libId);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("t.id");
                int libraryO = rs.getInt("t.libraryO");
                int libraryD = rs.getInt("t.libraryD");
                int receptionist = rs.getInt("t.receptionist");
                int carrier = rs.getInt("t.carrier");
                Date transportDate = rs.getDate("transport_date");
                String state = rs.getString("state");
                TransportBetweenLibraries transport = new TransportBetweenLibraries(id,libraryO,libraryD,receptionist,carrier,transportDate,state);
                transport.setLibraryO(rs.getString("l.library_name"));
                transport.setLibraryD(rs.getString("ll.library_name"));
                transport.setReceptionist(rs.getString("r.receptionist_name"));
                transport.setCarrier(rs.getString("c.carrier_name"));
                transportBetweenLibraries.add(transport);

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
    public List<TransportBetweenLibraries> searchWait(int libId,String text){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TransportBetweenLibraries> transportBetweenLibraries = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SEARCH_WAIT);
            ps.setInt(1,libId);
            ps.setString(2,text + '%');
            ps.setString(3,text + '%');
            ps.setString(4,text + '%');
            ps.setString(5,text + '%');
            ps.setString(6,text + '%');
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("t.id");
                int libraryO = rs.getInt("t.libraryO");
                int libraryD = rs.getInt("t.libraryD");
                int receptionist = rs.getInt("t.receptionist");
                int carrier = rs.getInt("t.carrier");
                Date transportDate = rs.getDate("transport_date");
                String state = rs.getString("state");
                TransportBetweenLibraries transport = new TransportBetweenLibraries(id,libraryO,libraryD,receptionist,carrier,transportDate,state);
                transport.setLibraryO(rs.getString("l.library_name"));
                transport.setLibraryD(rs.getString("ll.library_name"));
                transport.setReceptionist(rs.getString("r.receptionist_name"));
                transport.setCarrier(rs.getString("c.carrier_name"));
                transportBetweenLibraries.add(transport);

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
    public List<TransportBetweenLibraries> selectPerCarrier(int carrierId){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TransportBetweenLibraries> transportBetweenLibraries = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_PER_CARRIER);
            ps.setInt(1,carrierId);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("t.id");
                int libraryO = rs.getInt("t.libraryO");
                int libraryD = rs.getInt("t.libraryD");
                int receptionist = rs.getInt("t.receptionist");
                int carrier = rs.getInt("t.carrier");
                Date transportDate = rs.getDate("transport_date");
                String state = rs.getString("state");
                TransportBetweenLibraries transport = new TransportBetweenLibraries(id,libraryO,libraryD,receptionist,carrier,transportDate,state);
                transport.setLibraryO(rs.getString("l.library_name"));
                transport.setLibraryD(rs.getString("ll.library_name"));
                transport.setReceptionist(rs.getString("r.receptionist_name"));
                transport.setCarrier(rs.getString("c.carrier_name"));
                transportBetweenLibraries.add(transport);

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
    public List<TransportBetweenLibraries> searchPerCarrier(int carrierId,String text){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TransportBetweenLibraries> transportBetweenLibraries = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SEARCH_PER_CARRIER);
            ps.setInt(1,carrierId);
            ps.setString(2,text + '%');
            ps.setString(3,text + '%');
            ps.setString(4,text + '%');
            ps.setString(5,text + '%');
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("t.id");
                int libraryO = rs.getInt("t.libraryO");
                int libraryD = rs.getInt("t.libraryD");
                int receptionist = rs.getInt("t.receptionist");
                int carrier = rs.getInt("t.carrier");
                Date transportDate = rs.getDate("transport_date");
                String state = rs.getString("state");
                TransportBetweenLibraries transport = new TransportBetweenLibraries(id,libraryO,libraryD,receptionist,carrier,transportDate,state);
                transport.setLibraryO(rs.getString("l.library_name"));
                transport.setLibraryD(rs.getString("ll.library_name"));
                transport.setReceptionist(rs.getString("r.receptionist_name"));
                transport.setCarrier(rs.getString("c.carrier_name"));
                transportBetweenLibraries.add(transport);

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
    public List<TransportBetweenLibraries> selectPerCarrierOTW(int carrierId){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TransportBetweenLibraries> transportBetweenLibraries = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_PER_CARRIER_OTW);
            ps.setInt(1,carrierId);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("t.id");
                int libraryO = rs.getInt("t.libraryO");
                int libraryD = rs.getInt("t.libraryD");
                int receptionist = rs.getInt("t.receptionist");
                int carrier = rs.getInt("t.carrier");
                Date transportDate = rs.getDate("transport_date");
                String state = rs.getString("state");
                TransportBetweenLibraries transport = new TransportBetweenLibraries(id,libraryO,libraryD,receptionist,carrier,transportDate,state);
                transport.setLibraryO(rs.getString("l.library_name"));
                transport.setLibraryD(rs.getString("ll.library_name"));
                transport.setReceptionist(rs.getString("r.receptionist_name"));
                transport.setCarrier(rs.getString("c.carrier_name"));
                transportBetweenLibraries.add(transport);

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
    public List<TransportBetweenLibraries> searchPerCarrierOTW(int carrierId,String text){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TransportBetweenLibraries> transportBetweenLibraries = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SEARCH_PER_CARRIER_OTW);
            ps.setInt(1,carrierId);
            ps.setString(2,text + '%');
            ps.setString(3,text + '%');
            ps.setString(4,text + '%');
            ps.setString(5,text + '%');
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("t.id");
                int libraryO = rs.getInt("t.libraryO");
                int libraryD = rs.getInt("t.libraryD");
                int receptionist = rs.getInt("t.receptionist");
                int carrier = rs.getInt("t.carrier");
                Date transportDate = rs.getDate("transport_date");
                String state = rs.getString("state");
                TransportBetweenLibraries transport = new TransportBetweenLibraries(id,libraryO,libraryD,receptionist,carrier,transportDate,state);
                transport.setLibraryO(rs.getString("l.library_name"));
                transport.setLibraryD(rs.getString("ll.library_name"));
                transport.setReceptionist(rs.getString("r.receptionist_name"));
                transport.setCarrier(rs.getString("c.carrier_name"));
                transportBetweenLibraries.add(transport);

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
}
