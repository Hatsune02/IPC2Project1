package entities.module;
import entities.*;;
import java.sql.*;

public class Admin extends User{
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
