package jdbc;
import static jdbc.Conexion.*;
import entities.objects_process.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class LoanDAO {
    private static final String SQL_SELECT = "select * from loans";
    private static final String SQL_INSERT = "insert into loans(library,receptionist,final_user,book,loan_date,state,penalty_fee) value(?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "update loans set library=?,receptionist=?,final_user=?,book=?,loan_date=?,state=?,penalty_fee=? where id=?";
    private static final String SQL_DELETE = "delete from loans where id=?";

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
}
