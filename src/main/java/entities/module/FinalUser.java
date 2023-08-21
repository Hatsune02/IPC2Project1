package entities.module;

public class FinalUser extends User{
    private double balance;
    private boolean ban = false;
    private boolean premium = false;

    public FinalUser() {}

    public FinalUser(int ID) {
        super(ID);
    }

    public FinalUser(String name, String username, String password, String email) {
        super(name, username, password, email);
    }

    public FinalUser(String name, String username, String password, String email,double balance) {
        super(name, username, password, email);
        this.balance=balance;
    }

    public FinalUser(int ID, String name, String username, String password, String email) {
        super(ID, name, username, password, email);
    }

    public FinalUser(int ID, String name, String username, String password, String email, double balance) {
        super(ID, name, username, password, email);
        this.balance = balance;
    }

    public double getBalance() {return balance;}
    public void setBalance(double balance) {this.balance = balance;}
    public boolean isBan() {return ban;}
    public void setBan(boolean ban) {this.ban = ban;}
    public boolean isPremium() {return premium;}
    public void setPremium(boolean premium) {this.premium = premium;}

    @Override
    public String toString() {
        return "FinalUser{"+
                "id=" + getId() +
                ", name=" + getName() + '\'' +
                ", username=" + getUsername() +
                ", password=" + getPassword() +
                ", email=" + getEmail() +
                ", balance=" + balance +
                ", ban=" + ban +
                ", premium=" + premium +
                '}';
    }
}
