package jdbc;

import static jdbc.Conexion.*;
import entities.module.*;
import java.sql.*;
import java.util.*;


public class CarrierDAO {
    private static final String SQL_SELECT = "select * from carriers";
    private static final String SQL_INSERT = "insert into carriers(carrier_name, username, carrier_password, email) value(?,?,?,?)";
    private static final String SQL_UPDATE = "update carriers set carrier_name=?,username=?,carrier_password=?,email=? where id=?";
    private static final String SQL_DELETE = "delete from carriers where id=?";
    private static final String SQL_VALIDATE = "select count(id) as amount from carriers where username=? and carrier_password=?";
    private static final String SQL_SEARCH = "select * from carriers where id like ? or carrier_name like ? or username like ? or carrier_password like ? or email like ?";


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
    public int validate(Carrier carrier){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_VALIDATE);
            ps.setString(1,carrier.getUsername());
            ps.setString(2,carrier.getPassword());
            rs = ps.executeQuery();

            while (rs.next()){
                records=rs.getInt("amount");
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        finally {
            try {
                close(ps);
                close(rs);
                close(con);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return records;
    }
    public Carrier selectOne(Carrier carrier){
        List<Carrier> carriers = select();
        for(Carrier carrier1 : carriers){
            if(carrier1.getUsername().equals(carrier.getUsername()) || carrier1.getId() == carrier.getId()){
                carrier = carrier1;
                break;
            }
        }
        return carrier;
    }
    public List<Carrier> search(String text){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Carrier> carriers = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SEARCH);
            ps.setString(1,text + '%');
            ps.setString(2,text + '%');
            ps.setString(3,text + '%');
            ps.setString(4,text + '%');
            ps.setString(5,text + '%');
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
}
