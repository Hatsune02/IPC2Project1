package jdbc;
import static jdbc.Conexion.*;
import entities.module.*;
import java.sql.*;
import java.util.*;

public class FinalUserDAO {
    private static final String SQL_SELECT = "select * from final_users";
    private static final String SQL_INSERT = "insert into final_users(user_name, username, user_password, email, balance) value(?,?,?,?,?)";
    private static final String SQL_UPDATE = "update final_users set user_name=?,username=?,user_password=?,email=?,balance=? where id=?";
    private static final String SQL_DELETE = "delete from final_users where id=?";
    private static final String SQL_VALIDATE = "select count(id) as amount from final_users where username=? and user_password=?";


    public List<FinalUser> select(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<FinalUser> finalUsers = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("user_name");
                String username = rs.getString("username");
                String password = rs.getString("user_password");
                String email = rs.getString("email");
                double balance = rs.getDouble("balance");
                finalUsers.add(new FinalUser(id,name,username,password,email,balance));

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
        return finalUsers;
    }
    public int insert(FinalUser finalUser){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setString(1,finalUser.getName());
            ps.setString(2,finalUser.getUsername());
            ps.setString(3,finalUser.getPassword());
            ps.setString(4,finalUser.getEmail());
            ps.setDouble(5,finalUser.getBalance());
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
    public int update(FinalUser finalUser){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setString(1,finalUser.getName());
            ps.setString(2,finalUser.getUsername());
            ps.setString(3,finalUser.getPassword());
            ps.setString(4,finalUser.getEmail());
            ps.setDouble(5,finalUser.getBalance());
            ps.setInt(6,finalUser.getId());
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
    public int delete(FinalUser finalUser){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_DELETE);
            ps.setInt(1,finalUser.getId());
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
    public int validate(FinalUser finalUser){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_VALIDATE);
            ps.setString(1,finalUser.getUsername());
            ps.setString(2,finalUser.getPassword());
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
    public FinalUser selectOne(FinalUser finalUser){
        List<FinalUser> finalUsers = select();
        for(FinalUser finalUser1 : finalUsers){
            if(finalUser.getUsername().equals(finalUser1.getUsername()) || finalUser.getId() == finalUser1.getId()){
                finalUser = finalUser1;
                break;
            }
        }
        return finalUser;
    }
}
