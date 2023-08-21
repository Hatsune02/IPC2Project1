package jdbc;

import java.sql.*;

public class JDBC {
    String url = "jdbc:mysql://localhost:3306/librariesDB?serverTimezone=UTC";
    String username = "root";
    String password = "root";
    Connection connection;
    public Statement statement;
    //ResultSet es para mostrar
    public ResultSet resultSet;
    //PreparedStatement
    public PreparedStatement preparedStatement;

    public JDBC(){
        
    }
    
    public void show(){
        connection = null;
        try {
            //connection = DriverManager.getConnection(url,username,password);
            connection = getConnection();
            statement = connection.createStatement();
            //add();
            //update();
            //delete();
            resultSet = statement.executeQuery("select * from admins");
            while (resultSet.next()) {
                //Nombre de la columna
                /*System.out.println(resultSet.getString("id") + " | " + resultSet.getString("admin_name")
                        + " | " + resultSet.getString("username") + " | " + resultSet.getString("admin_password")
                        + " | " + resultSet.getString("email"));
                */
                //numero de columna
                System.out.println(resultSet.getString(1) + " | " + resultSet.getString(2)
                        + " | " + resultSet.getString(3)+ " | " + resultSet.getString(4)
                        + " | " + resultSet.getString(5));

            }
            resultSet = statement.executeQuery("select * from carriers");
            while (resultSet.next()) {
                //numero de columna
                System.out.println(resultSet.getString(1) + " | " + resultSet.getString(2)
                        + " | " + resultSet.getString(3)+ " | " + resultSet.getString(4)
                        + " | " + resultSet.getString(5));

            }
            //search();
            connection.close();
            statement.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(){
        connection = null;
        try {
            connection = getConnection();
            //connection = DriverManager.getConnection(url,username,password);
            //statement = connection.createStatement();
            //add admins con la tabla
            preparedStatement = connection.prepareStatement("insert into admins(admin_name, username, admin_password, email) value(?,?,?,?)");
            preparedStatement.setString(1,"Administrador3");
            preparedStatement.setString(2,"admin3");
            preparedStatement.setString(3,"1234");
            preparedStatement.setString(4,"example3@examples");
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void update(){
        connection = null;
        try {
            connection = getConnection();
            //connection = DriverManager.getConnection(url,username,password);
            //statement = connection.createStatement();
            //modificar tabla admins
            preparedStatement = connection.prepareStatement("update admins set admin_name=?,username=?,admin_password=? where id=?");
            preparedStatement.setString(1,"Administrador3");
            preparedStatement.setString(2,"admin3");
            preparedStatement.setString(3,"1234");
            preparedStatement.setString(4, "1"); // se puede escribir como string o int el id
            //preparedStatement.setInt(4, 1); // El id puede ser escrito como entero int
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(){
        connection = null;
        try {
            connection = getConnection();
            //connection = DriverManager.getConnection(url,username,password);
            //statement = connection.createStatement();
            //elminar tabla admins
            preparedStatement = connection.prepareStatement("delete from admins where id=?");
            preparedStatement.setInt(1, 1); // es el id
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void search(){
        connection = null;
        try {
            connection = getConnection();
            //connection = DriverManager.getConnection(url,username,password);
            //statement = connection.createStatement();
            //buscar por medio de la id (se puede buscar por medio de todos los atributos)
            preparedStatement = connection.prepareStatement("select * from admins where id = ?");
            preparedStatement.setString(1,"1");

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                System.out.println(resultSet.getString(1) + " | " + resultSet.getString(2)
                        + " | " + resultSet.getString(3)+ " | " + resultSet.getString(4)
                        + " | " + resultSet.getString(5));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        Connection con = null;
        try{
            /*Class.forName("com.mysql.jdbc.Driver"); SE USA CUANDO NO TENEMOS IMPORTADA LA CLASE
            con = (Connection) DriverManager.getConnection(url,username,password);*/
            con = DriverManager.getConnection(url,username,password);
        } catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }
}
