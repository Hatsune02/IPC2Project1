package com.example.demo1.jdbc;


import com.example.demo1.entities.objects_library.Library;
import com.example.demo1.entities.reports.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo1.jdbc.Conexion.close;
import static com.example.demo1.jdbc.Conexion.getConnection;

public class ReportsDAO {

    private static final String SQL_SELECT_PREMIUM = "select u.id,u.user_name, u.premium from final_users as u\n" +
            "where u.premium = true;";

    private static final String SQL_SELECT_RECEPTIONISTS = "select r.id, r.receptionist_name, count(l.id) as finalizados from receptionists as r\n" +
            "left join loans as l on l.receptionist = r.id where l.state = 'FINALIZADO CON INCIDENCIA' or l.state = 'FINALIZADO' group by r.id\n" +
            "order by finalizados desc limit 5; ";

    private static final String SQL_SELECT_CARRIERS = "select c.id, c.carrier_name, \n" +
            "sum(case when t.state = 'FINALIZADO' then 1 else 0 end) as finalizados\n" +
            "from carriers c \n" +
            "left join (select carrier, state from transport_between_libraries \n" +
            "\t\t\tunion all \n" +
            "\t\t\tselect carrier, state from transport_users) t\n" +
            "on c.id = t.carrier\n" +
            "group by c.id\t\n" +
            "order by finalizados desc limit 5;";

    private static final String SQL_SELECT_BOOKS_LOANS = "select b.isbn, b.book_name, count(l.id) as prestamos_en_rango \n" +
            "from books b\n" +
            "left join loans l on b.isbn = l.book\n" +
            "where l.loan_date >= date_sub(curdate(),interval 3 month)\n" +
            "group by b.isbn\n" +
            "order by prestamos_en_rango desc limit 10;";

    private static final String SQL_SELECT_USERS_INCIDENTS = "select u.id, u.user_name, count(i.id) as incidencias from final_users as u\n" +
            "left join loans as l on l.final_user = u.id\n" +
            "left join incidents as i on i.loan = l.id \n" +
            "group by u.id\t\n" +
            "order by incidencias desc limit 5; ";
    private static final String SQL_SELECT_SUSPENDED_USERS = "select u.id, u.user_name, u.ban from final_users u\n" +
            "where ban = true;";
    private static final String SQL_SELECT_LOANS_A = "select p.id, l.library_name, r.receptionist_name,u.user_name ,b.book_name, p.loan_date, p.state from loans p\n" +
            "left join libraries as l on l.id = p.library\n" +
            "left join receptionists as r on r.id = p.receptionist\n" +
            "left join final_users as u on u.id = p.final_user\n" +
            "left join books as b on b.isbn = p.book\t\t\n" +
            "where p.loan_date >= date_sub(curdate(),interval 3 month) and p.state = 'ACTIVO';";
    private static final String SQL_SELECT_LOANS_F = "select p.id, l.library_name, r.receptionist_name,u.user_name ,b.book_name, p.loan_date, p.state from loans p\n" +
            "left join libraries as l on l.id = p.library\n" +
            "left join receptionists as r on r.id = p.receptionist\n" +
            "left join final_users as u on u.id = p.final_user\n" +
            "left join books as b on b.isbn = p.book\t\t\n" +
            "where p.loan_date >= date_sub(curdate(),interval 3 month) and p.state = 'FINALIZADO';";
    private static final String SQL_SELECT_LOANS_FI = "select p.id, l.library_name, r.receptionist_name,u.user_name ,b.book_name, p.loan_date, p.state from loans p\n" +
            "left join libraries as l on l.id = p.library\n" +
            "left join receptionists as r on r.id = p.receptionist\n" +
            "left join final_users as u on u.id = p.final_user\n" +
            "left join books as b on b.isbn = p.book\t\t\n" +
            "where p.loan_date >= date_sub(curdate(),interval 3 month) and p.state = 'FINALIZADO CON INCIDENCIA';";
    private static final String SQL_TRANSACTIONS = "select * from transactions;";
    private static final String SQL_TRANSACTIONS_INSERT = "insert into transactions (money_spent,money_recharge,detail) values (?,?,?);";


    public List<Premium> selectPremium(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Premium> premiums = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_PREMIUM);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("u.id");
                String name = rs.getString("u.user_name");
                premiums.add(new Premium(id,name));
            }

        }catch (SQLException e){
            e.printStackTrace(System.out);
        }
        finally {
            try {
                close(ps);
                close(rs);
                close(con);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return premiums;
    }
    public List<ReceptionistEndLoans> selectReceptionistEndLoans(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ReceptionistEndLoans> receptionists = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_RECEPTIONISTS);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("r.id");
                String name = rs.getString("r.receptionist_name");
                int endLoans = rs.getInt("finalizados");
                receptionists.add(new ReceptionistEndLoans(id,name,endLoans));
            }

        }catch (SQLException e){
            e.printStackTrace(System.out);
        }
        finally {
            try {
                close(ps);
                close(rs);
                close(con);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return receptionists;
    }
    public List<CarriersEndTransports> selectCarriersEndT(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<CarriersEndTransports> premiums = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_CARRIERS);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("c.id");
                String name = rs.getString("c.carrier_name");
                int endT = rs.getInt("finalizados");
                premiums.add(new CarriersEndTransports(id,name,endT));
            }

        }catch (SQLException e){
            e.printStackTrace(System.out);
        }
        finally {
            try {
                close(ps);
                close(rs);
                close(con);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return premiums;
    }
    public List<BooksLoans> selectBooksLoans(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<BooksLoans> premiums = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_BOOKS_LOANS);
            rs = ps.executeQuery();
            while (rs.next()){
                int isbn = rs.getInt("b.isbn");
                String name = rs.getString("b.book_name");
                int loans = rs.getInt("prestamos_en_rango");
                premiums.add(new BooksLoans(isbn,name,loans));
            }

        }catch (SQLException e){
            e.printStackTrace(System.out);
        }
        finally {
            try {
                close(ps);
                close(rs);
                close(con);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return premiums;
    }

    public List<UserIncidents> selectUserIncidents(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<UserIncidents> premiums = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_USERS_INCIDENTS);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("u.id");
                String name = rs.getString("u.user_name");
                int incidents = rs.getInt("incidencias");

                premiums.add(new UserIncidents(id,name,incidents));
            }

        }catch (SQLException e){
            e.printStackTrace(System.out);
        }
        finally {
            try {
                close(ps);
                close(rs);
                close(con);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return premiums;
    }
    public List<SuspendedUsers> selectSuspendedUsers(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<SuspendedUsers> premiums = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT_SUSPENDED_USERS);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("u.id");
                String name = rs.getString("u.user_name");
                premiums.add(new SuspendedUsers(id,name));
            }

        }catch (SQLException e){
            e.printStackTrace(System.out);
        }
        finally {
            try {
                close(ps);
                close(rs);
                close(con);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return premiums;
    }
    public List<Loans30Days> selectLoans30Days(String SQL){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Loans30Days> premiums = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("p.id");
                String library = rs.getString("l.library_name");
                String recep = rs.getString("r.receptionist_name");
                String user = rs.getString("u.user_name");
                String book = rs.getString("b.book_name");
                Date date = rs.getDate("p.loan_date");
                String state = rs.getString("p.state");
                premiums.add(new Loans30Days(id,library,recep,user,book,date,state));
            }

        }catch (SQLException e){
            e.printStackTrace(System.out);
        }
        finally {
            try {
                close(ps);
                close(rs);
                close(con);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return premiums;
    }

    public List<Loans30Days> selectLoansA(){
        return selectLoans30Days(SQL_SELECT_LOANS_A);
    }
    public List<Loans30Days> selectLoansF(){
        return selectLoans30Days(SQL_SELECT_LOANS_F);
    }
    public List<Loans30Days> selectLoansFI(){
        return selectLoans30Days(SQL_SELECT_LOANS_FI);
    }
    public List<Transactions> selectTransactions(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Transactions> premiums = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_TRANSACTIONS);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                int moneyS = rs.getInt("money_spent");
                int moneyR = rs.getInt("money_recharge");
                String detail = rs.getString("detail");
                premiums.add(new Transactions(id,moneyS,moneyR,detail));
            }

        }catch (SQLException e){
            e.printStackTrace(System.out);
        }
        finally {
            try {
                close(ps);
                close(rs);
                close(con);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return premiums;
    }
    public int insertTransaction(Transactions transactions){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_TRANSACTIONS_INSERT);
            ps.setDouble(1,transactions.getMoneySpent());
            ps.setDouble(2,transactions.getMoneyRecharge());
            ps.setString(3,transactions.getDetail());
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
