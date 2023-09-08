package com.example.demo1.entities.module;

public class Carrier extends User{

    public Carrier() {}

    public Carrier(int id) {
        super(id);
    }

    public Carrier(String name, String username, String password, String email) {
        super(name, username, password, email);
    }

    public Carrier(int id, String name, String username, String password, String email) {
        super(id, name, username, password, email);
    }

    @Override
    public String toString() {
        return "Carrier{"+
                "id=" + getId() +
                ", name=" + getName() + '\'' +
                ", username=" + getUsername() +
                ", password=" + getPassword() +
                ", email=" + getEmail() +
                '}';
    }
}
