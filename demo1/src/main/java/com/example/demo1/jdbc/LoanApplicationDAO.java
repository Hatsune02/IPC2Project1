package com.example.demo1.jdbc;
import static com.example.demo1.jdbc.Conexion.*;

import com.example.demo1.entities.objects_library.ExistingBooks;
import com.example.demo1.entities.objects_process.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class LoanApplicationDAO {
    private static final String SQL_SELECT = "select * from loan_applications";
    private static final String SQL_INSERT = "insert into loan_applications(library,receptionist,final_user,book,loan_application_date,state,delivery_type,carrier) value(?,?,?,?,?,?,?,?)";
    private static final String SQL_INSERT1 = "insert into loan_applications(id,library,receptionist,final_user,book,loan_application_date,state,delivery_type,carrier) value(?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "update loan_applications set library=?,receptionist=?,final_user=?,book=?,loan_application_date=?,state=?,delivery_type=?,carrier=? where id=?";
    private static final String SQL_DELETE = "delete from loan_applications where id=?";
    private static final String SQL_LIST = "select loan_applications.id, loan_applications.library, libraries.library_name, loan_applications.receptionist, receptionists.receptionist_name,loan_applications.final_user,final_users.user_name,loan_applications.book,books.book_name, loan_application_date,delivery_type,state,loan_applications.carrier,carriers.carrier_name from loan_applications\n" +
            "join libraries on libraries.id = loan_applications.library join receptionists on receptionists.id = loan_applications.receptionist join final_users on final_users.id = loan_applications.final_user join books on books.isbn = loan_applications.book join carriers on carriers.id = loan_applications.carrier " +
            "where state = 'PENDIENTE';";
    private static final String SQL_LIST_PER_RECEP = "select loan_applications.id, loan_applications.library, libraries.library_name, loan_applications.receptionist, receptionists.receptionist_name,loan_applications.final_user,final_users.user_name,loan_applications.book,books.book_name, loan_application_date,delivery_type,state,loan_applications.carrier,carriers.carrier_name from loan_applications\n" +
            "join libraries on libraries.id = loan_applications.library join receptionists on receptionists.id = loan_applications.receptionist join final_users on final_users.id = loan_applications.final_user join books on books.isbn = loan_applications.book join carriers on carriers.id = loan_applications.carrier " +
            "where loan_applications.receptionist = ? and state='PENDIENTE' and delivery_type != 'DOMICILIO';";
    private static final String SQL_SEARCH = "select loan_applications.id, loan_applications.library, libraries.library_name, loan_applications.receptionist, receptionists.receptionist_name,loan_applications.final_user,final_users.user_name,loan_applications.book,books.book_name, loan_application_date,delivery_type,state,loan_applications.carrier,carriers.carrier_name from loan_applications\n" +
            "join libraries on libraries.id = loan_applications.library join receptionists on receptionists.id = loan_applications.receptionist join final_users on final_users.id = loan_applications.final_user join books on books.isbn = loan_applications.book join carriers on carriers.id = loan_applications.carrier " +
            "where loan_applications.receptionist = ? and state='PENDIENTE' and delivery_type != 'DOMICILIO' and (loan_applications.id like ? or loan_applications.final_user like ?) ;";
    public List<LoanApplication> select(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<LoanApplication> loanApplications = new ArrayList<>();

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
                Date loanApplicationDate = rs.getDate("loan_application_date");
                String state = rs.getString("state");
                String deliveryType = rs.getString("delivery_type");
                int carrier = rs.getInt("carrier");
                loanApplications.add(new LoanApplication(id,libraryId,receptionistId,finalUserId,bookIsbn,loanApplicationDate,state,deliveryType,carrier));

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
        return loanApplications;
    }
    public int insert(LoanApplication loanApplication){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setInt(1,loanApplication.getLibraryId());
            ps.setInt(2,loanApplication.getReceptionistId());
            ps.setInt(3,loanApplication.getFinalUserId());
            ps.setInt(4,loanApplication.getBookIsbn());
            ps.setDate(5,loanApplication.getLoanApplicationDate());
            ps.setString(6,loanApplication.getState());
            ps.setString(7,loanApplication.getDeliveryType());
            ps.setInt(8,loanApplication.getCarrierId());
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
    public int insertId(LoanApplication loanApplication){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT1);
            ps.setInt(1,loanApplication.getId());
            ps.setInt(2,loanApplication.getLibraryId());
            ps.setInt(3,loanApplication.getReceptionistId());
            ps.setInt(4,loanApplication.getFinalUserId());
            ps.setInt(5,loanApplication.getBookIsbn());
            ps.setDate(6,loanApplication.getLoanApplicationDate());
            ps.setString(7,loanApplication.getState());
            ps.setString(8,loanApplication.getDeliveryType());
            ps.setInt(9,loanApplication.getCarrierId());
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
    public int update(LoanApplication loanApplication){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setInt(1,loanApplication.getLibraryId());
            ps.setInt(2,loanApplication.getReceptionistId());
            ps.setInt(3,loanApplication.getFinalUserId());
            ps.setInt(4,loanApplication.getBookIsbn());
            ps.setDate(5,loanApplication.getLoanApplicationDate());
            ps.setString(6,loanApplication.getState());
            ps.setString(7,loanApplication.getDeliveryType());
            ps.setInt(8,loanApplication.getCarrierId());
            ps.setInt(9,loanApplication.getId());
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
    public int delete(LoanApplication loanApplication){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_DELETE);
            ps.setInt(1,loanApplication.getId());
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
    public List<LoanApplication> list(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<LoanApplication> loanApplications = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_LIST);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                int libraryId = rs.getInt("library");
                int receptionistId = rs.getInt("receptionist");
                int finalUserId = rs.getInt("final_user");
                int bookIsbn = rs.getInt("book");
                Date loanApplicationDate = rs.getDate("loan_application_date");
                String state = rs.getString("state");
                String deliveryType = rs.getString("delivery_type");
                int carrier = rs.getInt("carrier");
                LoanApplication loan = new LoanApplication(id,libraryId,receptionistId,finalUserId,bookIsbn,loanApplicationDate,state,deliveryType,carrier);
                loan.setLibrary(rs.getString("libraries.library_name"));
                loan.setFinalUser(rs.getString("final_users.user_name"));
                loan.setBook(rs.getString("books.book_name"));
                loan.setCarrier(rs.getString("carriers.carrier_name"));
                loan.setReceptionist(rs.getString("receptionists.receptionist_name"));
                loanApplications.add(loan);
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
        return loanApplications;
    }

    public List<LoanApplication> listPerRecep(int recepId){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<LoanApplication> loanApplications = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_LIST_PER_RECEP);
            ps.setInt(1,recepId);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                int libraryId = rs.getInt("library");
                int receptionistId = rs.getInt("receptionist");
                int finalUserId = rs.getInt("final_user");
                int bookIsbn = rs.getInt("book");
                Date loanApplicationDate = rs.getDate("loan_application_date");
                String state = rs.getString("state");
                String deliveryType = rs.getString("delivery_type");
                int carrier = rs.getInt("carrier");
                LoanApplication loan = new LoanApplication(id,libraryId,receptionistId,finalUserId,bookIsbn,loanApplicationDate,state,deliveryType,carrier);
                loan.setLibrary(rs.getString("libraries.library_name"));
                loan.setFinalUser(rs.getString("final_users.user_name"));
                loan.setBook(rs.getString("books.book_name"));
                loan.setCarrier(rs.getString("carriers.carrier_name"));
                loan.setReceptionist(rs.getString("receptionists.receptionist_name"));
                loanApplications.add(loan);
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
        return loanApplications;
    }
    public LoanApplication selectOne(LoanApplication loan){
        for (LoanApplication loan1: list()){
            if(loan1.getId()==loan.getId()){
                loan=loan1;
            }
        }
        return loan;
    }
    public List<LoanApplication> search(int recepId, String text){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<LoanApplication> loanApplications = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SEARCH);
            ps.setString(1,text + '%');
            ps.setString(2,text + '%');
            ps.setString(3,text + '%');
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                int libraryId = rs.getInt("library");
                int receptionistId = rs.getInt("receptionist");
                int finalUserId = rs.getInt("final_user");
                int bookIsbn = rs.getInt("book");
                Date loanApplicationDate = rs.getDate("loan_application_date");
                String state = rs.getString("state");
                String deliveryType = rs.getString("delivery_type");
                int carrier = rs.getInt("carrier");
                LoanApplication loan = new LoanApplication(id,libraryId,receptionistId,finalUserId,bookIsbn,loanApplicationDate,state,deliveryType,carrier);
                loan.setLibrary(rs.getString("libraries.library_name"));
                loan.setFinalUser(rs.getString("final_users.user_name"));
                loan.setBook(rs.getString("books.book_name"));
                loan.setCarrier(rs.getString("carriers.carrier_name"));
                loan.setReceptionist(rs.getString("receptionists.receptionist_name"));
                loanApplications.add(loan);
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
        return loanApplications;
    }
}
