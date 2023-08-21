package entities.module;
import jdbc.*;
import entities.*;;
import java.sql.*;

public class Admin extends User{
    JDBC jdbc = new JDBC();
    public Admin() {}

    public Admin(int ID) {
        super(ID);
    }

    public Admin(String name, String username, String password, String email) {
        super(name, username, password, email);
    }

    public Admin(int ID, String name, String username, String password, String email) {
        super(ID, name, username, password, email);
    }

    public void createUser(TableName table, String name, String username, String password, String email){
        createUser(table,name,username,password,email,0);
    }
    public void createUser(TableName table, String name, String username, String password, String email,int libraryID){
        JDBC jdbc = new JDBC();
        Connection connection;
        try {
            connection = jdbc.getConnection();
            //INSERTAR NUEVO USUARIO A LA TABLA FINAL USERS
            //jdbc.preparedStatement = connection.prepareStatement("insert into receptionists(receptionist_name, username, receptionist_password, email,library) value(?,?,?,?,?)");
            jdbc.preparedStatement = connection.prepareStatement(createStringInsertUser(table));
            jdbc.preparedStatement.setString(1,name);
            jdbc.preparedStatement.setString(2,username);
            jdbc.preparedStatement.setString(3,password);
            jdbc.preparedStatement.setString(4,email);
            if(table == TableName.RECEPTIONISTS){
                jdbc.preparedStatement.setInt(5,libraryID);
            }

            jdbc.preparedStatement.executeUpdate();
            //LLAMAR DATOS PARA CREAR USUARIO
            //jdbc.preparedStatement = connection.prepareStatement("select * from final_users where username=?");
            jdbc.preparedStatement = connection.prepareStatement("select * from " + table.getName()+" where username=?");
            jdbc.preparedStatement.setString(1,username);
            jdbc.resultSet = jdbc.preparedStatement.executeQuery();
            if(jdbc.resultSet.next()){
                switch (table) {
                    case ADMINS ->
                            OBJECTS.admins.add(new Admin(jdbc.resultSet.getInt(1), name, username, password, email));
                    case RECEPTIONISTS ->
                            OBJECTS.receptionists.add(new Receptionist(jdbc.resultSet.getInt(1), name, username, password, email, libraryID));
                    case CARRIERS ->
                            OBJECTS.carriers.add(new Carrier(jdbc.resultSet.getInt(1), name, username, password, email));
                }
            }
            connection.close();
            jdbc.resultSet.close();
            jdbc.preparedStatement.close();
        } catch (SQLException e){e.printStackTrace();}
    }

    public void updateUser(TableName table,int ID,String name, String username, String password, String email){
        updateUser(table,ID,name,username,password,email,0);
    }
    public void updateUser(TableName table,int ID,String name, String username, String password, String email,int libraryID){
        Connection connection;

        try {
            connection = jdbc.getConnection();
            jdbc.preparedStatement = connection.prepareStatement(createStringUpdateUser(table));
            jdbc.preparedStatement.setString(1,name);
            jdbc.preparedStatement.setString(2,username);
            jdbc.preparedStatement.setString(3,password);
            jdbc.preparedStatement.setString(4,email);
            if(table==TableName.RECEPTIONISTS){
                jdbc.preparedStatement.setInt(5, libraryID);
                jdbc.preparedStatement.setInt(6, ID);
            }
            else {
                jdbc.preparedStatement.setInt(5, ID);
            }
            jdbc.preparedStatement.executeUpdate();

            switch (table) {
                case ADMINS ->
                        OBJECTS.admins.remove(new Admin(jdbc.resultSet.getInt(1), name, username, password, email));
                case RECEPTIONISTS ->
                        OBJECTS.receptionists.add(new Receptionist(jdbc.resultSet.getInt(1), name, username, password, email, libraryID));
                case CARRIERS ->
                        OBJECTS.carriers.add(new Carrier(jdbc.resultSet.getInt(1), name, username, password, email));
            }

            connection.close();
            jdbc.preparedStatement.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public String createStringUpdateUser(TableName table){
        return switch (table) {
            case ADMINS -> "update admins set admin_name=?,username=?,admin_password=?,email=? where id=?";
            case RECEPTIONISTS ->
                    "update receptionists set receptionist_name=?,username=?,receptionist_password=?,email=?,library=? where id=?";
            case CARRIERS -> "update carriers set carrier_name=?,username=?,carrier_password=?,email=? where id=?";
            default -> "";
        };
    }
    public String createStringInsertUser(TableName table){
        return switch (table) {
            case ADMINS -> "insert into admins(admin_name, username, admin_password, email) value(?,?,?,?)";
            case RECEPTIONISTS ->
                    "insert into receptionists(receptionist_name, username, receptionist_password, email,library) value(?,?,?,?,?)";
            case CARRIERS -> "insert into carriers(carrier_name, username, carrier_password, email) value(?,?,?,?)";
            default -> "";
        };
    }

    @Override
    public String toString() {
        return "Admin{"+
                "id=" + getId() +
                ", name=" + getName() + '\'' +
                ", username=" + getUsername() +
                ", password=" + getPassword() +
                ", email=" + getEmail() +
                '}';
    }
}
