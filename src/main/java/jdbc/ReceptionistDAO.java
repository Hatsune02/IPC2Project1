package jdbc;
import static jdbc.Conexion.*;
import entities.module.*;
import java.sql.*;
import java.util.*;

public class ReceptionistDAO {
    private static final String SQL_SELECT = "select * from receptionists";
    private static final String SQL_INSERT = "insert into receptionists(receptionist_name, username, receptionist_password, email, library) value(?,?,?,?,?)";
    private static final String SQL_UPDATE = "update receptionists set receptionist_name=?,username=?,receptionist_password=?,email=?,library=? where id=?";
    private static final String SQL_DELETE = "delete from receptionists where id=?";

    public List<Receptionist> select(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Receptionist> receptionists = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("admin_name");
                String username = rs.getString("username");
                String password = rs.getString("admin_password");
                String email = rs.getString("email");
                int libraryID = rs.getInt("library");
                receptionists.add(new Receptionist(id,name,username,password,email,libraryID));

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
        return receptionists;
    }
    public int insert(Receptionist receptionist){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setString(1,receptionist.getName());
            ps.setString(2,receptionist.getUsername());
            ps.setString(3,receptionist.getPassword());
            ps.setString(4,receptionist.getEmail());
            ps.setInt(5,receptionist.getLibraryID());
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
    public int update(Receptionist receptionist){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setString(1,receptionist.getName());
            ps.setString(2,receptionist.getUsername());
            ps.setString(3,receptionist.getPassword());
            ps.setString(4,receptionist.getEmail());
            ps.setInt(5,receptionist.getLibraryID());
            ps.setInt(6,receptionist.getId());
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
    public int delete(Receptionist receptionist){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_DELETE);
            ps.setInt(1,receptionist.getId());
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
