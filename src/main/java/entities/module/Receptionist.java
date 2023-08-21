package entities.module;
import jdbc.JDBC;
import entities.OBJECTS;
import java.sql.*;

public class Receptionist extends User{
    private int libraryID;

    public Receptionist() {}

    public Receptionist(int id) {
        super(id);
    }

    public Receptionist(String name, String username, String password, String email, int libraryID) {
        super(name, username, password, email);
        this.libraryID = libraryID;
    }

    public Receptionist(int id, String name, String username, String password, String email, int libraryID) {
        super(id, name, username, password, email);
        this.libraryID = libraryID;
    }

    public void createFinalUser(String name, String username, String password, String email){
        JDBC jdbc = new JDBC();
        Connection connection;
        try {
            connection = jdbc.getConnection();
            //INSERTAR NUEVO USUARIO A LA TABLA FINAL USERS
            jdbc.preparedStatement = connection.prepareStatement("insert into final_users(user_name, username, user_password, email) value(?,?,?,?)");
            jdbc.preparedStatement.setString(1,name);
            jdbc.preparedStatement.setString(2,username);
            jdbc.preparedStatement.setString(3,password);
            jdbc.preparedStatement.setString(4,email);
            jdbc.preparedStatement.executeUpdate();
            //LLAMAR DATOS PARA CREAR USUARIO
            jdbc.preparedStatement = connection.prepareStatement("select * from final_users where username=?");
            jdbc.preparedStatement.setString(1,username);
            jdbc.resultSet = jdbc.preparedStatement.executeQuery();
            if(jdbc.resultSet.next()){
                OBJECTS.finalUsers.add(new FinalUser(jdbc.resultSet.getInt(1),name,username,password,email,0));
            }
            connection.close();
            jdbc.resultSet.close();
            jdbc.preparedStatement.close();
        } catch (SQLException e){e.printStackTrace();}
    }

    public int getLibraryID() {return libraryID;}
    public void setLibraryID(int libraryID) {this.libraryID = libraryID;}

    public String toString() {
        return "Receptionist{"+
                "id=" + getId() +
                ", name=" + getName() + '\'' +
                ", username=" + getUsername() +
                ", password=" + getPassword() +
                ", email=" + getEmail() +
                ", libraryID=" + libraryID +
                '}';
    }
}
