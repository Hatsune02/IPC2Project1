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
    private static final String SQL_SELECT_JOIN = "select library, libraries.library_name, book, books.book_name, existence " +
            "from existing_books join libraries on libraries.id = existing_books.library " +
            "join books on books.isbn = existing_books.book";

    private static final String SQL_SELECT_LIBRARIES = "select library, libraries.library_name from existing_books \n" +
            "join libraries on libraries.id = existing_books.library\n" +
            "group by libraries.library_name;";
    private static final String SQL_SELECT_FILTER = "select library, libraries.library_name, book, books.book_name, existence " +
            "from existing_books join libraries on libraries.id = existing_books.library\n" +
            "join books on books.isbn = existing_books.book\n" +
            "where library=?;";
    private static final String SQL_SEARCH = "select library, libraries.library_name, book, books.book_name, existence " +
            "from existing_books join libraries on libraries.id = existing_books.library\n" +
            "join books on books.isbn = existing_books.book\n" +
            "where library like ? or libraries.library_name like ? or book like ? or books.book_name like ? or existence like ?;";
    private static final String SQL_F_S = "select library, libraries.library_name, book, books.book_name, existence " +
            "from existing_books join libraries on libraries.id = existing_books.library\n" +
            "join books on books.isbn = existing_books.book\n" +
            "where library=? and book like ? or books.book_name like ? or existence like ?;";

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
    public List<ExistingBooks> list(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ExistingBooks> existingBooks = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_JOIN);
            rs = ps.executeQuery();
            while (rs.next()){
                int libraryID = rs.getInt("library");
                String library = rs.getString("libraries.library_name");
                int bookIsbn = rs.getInt("book");
                String book = rs.getString("books.book_name");
                int existence = rs.getInt("existence");
                existingBooks.add(new ExistingBooks(libraryID,library,bookIsbn,book,existence));
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

    public List<ExistingBooks> libraries(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ExistingBooks> librariesName = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_LIBRARIES);
            rs = ps.executeQuery();
            while (rs.next()){
                ExistingBooks ex = new ExistingBooks();
                ex.setLibraryID(rs.getInt("library"));
                ex.setLibrary(rs.getString("libraries.library_name"));
                librariesName.add(ex);
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
    public List<ExistingBooks> filterList(int id){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ExistingBooks> existingBooks = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_FILTER);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()){
                int libraryID = rs.getInt("library");
                String library = rs.getString("libraries.library_name");
                int bookIsbn = rs.getInt("book");
                String book = rs.getString("books.book_name");
                int existence = rs.getInt("existence");
                existingBooks.add(new ExistingBooks(libraryID,library,bookIsbn,book,existence));
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
    public List<ExistingBooks> search(String text){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ExistingBooks> existingBooks = new ArrayList<>();

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
                int libraryID = rs.getInt("library");
                String library = rs.getString("libraries.library_name");
                int bookIsbn = rs.getInt("book");
                String book = rs.getString("books.book_name");
                int existence = rs.getInt("existence");
                existingBooks.add(new ExistingBooks(libraryID,library,bookIsbn,book,existence));
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
    public List<ExistingBooks> searchAndFilter(int id, String text){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ExistingBooks> existingBooks = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_F_S);
            ps.setInt(1, id);
            ps.setString(2,text + '%');
            ps.setString(3,text + '%');
            ps.setString(4,text + '%');
            rs = ps.executeQuery();
            while (rs.next()){
                int libraryID = rs.getInt("library");
                String library = rs.getString("libraries.library_name");
                int bookIsbn = rs.getInt("book");
                String book = rs.getString("books.book_name");
                int existence = rs.getInt("existence");
                existingBooks.add(new ExistingBooks(libraryID,library,bookIsbn,book,existence));
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
}
