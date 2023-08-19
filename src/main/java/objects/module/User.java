package objects.module;

public class User {
    private final int ID;
    private String name, username,password,email;
    public User(int ID, String name, String username, String password, String email){
        this.ID = ID;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public int getID() {return ID;}
    public String getName() {return name;}
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getEmail() {return email;}
    public void setName(String name) {this.name = name;}
    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}
    public void setEmail(String email) {this.email = email;}
}
