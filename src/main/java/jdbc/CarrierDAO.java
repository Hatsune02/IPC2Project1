package jdbc;

import static jdbc.Conexion.*;
import entities.module.*;
import java.sql.*;
import java.util.*;

import static jdbc.Conexion.close;
import static jdbc.Conexion.getConnection;

public class CarrierDAO {
    private static final String SQL_SELECT = "select * from carriers";
    private static final String SQL_INSERT = "insert into carriers(carrier_name, username, carrier_password, email) value(?,?,?,?)";
    private static final String SQL_UPDATE = "update carriers set carrier_name=?,username=?,carrier_password=?,email=? where id=?";
    private static final String SQL_DELETE = "delete from carriers where id=?";

    public List<Carrier> select(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Carrier> carriers = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("carrier_name");
                String username = rs.getString("username");
                String password = rs.getString("carrier_password");
                String email = rs.getString("email");
                carriers.add(new Carrier(id,name,username,password,email));
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
        return carriers;
    }
    public int insert(Carrier carrier){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setString(1,carrier.getName());
            ps.setString(2,carrier.getUsername());
            ps.setString(3,carrier.getPassword());
            ps.setString(4,carrier.getEmail());
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
    public int update(Carrier carrier){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setString(1,carrier.getName());
            ps.setString(2,carrier.getUsername());
            ps.setString(3,carrier.getPassword());
            ps.setString(4,carrier.getEmail());
            ps.setInt(5,carrier.getId());
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
    public int delete(Carrier carrier){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_DELETE);
            ps.setInt(1,carrier.getId());
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