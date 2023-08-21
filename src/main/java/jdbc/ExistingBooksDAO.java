package jdbc;
import static jdbc.Conexion.*;
import entities.objects_library.*;
import java.sql.*;
import java.util.*;

public class ExistingBooksDAO {
    private static final String SQL_SELECT = "select * from existing_books";
    private static final String SQL_INSERT = "insert into existing_books(library, book, existence) value(?,?,?)";
    private static final String SQL_UPDATE = "update existing_books set existence=? where library=? and book=?";
    private static final String SQL_DELETE = "delete from existing_books where library=? and book=?";

    public List<ExistingBooks> select(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ExistingBooks> existingBooks = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()){
                int libraryID = rs.getInt("library");
                int bookIsbn = rs.getInt("book");
                int existence = rs.getInt("existence");
                existingBooks.add(new ExistingBooks(libraryID,bookIsbn,existence));

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
        return existingBooks;
    }
    public int insert(ExistingBooks existingBooks){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setInt(1,existingBooks.getLibraryID());
            ps.setInt(2,existingBooks.getBookIsbn());
            ps.setInt(3,existingBooks.getExistence());
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
    public int update(ExistingBooks existingBooks){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setInt(1,existingBooks.getExistence());
            ps.setInt(2,existingBooks.getLibraryID());
            ps.setInt(3,existingBooks.getBookIsbn());
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
    public int delete(ExistingBooks existingBooks){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_DELETE);
            ps.setInt(1,existingBooks.getLibraryID());
            ps.setInt(2,existingBooks.getBookIsbn());
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
