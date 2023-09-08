package com.example.demo1.jdbc;
import static com.example.demo1.jdbc.Conexion.*;
import com.example.demo1.entities.objects_process.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class LoanDAO {
    private static final String SQL_SELECT = "select * from loans";
    private static final String SQL_INSERT = "insert into loans(library,receptionist,final_user,book,loan_date,state,penalty_fee) value(?,?,?,?,?,?,?)";
    private static final String SQL_INSERT1 = "insert into loans(id,library,receptionist,final_user,book,loan_date,state,penalty_fee) value(?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "update loans set library=?,receptionist=?,final_user=?,book=?,loan_date=?,state=?,penalty_fee=? where id=?";
    private static final String SQL_DELETE = "delete from loans where id=?";
    private static final String SQL_LOANS_USER = "select count(final_user) loansUser from loans where final_user = ? and loans.state = 'ACTIVO'";
    private static final String SQL_LIST = "select loans.id, loans.library, libraries.library_name, loans.receptionist,receptionists.receptionist_name, loans.final_user, final_users.user_name, book, books.book_name, loan_date, state, penalty_fee from loans\n" +
            "join final_users on final_users.id = loans.final_user join books on books.isbn = loans.book\n" +
            "join libraries on libraries.id = loans.library join receptionists on receptionists.id = loans.receptionist order by loans.id;";

    private static final String SQL_LIST_PER_RECEP = "select loans.id, loans.library, libraries.library_name, loans.receptionist,receptionists.receptionist_name, loans.final_user, final_users.user_name, book, books.book_name, loan_date, state, penalty_fee from loans\n" +
            "join final_users on final_users.id = loans.final_user join books on books.isbn = loans.book\n" +
            "join libraries on libraries.id = loans.library join receptionists on receptionists.id = loans.receptionist where loans.receptionist=? and state='ACTIVO' order by loans.id ;";
    private static final String SQL_LIST_PER_USER = "select loans.id, loans.library, libraries.library_name, loans.receptionist,receptionists.receptionist_name, loans.final_user, final_users.user_name, book, books.book_name, loan_date, state, penalty_fee from loans\n" +
            "join final_users on final_users.id = loans.final_user join books on books.isbn = loans.book\n" +
            "join libraries on libraries.id = loans.library join receptionists on receptionists.id = loans.receptionist where loans.final_user=? and state='ACTIVO' order by loans.id ;";
    private static final String SQL_SEARCH = "select loans.id, loans.library, libraries.library_name, loans.receptionist,receptionists.receptionist_name, loans.final_user, final_users.user_name, book, books.book_name, loan_date, state, penalty_fee from loans\n" +
            "join final_users on final_users.id = loans.final_user join books on books.isbn = loans.book\n" +
            "join libraries on libraries.id = loans.library join receptionists on receptionists.id = loans.receptionist where loans.receptionist=? and state='ACTIVO'" +
            "and (loans.id like ? or loans.library like ? or libraries.library_name like ? or loans.receptionist like ? or receptionists.receptionist_name like ? or loans.final_user like ? or final_users.user_name like ? or book like ? or books.book_name like ? or loan_date like ? or state like ? or penalty_fee) order by loans.id ;";
    private static final String SQL_SEARCH_FOR_INCIDENT = "select loans.id, loans.library, libraries.library_name, loans.receptionist,receptionists.receptionist_name, loans.final_user, final_users.user_name, book, books.book_name, loan_date, state, penalty_fee from loans\n" +
            "join final_users on final_users.id = loans.final_user join books on books.isbn = loans.book\n" +
            "join libraries on libraries.id = loans.library join receptionists on receptionists.id = loans.receptionist where loans.receptionist = ? and state='ACTIVO' " +
            "and (loans.id like ? or loans.final_user like ? or final_users.user_name like ? or book like ? or books.book_name like ? or loan_date like ?) order by loans.id ;";


    public List<Loan> select(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Loan> loans = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                int libraryId = rs.getInt("library");
                int receptionistId = rs.getInt("receptionist");
                int finalUserId = rs.getInt("final_user");
                int bookIsbn = rs.getInt("book");
                Date loanDate = rs.getDate("loan_date");
                String state = rs.getString("state");
                double penaltyFee = rs.getDouble("penalty_fee");
                loans.add(new Loan(id,libraryId,receptionistId,finalUserId,bookIsbn,loanDate,state,penaltyFee));

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
        return loans;
    }
    public int insert(Loan loan){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setInt(1,loan.getLibraryId());
            ps.setInt(2,loan.getReceptionistId());
            ps.setInt(3,loan.getFinalUserId());
            ps.setInt(4,loan.getBookIsbn());
            ps.setDate(5,loan.getLoanDate());
            ps.setString(6,loan.getState());
            ps.setDouble(7,loan.getPenaltyFee());
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
    public int insertId(Loan loan){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT1);
            ps.setInt(1,loan.getId());
            ps.setInt(2,loan.getLibraryId());
            ps.setInt(3,loan.getReceptionistId());
            ps.setInt(4,loan.getFinalUserId());
            ps.setInt(5,loan.getBookIsbn());
            ps.setDate(6,loan.getLoanDate());
            ps.setString(7,loan.getState());
            ps.setDouble(8,loan.getPenaltyFee());
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
    public int update(Loan loan){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setInt(1,loan.getLibraryId());
            ps.setInt(2,loan.getReceptionistId());
            ps.setInt(3,loan.getFinalUserId());
            ps.setInt(4,loan.getBookIsbn());
            ps.setDate(5,loan.getLoanDate());
            ps.setString(6,loan.getState());
            ps.setDouble(7,loan.getPenaltyFee());
            ps.setInt(8,loan.getId());
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
    public int delete(Loan loan){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_DELETE);
            ps.setInt(1,loan.getId());
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
    public Loan selectOneById(Loan loan){
        for(Loan loan1 : list()){
            if(loan1.getId()==loan.getId() || loan1.getFinalUserId()==loan.getFinalUserId()){
                loan = loan1;
            }
        }
        return loan;
    }
    public Loan selectRecent(Loan loan){
        int id = 1;
        for(Loan loan1 : list()){
            if(loan1.getId() > id) id = loan1.getId();
        }
        for(Loan loan1 : list()){
            if(loan1.getId() == id) loan = loan1;
        }
        return loan;
    }
    public int countLoansPerUser(int userId){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_LOANS_USER);
            ps.setInt(1,userId);
            rs = ps.executeQuery();
            while(rs.next()){
                records = rs.getInt("loansUser");
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
    public List<Loan> list(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Loan> loans = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_LIST);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("loans.id");
                int libraryId = rs.getInt("loans.library");
                int receptionistId = rs.getInt("loans.receptionist");
                int finalUserId = rs.getInt("loans.final_user");
                int bookIsbn = rs.getInt("book");
                Date loanDate = rs.getDate("loan_date");
                String state = rs.getString("state");
                double penaltyFee = rs.getDouble("penalty_fee");
                Loan loan = new Loan(id,libraryId,receptionistId,finalUserId,bookIsbn,loanDate,state,penaltyFee);

                loan.setLibrary(rs.getString("libraries.library_name"));
                loan.setReceptionist(rs.getString("receptionists.receptionist_name"));
                loan.setFinalUser(rs.getString("final_users.user_name"));
                loan.setBook(rs.getString("books.book_name"));

                loans.add(loan);

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
        return loans;
    }
    public List<Loan> listPerRecep(int recepID){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Loan> loans = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_LIST_PER_RECEP);
            ps. setInt(1, recepID);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("loans.id");
                int libraryId = rs.getInt("loans.library");
                int receptionistId = rs.getInt("loans.receptionist");
                int finalUserId = rs.getInt("loans.final_user");
                int bookIsbn = rs.getInt("book");
                Date loanDate = rs.getDate("loan_date");
                String state = rs.getString("state");
                double penaltyFee = rs.getDouble("penalty_fee");
                Loan loan = new Loan(id,libraryId,receptionistId,finalUserId,bookIsbn,loanDate,state,penaltyFee);

                loan.setLibrary(rs.getString("libraries.library_name"));
                loan.setReceptionist(rs.getString("receptionists.receptionist_name"));
                loan.setFinalUser(rs.getString("final_users.user_name"));
                loan.setBook(rs.getString("books.book_name"));

                loans.add(loan);

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
        return loans;
    }
    public List<Loan> listPerUser(int userId){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Loan> loans = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_LIST_PER_USER);
            ps. setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("loans.id");
                int libraryId = rs.getInt("loans.library");
                int receptionistId = rs.getInt("loans.receptionist");
                int finalUserId = rs.getInt("loans.final_user");
                int bookIsbn = rs.getInt("book");
                Date loanDate = rs.getDate("loan_date");
                String state = rs.getString("state");
                double penaltyFee = rs.getDouble("penalty_fee");
                Loan loan = new Loan(id,libraryId,receptionistId,finalUserId,bookIsbn,loanDate,state,penaltyFee);

                loan.setLibrary(rs.getString("libraries.library_name"));
                loan.setReceptionist(rs.getString("receptionists.receptionist_name"));
                loan.setFinalUser(rs.getString("final_users.user_name"));
                loan.setBook(rs.getString("books.book_name"));

                loans.add(loan);

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
        return loans;
    }
    public List<Loan> searchForIncident(int recepID, String text){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Loan> loans = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SEARCH_FOR_INCIDENT);
            ps.setInt(1, recepID);
            ps.setString(2,text + '%');
            ps.setString(3,text + '%');
            ps.setString(4,text + '%');
            ps.setString(5,text + '%');
            ps.setString(6,text + '%');
            ps.setString(7,text + '%');
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("loans.id");
                int libraryId = rs.getInt("loans.library");
                int receptionistId = rs.getInt("loans.receptionist");
                int finalUserId = rs.getInt("loans.final_user");
                int bookIsbn = rs.getInt("book");
                Date loanDate = rs.getDate("loan_date");
                String state = rs.getString("state");
                double penaltyFee = rs.getDouble("penalty_fee");
                Loan loan = new Loan(id,libraryId,receptionistId,finalUserId,bookIsbn,loanDate,state,penaltyFee);

                loan.setLibrary(rs.getString("libraries.library_name"));
                loan.setReceptionist(rs.getString("receptionists.receptionist_name"));
                loan.setFinalUser(rs.getString("final_users.user_name"));
                loan.setBook(rs.getString("books.book_name"));

                loans.add(loan);

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
        return loans;
    }
}
