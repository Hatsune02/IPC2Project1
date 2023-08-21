package jdbc;
import static jdbc.Conexion.*;
import entities.objets_process.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class LoanApplicationDAO {
    private static final String SQL_SELECT = "select * from loan_applications";
    private static final String SQL_INSERT = "insert into loan_applications(library,receptionist,final_user,book,loan_application_date,state,delivery_type,carrier) value(?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "update loan_applications set library=?,receptionist=?,final_user=?,book=?,loan_application_date=?,state=?,delivery_type=?,carrier=? where id=?";
    private static final String SQL_DELETE = "delete from loan_applications where id=?";

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
}
