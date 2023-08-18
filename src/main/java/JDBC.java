import java.sql.*;

public class JDBC {
    String url = "jdbc:mysql://localhost:3306/librariesDB?serverTimezone=UTC";
    String username = "root";
    String password = "root";
    Connection connection;
    Statement statement;
    //ResultSet es para mostrar
    ResultSet resultSetAdmin, resultSetCarrier;
    //PreparedStatement

    public JDBC(){
        try {
            connection = DriverManager.getConnection(url,username,password);
            statement = connection.createStatement();
            resultSetAdmin = statement.executeQuery("select * from admins");
            while (resultSetAdmin.next()) {
                //Nombre de la columna
                /*System.out.println(resultSet.getString("id") + " | " + resultSet.getString("admin_name")
                        + " | " + resultSet.getString("username") + " | " + resultSet.getString("admin_password")
                        + " | " + resultSet.getString("email"));
                */
                //numero de columna
                System.out.println(resultSetAdmin.getString(1) + " | " + resultSetAdmin.getString(2)
                        + " | " + resultSetAdmin.getString(3)+ " | " + resultSetAdmin.getString(4)
                        + " | " + resultSetAdmin.getString(5));

            }
            resultSetCarrier = statement.executeQuery("select * from carriers");
            while (resultSetCarrier.next()) {
                //numero de columna
                System.out.println(resultSetCarrier.getString(1) + " | " + resultSetCarrier.getString(2)
                        + " | " + resultSetCarrier.getString(3)+ " | " + resultSetCarrier.getString(4)
                        + " | " + resultSetCarrier.getString(5));

            }
            connection.close();
            statement.close();
            resultSetAdmin.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
