package jdbc;
import static jdbc.Conexion.*;
import entities.objects_library.*;
import java.sql.*;
import java.util.*;

public class LibraryDAO {
    private static final String SQL_SELECT = "select * from libraries";
    private static final String SQL_INSERT = "insert into libraries(library_name, address) value(?,?)";
    private static final String SQL_UPDATE = "update libraries set library_name=?,address=? where id=?";
    private static final String SQL_DELETE = "delete from libraries where id=?";

    public List<Library> select(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Library> libraries = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("library_name");
                String address = rs.getString("address");
                libraries.add(new Library(id,name,address));

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
        return libraries;
    }
    public int insert(Library library){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setString(1,library.getName());
            ps.setString(2,library.getAddress());
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
    public int update(Library library){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setString(1,library.getName());
            ps.setString(2,library.getAddress());
            ps.setInt(3,library.getId());
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
    public int delete(Library library){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_DELETE);
            ps.setInt(1,library.getId());
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
