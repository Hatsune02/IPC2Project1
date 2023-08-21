package jdbc;
import static jdbc.Conexion.*;
import entities.objets_process.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class TransportToUserDAO {
    private static final String SQL_SELECT = "select * from transport_users";
    private static final String SQL_INSERT = "insert into transport_users(library,final_user,carrier,transport_date,state,book) value(?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "update transport_users set library=?,final_user=?,carrier=?,transport_date=?,state=?,book=? where id=?";
    private static final String SQL_DELETE = "delete from transport_users where id=?";

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
}
