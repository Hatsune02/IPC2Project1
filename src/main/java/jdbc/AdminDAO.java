package jdbc;
import static jdbc.Conexion.*;
import entities.module.*;
import java.sql.*;
import java.util.*;

public class AdminDAO {
    private static final String SQL_SELECT = "select * from admins";
    private static final String SQL_INSERT = "insert into admins(admin_name, username, admin_password, email) value(?,?,?,?)";
    private static final String SQL_UPDATE = "update admins set admin_name=?,username=?,admin_password=?,email=? where id=?";
    private static final String SQL_DELETE = "delete from admins where id=?";

    public List<Admin> select(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //Admin admin = null;
        List<Admin> admins = new ArrayList<>();

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
                admins.add(new Admin(id,name,username,password,email));
                /* CREAR UNA ADMIN VACIO EN BASE A LA BASE DE DATOS
                admin = new Admin();
                admin.setId(id);
                admin.setName(name);
                admin.setUsername(username);
                admin.setPassword(password);
                admin.setEmail(email);
                admin = new Admin(id,name,username,password,email);
                admins.add(admin);*/
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
        return admins;
    }
    public int insert(Admin admin){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setString(1,admin.getName());
            ps.setString(2,admin.getUsername());
            ps.setString(3,admin.getPassword());
            ps.setString(4,admin.getEmail());
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
    public int update(Admin admin){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setString(1,admin.getName());
            ps.setString(2,admin.getUsername());
            ps.setString(3,admin.getPassword());
            ps.setString(4,admin.getEmail());
            ps.setInt(5,admin.getId());
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
    public int delete(Admin admin){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_DELETE);
            ps.setInt(1,admin.getId());
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
