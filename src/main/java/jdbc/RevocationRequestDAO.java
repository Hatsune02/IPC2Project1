package jdbc;
import static jdbc.Conexion.*;

import entities.objects_process.*;
import java.sql.*;
import java.util.*;

public class RevocationRequestDAO {
    private static final String SQL_SELECT = "select * from revocation_requests";
    private static final String SQL_INSERT = "insert into revocation_requests(final_user,state,detail) value(?,?,?)";
    private static final String SQL_UPDATE = "update revocation_requests set final_user=?,state=?,detail=? where id=?";
    private static final String SQL_UPDATE_STATE = "update revocation_requests set state=? where id=?";
    private static final String SQL_DELETE = "delete from revocation_requests where id=?";
    private static final String SQL_LIST = "select revocation_requests.id,final_user, final_users.user_name , state, detail from revocation_requests\n" +
            "join final_users on final_users.id = revocation_requests.final_user";
    private static final String SQL_LIST_P = "select revocation_requests.id,final_user, final_users.user_name , state, detail from revocation_requests\n" +
            "join final_users on final_users.id = revocation_requests.final_user where state='pendiente'";
    private static final String SQL_LIST_A = "select revocation_requests.id,final_user, final_users.user_name , state, detail from revocation_requests\n" +
            "join final_users on final_users.id = revocation_requests.final_user where state='aceptado'";
    private static final String SQL_LIST_D = "select revocation_requests.id,final_user, final_users.user_name , state, detail from revocation_requests\n" +
            "join final_users on final_users.id = revocation_requests.final_user where state='rechazado'";
    private static final String SQL_SEARCH_P = "select revocation_requests.id,final_user, final_users.user_name , state, detail from revocation_requests\n" +
            "join final_users on final_users.id = revocation_requests.final_user where state='pendiente' and (revocation_requests.id like ? or final_user like ? or final_users.user_name like ? or state like ? or detail like ?)";
    private static final String SQL_SEARCH_A = "select revocation_requests.id,final_user, final_users.user_name , state, detail from revocation_requests\n" +
            "join final_users on final_users.id = revocation_requests.final_user where state='aceptado' and (revocation_requests.id like ? or final_user like ? or final_users.user_name like ? or state like ? or detail like ?);";
    private static final String SQL_SEARCH_D = "select revocation_requests.id,final_user, final_users.user_name , state, detail from revocation_requests\n" +
            "join final_users on final_users.id = revocation_requests.final_user where state='rechazado' and (revocation_requests.id like ? or final_user like ? or final_users.user_name like ? or state like ? or detail like ?)";

    public List<RevocationRequest> select() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<RevocationRequest> revocationRequests = new ArrayList<>();

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int finalUser = rs.getInt("final_user");
                String state = rs.getString("state");
                String detail = rs.getString("detail");
                revocationRequests.add(new RevocationRequest(id, finalUser, state, detail));

            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(ps);
                close(con);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }

        }
        return revocationRequests;
    }

    public int insert(RevocationRequest revocationRequest) {
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setInt(1, revocationRequest.getFinalUserId());
            ps.setString(2, revocationRequest.getState());
            ps.setString(3, revocationRequest.getDetail());
            records = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                close(ps);
                close(con);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return records;
    }

    public int update(RevocationRequest revocationRequest) {
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setInt(1, revocationRequest.getFinalUserId());
            ps.setString(2, revocationRequest.getState());
            ps.setString(3, revocationRequest.getDetail());
            ps.setInt(4, revocationRequest.getId());
            records = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                close(ps);
                close(con);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return records;
    }
    public void updateState(RevocationRequest revocationRequest) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE_STATE);
            ps.setString(1, revocationRequest.getState());
            ps.setInt(2, revocationRequest.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                close(ps);
                close(con);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
    }

    public int delete(RevocationRequest revocationRequest) {
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_DELETE);
            ps.setInt(1, revocationRequest.getId());
            records = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                close(ps);
                close(con);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return records;
    }

    public RevocationRequest selectOne(RevocationRequest revocationRequest1) {
        List<RevocationRequest> revocationRequests = listPending();
        for (RevocationRequest revocationRequest : revocationRequests) {
            if (revocationRequest.getId() == revocationRequest1.getId()) {
                revocationRequest1 = revocationRequest;
                break;
            }
        }
        revocationRequests = listAccept();
        for (RevocationRequest revocationRequest : revocationRequests) {
            if (revocationRequest.getId() == revocationRequest1.getId()) {
                revocationRequest1 = revocationRequest;
                break;
            }
        }
        revocationRequests = listDecline();
        for (RevocationRequest revocationRequest : revocationRequests) {
            if (revocationRequest.getId() == revocationRequest1.getId()) {
                revocationRequest1 = revocationRequest;
                break;
            }
        }
        return revocationRequest1;
    }

    public List<RevocationRequest> list(String SQL) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<RevocationRequest> revocationRequests = new ArrayList<>();

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int finalUserId = rs.getInt("final_user");
                String finalUser = rs.getString("final_users.user_name");
                String state = rs.getString("state");
                String detail = rs.getString("detail");
                revocationRequests.add(new RevocationRequest(id, finalUserId, finalUser, state, detail));
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(ps);
                close(con);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }

        }
        return revocationRequests;
    }

    public List<RevocationRequest> listPending() {
        return list(SQL_LIST_P);
    }

    public List<RevocationRequest> listAccept() {
        return list(SQL_LIST_A);
    }

    public List<RevocationRequest> listDecline() {
        return list(SQL_LIST_D);
    }

    public List<RevocationRequest> search(String text, String SQL) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<RevocationRequest> revocationRequests = new ArrayList<>();

        try {
            con = getConnection();
            ps = con.prepareStatement(SQL);
            ps.setString(1, text + '%');
            ps.setString(2, text + '%');
            ps.setString(3, text + '%');
            ps.setString(4, text + '%');
            ps.setString(5, text + '%');
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int finalUserId = rs.getInt("final_user");
                String finalUser = rs.getString("final_users.user_name");
                String state = rs.getString("state");
                String detail = rs.getString("detail");
                revocationRequests.add(new RevocationRequest(id, finalUserId, finalUser, state, detail));
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(ps);
                close(con);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }

        }
        return revocationRequests;
    }
    public List<RevocationRequest> searchP(String text){
        return search(text,SQL_SEARCH_P);
    }
    public List<RevocationRequest> searchA(String text){
        return search(text,SQL_SEARCH_A);
    }
    public List<RevocationRequest> searchD(String text){
        return search(text,SQL_SEARCH_D);
    }
}
