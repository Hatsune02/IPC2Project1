package jdbc;
import static jdbc.Conexion.*;
import entities.objects_library.*;
import java.sql.*;
import java.util.*;

public class BookDAO {
    private static final String SQL_SELECT = "select * from books";
    private static final String SQL_INSERT = "insert into books(book_name, price, category, author) value(?,?,?,?)";
    private static final String SQL_UPDATE = "update books set book_name=?,price=?,category=?,author=? where isbn=?";
    private static final String SQL_DELETE = "delete from books where isbn=?";

    public List<Book> select(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Book> books = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()){
                int isbn = rs.getInt("isbn");
                String name = rs.getString("book_name");
                double price = rs.getDouble("price");
                int categoryID = rs.getInt("category");
                String author = rs.getString("author");
                books.add(new Book(isbn,name,price,categoryID,author));

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
        return books;
    }
    public int insert(Book book){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setString(1,book.getName());
            ps.setDouble(2,book.getPrice());
            ps.setInt(3,book.getCategoryID());
            ps.setString(4,book.getAuthor());
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
    public int update(Book book){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setString(1,book.getName());
            ps.setDouble(2,book.getPrice());
            ps.setInt(3,book.getCategoryID());
            ps.setString(4,book.getAuthor());
            ps.setInt(5,book.getIsbn());
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
    public int delete(Book book){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_DELETE);
            ps.setInt(1,book.getIsbn());
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
