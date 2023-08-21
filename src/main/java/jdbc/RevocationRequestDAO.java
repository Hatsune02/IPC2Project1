package jdbc;
import static jdbc.Conexion.*;
import entities.objets_process.*;
import java.sql.*;
import java.util.*;

public class RevocationRequestDAO {
    private static final String SQL_SELECT = "select * from revocation_requests";
    private static final String SQL_INSERT = "insert into revocation_requests(final_user,state,detail) value(?,?,?)";
    private static final String SQL_UPDATE = "update revocation_requests set final_user=?,state=?,detail=? where id=?";
    private static final String SQL_DELETE = "delete from revocation_requests where id=?";

    public List<RevocationRequest> select(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<RevocationRequest> revocationRequests = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                int finalUser = rs.getInt("final_user");
                String state = rs.getString("state");
                String detail = rs.getString("detail");
                revocationRequests.add(new RevocationRequest(id,finalUser,state,detail));

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
        return revocationRequests;
    }
    public int insert(RevocationRequest revocationRequest){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setInt(1,revocationRequest.getFinalUserId());
            ps.setString(2,revocationRequest.getState());
            ps.setString(3,revocationRequest.getDetail());
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
    public int update(RevocationRequest revocationRequest){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setInt(1,revocationRequest.getFinalUserId());
            ps.setString(2,revocationRequest.getState());
            ps.setString(3,revocationRequest.getDetail());
            ps.setInt(4,revocationRequest.getId());
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
    public int delete(RevocationRequest revocationRequest){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_DELETE);
            ps.setInt(1,revocationRequest.getId());
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
