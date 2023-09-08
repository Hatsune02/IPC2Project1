package com.example.demo1.jdbc;
import static com.example.demo1.jdbc.Conexion.*;
import com.example.demo1.entities.objects_process.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class TransportToUserDAO {
    private static final String SQL_SELECT = "select * from transport_users";
    private static final String SQL_INSERT = "insert into transport_users(library,final_user,carrier,transport_date,state,book) value(?,?,?,?,?,?)";
    private static final String SQL_INSERT1 = "insert into transport_users(id,library,final_user,carrier,transport_date,state,book) value(?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "update transport_users set library=?,final_user=?,carrier=?,transport_date=?,state=?,book=? where id=?";
    private static final String SQL_UPDATE_STATE = "update transport_users set state=? where id=?";
    private static final String SQL_DELETE = "delete from transport_users where id=?";
    private static final String SQL_SELECT_PER_CARRIER = "select t.id,t.library, l.library_name, t.final_user, u.user_name, t.carrier, c.carrier_name, transport_date, state, t.book, b.book_name from transport_users as t\n" +
            "join libraries as l on l.id = t.library join final_users as u on u.id = t.final_user join carriers as c on c.id = t.carrier join books as b on b.isbn = t.book where t.carrier = ? and state = 'PENDIENTE';";
    private static final String SQL_SELECT_PER_CARRIEROTW = "select t.id,t.library, l.library_name, t.final_user, u.user_name, t.carrier, c.carrier_name, transport_date, state, t.book, b.book_name from transport_users as t\n" +
            "join libraries as l on l.id = t.library join final_users as u on u.id = t.final_user join carriers as c on c.id = t.carrier join books as b on b.isbn = t.book where t.carrier = ? and state = 'EN CAMINO';";
    private static final String SQL_SEARCH_PENDING = "select t.id,t.library, l.library_name, t.final_user, u.user_name, t.carrier, c.carrier_name, transport_date, state, t.book, b.book_name from transport_users as t\n" +
            "join libraries as l on l.id = t.library join final_users as u on u.id = t.final_user join carriers as c on c.id = t.carrier join books as b on b.isbn = t.book where t.carrier = ? and state = 'PENDIENTE' " +
            "and (t.id like ? or l.library_name like ? or u.user_name like ? or transport_date like ? or b.book_name like ?);";
    private static final String SQL_SEARCH_OTW = "select t.id,t.library, l.library_name, t.final_user, u.user_name, t.carrier, c.carrier_name, transport_date, state, t.book, b.book_name from transport_users as t\n" +
            "join libraries as l on l.id = t.library join final_users as u on u.id = t.final_user join carriers as c on c.id = t.carrier join books as b on b.isbn = t.book where t.carrier = ? and state = 'EN CAMINO' " +
            "and (t.id like ? or l.library_name like ? or u.user_name like ? or transport_date like ? or b.book_name like ?);";

    public List<TransportToUser> select(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TransportToUser> transportToUsers = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                int library = rs.getInt("library");
                int finalUser = rs.getInt("final_user");
                int carrier = rs.getInt("carrier");
                Date transportDate = rs.getDate("transport_date");
                int book = rs.getInt("book");
                String state = rs.getString("state");
                transportToUsers.add(new TransportToUser(id,library,finalUser,carrier,transportDate,state,book));

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
        return transportToUsers;
    }
    public int insert(TransportToUser transportToUser){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setInt(1,transportToUser.getLibraryId());
            ps.setInt(2,transportToUser.getFinalUserId());
            ps.setInt(3,transportToUser.getCarrierId());
            ps.setDate(4,transportToUser.getTransportDate());
            ps.setString(5,transportToUser.getState());
            ps.setInt(6,transportToUser.getBookIsbn());
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
    public int insertId(TransportToUser transportToUser){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT1);
            ps.setInt(1,transportToUser.getId());
            ps.setInt(2,transportToUser.getLibraryId());
            ps.setInt(3,transportToUser.getFinalUserId());
            ps.setInt(4,transportToUser.getCarrierId());
            ps.setDate(5,transportToUser.getTransportDate());
            ps.setString(6,transportToUser.getState());
            ps.setInt(7,transportToUser.getBookIsbn());
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
    public int update(TransportToUser transportToUser){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setInt(1,transportToUser.getLibraryId());
            ps.setInt(2,transportToUser.getFinalUserId());
            ps.setInt(3,transportToUser.getCarrierId());
            ps.setDate(4,transportToUser.getTransportDate());
            ps.setString(5,transportToUser.getState());
            ps.setInt(6,transportToUser.getBookIsbn());
            ps.setInt(7,transportToUser.getId());
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
    public int delete(TransportToUser transportToUser){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_DELETE);
            ps.setInt(1,transportToUser.getId());
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
    public int updateState(TransportToUser transportToUser){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE_STATE);
            ps.setString(1,transportToUser.getState());
            ps.setInt(2,transportToUser.getId());
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

    public TransportToUser selectOne(TransportToUser transport){
        for(TransportToUser t : select()){
            if(t.getId()==transport.getId()) {
                transport = t;
                break;
            }
        }
        return transport;
    }
    public List<TransportToUser> selectPerCarrier(int carrierId){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TransportToUser> transportToUsers = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_PER_CARRIER);
            ps.setInt(1,carrierId);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("t.id");
                int library = rs.getInt("t.library");
                int finalUser = rs.getInt("t.final_user");
                int carrier = rs.getInt("t.carrier");
                Date transportDate = rs.getDate("transport_date");
                int book = rs.getInt("t.book");
                String state = rs.getString("state");
                TransportToUser transport = new TransportToUser(id,library,finalUser,carrier,transportDate,state,book);
                transport.setLibrary(rs.getString("l.library_name"));
                transport.setFinalUser(rs.getString("u.user_name"));
                transport.setCarrier(rs.getString("c.carrier_name"));
                transport.setBook(rs.getString("b.book_name"));

                transportToUsers.add(transport);

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
        return transportToUsers;
    }
    public List<TransportToUser> selectPerCarrierOTW(int carrierId){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TransportToUser> transportToUsers = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_PER_CARRIEROTW);
            ps.setInt(1,carrierId);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("t.id");
                int library = rs.getInt("t.library");
                int finalUser = rs.getInt("t.final_user");
                int carrier = rs.getInt("t.carrier");
                Date transportDate = rs.getDate("transport_date");
                int book = rs.getInt("t.book");
                String state = rs.getString("state");
                TransportToUser transport = new TransportToUser(id,library,finalUser,carrier,transportDate,state,book);
                transport.setLibrary(rs.getString("l.library_name"));
                transport.setFinalUser(rs.getString("u.user_name"));
                transport.setCarrier(rs.getString("c.carrier_name"));
                transport.setBook(rs.getString("b.book_name"));

                transportToUsers.add(transport);

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
        return transportToUsers;
    }

    public List<TransportToUser> searchPending(int carrierId, String text){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TransportToUser> transportToUsers = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SEARCH_PENDING);
            ps.setInt(1,carrierId);
            ps.setString(2,text+'%');
            ps.setString(3,text+'%');
            ps.setString(4,text+'%');
            ps.setString(5,text+'%');
            ps.setString(6,text+'%');
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("t.id");
                int library = rs.getInt("t.library");
                int finalUser = rs.getInt("t.final_user");
                int carrier = rs.getInt("t.carrier");
                Date transportDate = rs.getDate("transport_date");
                int book = rs.getInt("t.book");
                String state = rs.getString("state");
                TransportToUser transport = new TransportToUser(id,library,finalUser,carrier,transportDate,state,book);
                transport.setLibrary(rs.getString("l.library_name"));
                transport.setFinalUser(rs.getString("u.user_name"));
                transport.setCarrier(rs.getString("c.carrier_name"));
                transport.setBook(rs.getString("b.book_name"));

                transportToUsers.add(transport);

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
        return transportToUsers;
    }

    public List<TransportToUser> searchOnTheWay(int carrierId, String text){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TransportToUser> transportToUsers = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SEARCH_OTW);
            ps.setInt(1,carrierId);
            ps.setString(2,text+'%');
            ps.setString(3,text+'%');
            ps.setString(4,text+'%');
            ps.setString(5,text+'%');
            ps.setString(6,text+'%');
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("t.id");
                int library = rs.getInt("t.library");
                int finalUser = rs.getInt("t.final_user");
                int carrier = rs.getInt("t.carrier");
                Date transportDate = rs.getDate("transport_date");
                int book = rs.getInt("t.book");
                String state = rs.getString("state");
                TransportToUser transport = new TransportToUser(id,library,finalUser,carrier,transportDate,state,book);
                transport.setLibrary(rs.getString("l.library_name"));
                transport.setFinalUser(rs.getString("u.user_name"));
                transport.setCarrier(rs.getString("c.carrier_name"));
                transport.setBook(rs.getString("b.book_name"));

                transportToUsers.add(transport);

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
        return transportToUsers;
    }
}
