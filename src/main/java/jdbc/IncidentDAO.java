package jdbc;
import static jdbc.Conexion.*;
import entities.objets_process.*;
import java.sql.*;
import java.util.*;

public class IncidentDAO {
    private static final String SQL_SELECT = "select * from incidents";
    private static final String SQL_INSERT = "insert into incidents(loan, incident_type, amount_paid) value(?,?,?)";
    private static final String SQL_UPDATE = "update incidents set loan=?,incident_type=?,amount_paid=? where id=?";
    private static final String SQL_DELETE = "delete from incidents where id=?";

    public List<Incident> select(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Incident> incidents = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                int loanId = rs.getInt("loan");
                String incident_type = rs.getString("incident_type");
                double amountPaid = rs.getDouble("amount_paid");
                incidents.add(new Incident(id,loanId,incident_type,amountPaid));

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
        return incidents;
    }
    public int insert(Incident incident){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setInt(1,incident.getLoanId());
            ps.setString(2,incident.getIncidentType());
            ps.setDouble(3,incident.getAmountPaid());
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
    public int update(Incident incident){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setInt(1,incident.getLoanId());
            ps.setString(2,incident.getIncidentType());
            ps.setDouble(3,incident.getAmountPaid());
            ps.setInt(4,incident.getId());
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
    public int delete(Incident incident){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_DELETE);
            ps.setInt(1,incident.getId());
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
