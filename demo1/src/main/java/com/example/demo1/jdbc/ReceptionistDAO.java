package com.example.demo1.jdbc;
import static com.example.demo1.jdbc.Conexion.*;
import com.example.demo1.entities.module.*;

import java.sql.*;
import java.util.*;

public class ReceptionistDAO {
    private static final String SQL_SELECT = "select * from receptionists";
    private static final String SQL_INSERT = "insert into receptionists(receptionist_name, username, receptionist_password, email, library) value(?,?,?,?,?)";
    private static final String SQL_INSERT1 = "insert into receptionists(id,receptionist_name, username, receptionist_password, email, library) value(?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "update receptionists set receptionist_name=?,username=?,receptionist_password=?,email=?,library=? where id=?";
    private static final String SQL_DELETE = "delete from receptionists where id=?";
    private static final String SQL_LESS_LOANS = "select r.id, r.username, count(l.receptionist) as transports from receptionists as r\n" +
            "left join loans as l on l.receptionist = r.id\n" +
            "group by id order by transports limit 1;";
    private static final String SQL_VALIDATE = "select count(id) as amount from receptionists where username=? and receptionist_password=?";
    private static final String SQL_SELECT_JOIN = "select receptionists.id, receptionist_name, username,receptionist_password,email,library,libraries.library_name from receptionists\n" +
            "join libraries on libraries.id = receptionists.library;";
    private static final String SQL_SELECT_LIBRARIES = "select library, libraries.library_name from receptionists join libraries on libraries.id = receptionists.library\n" +
            "group by libraries.library_name;";
    private static final String SQL_SELECT_FILTER = "select receptionists.id, receptionist_name, username,receptionist_password,email,library,libraries.library_name from receptionists\n" +
            "join libraries on libraries.id = receptionists.library where library=?";
    private static final String SQL_SEARCH = "select receptionists.id, receptionist_name, username,receptionist_password,email,library,libraries.library_name from receptionists\n" +
            "join libraries on libraries.id = receptionists.library " +
            "where receptionists.id like ? or receptionist_name like ? or username like ? or receptionist_password like ? or email like ? or library like ? or libraries.library_name like ?";
    private static final String SQL_F_S = "select receptionists.id, receptionist_name, username,receptionist_password,email,library,libraries.library_name from receptionists\n" +
            "join libraries on libraries.id = receptionists.library " +
            "where library=? and (receptionists.id like ? or receptionist_name like ? or username like ? or receptionist_password like ? or email like ?)";

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
                String name = rs.getString("receptionist_name");
                String username = rs.getString("username");
                String password = rs.getString("receptionist_password");
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
    public int insertId(Receptionist receptionist){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT1);
            ps.setInt(1,receptionist.getId());
            ps.setString(2,receptionist.getName());
            ps.setString(3,receptionist.getUsername());
            ps.setString(4,receptionist.getPassword());
            ps.setString(5,receptionist.getEmail());
            ps.setInt(6,receptionist.getLibraryID());
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
    public int validate(Receptionist receptionist){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_VALIDATE);
            ps.setString(1,receptionist.getUsername());
            ps.setString(2,receptionist.getPassword());
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
    public Receptionist selectOne(Receptionist receptionist){
        List<Receptionist> receptionists = list();
        for(Receptionist receptionist1 : receptionists){
            if(receptionist1.getUsername().equals(receptionist.getUsername()) || receptionist1.getId() == receptionist.getId()){
                receptionist = receptionist1;
                break;
            }
        }
        return receptionist;
    }
    public List<Receptionist> list(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Receptionist> receptionists = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_JOIN);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("receptionist_name");
                String username = rs.getString("username");
                String password = rs.getString("receptionist_password");
                String email = rs.getString("email");
                int libraryID = rs.getInt("library");
                String library = rs.getString("libraries.library_name");
                receptionists.add(new Receptionist(id,name,username,password,email,libraryID,library));
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
    public List<Receptionist> libraries(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Receptionist> librariesName = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_LIBRARIES);
            rs = ps.executeQuery();
            while (rs.next()){
                Receptionist recep = new Receptionist();
                recep.setLibraryID(rs.getInt("library"));
                recep.setLibrary(rs.getString("libraries.library_name"));
                librariesName.add(recep);
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
        return librariesName;
    }

    public List<Receptionist> filterList(int id){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Receptionist> receptionists = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_FILTER);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()){
                int id1 = rs.getInt("id");
                String name = rs.getString("receptionist_name");
                String username = rs.getString("username");
                String password = rs.getString("receptionist_password");
                String email = rs.getString("email");
                int libraryID = rs.getInt("library");
                String library = rs.getString("libraries.library_name");
                receptionists.add(new Receptionist(id1,name,username,password,email,libraryID,library));
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
    public List<Receptionist> search(String text){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Receptionist> receptionists = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SEARCH);
            ps.setString(1,text + '%');
            ps.setString(2,text + '%');
            ps.setString(3,text + '%');
            ps.setString(4,text + '%');
            ps.setString(5,text + '%');
            ps.setString(6,text + '%');
            ps.setString(7,text + '%');
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("receptionist_name");
                String username = rs.getString("username");
                String password = rs.getString("receptionist_password");
                String email = rs.getString("email");
                int libraryID = rs.getInt("library");
                String library = rs.getString("libraries.library_name");
                receptionists.add(new Receptionist(id,name,username,password,email,libraryID,library));
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
    public List<Receptionist> searchAndFilter(int id, String text){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Receptionist> receptionists = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_F_S);
            ps.setInt(1, id);
            ps.setString(2,text + '%');
            ps.setString(3,text + '%');
            ps.setString(4,text + '%');
            ps.setString(5,text + '%');
            ps.setString(6,text + '%');
            rs = ps.executeQuery();
            while (rs.next()){
                int id1 = rs.getInt("id");
                String name = rs.getString("receptionist_name");
                String username = rs.getString("username");
                String password = rs.getString("receptionist_password");
                String email = rs.getString("email");
                int libraryID = rs.getInt("library");
                String library = rs.getString("libraries.library_name");
                receptionists.add(new Receptionist(id1,name,username,password,email,libraryID,library));
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

    public int receptionistLessLoans(){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_LESS_LOANS);
            rs = ps.executeQuery();
            while(rs.next()){
                records = rs.getInt("r.id");
            }

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
