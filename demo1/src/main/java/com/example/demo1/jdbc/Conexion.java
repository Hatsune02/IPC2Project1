package com.example.demo1.jdbc;

import java.sql.*;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/librariesDB?serverTimezone=UTC";
//    private static final String URL = "jdbc:mysql://localhost:3306/librariesDB?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
    public static void close(ResultSet rs)throws SQLException{rs.close();}
    public static void close(Statement s)throws SQLException{s.close();}
    public static void close(PreparedStatement ps)throws SQLException{ps.close();}
    public static void close(Connection connection)throws SQLException{connection.close();}
}
