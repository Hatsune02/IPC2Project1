package com.example.demo1.entities.objects_library;

public class Library {
    private int id;
    private String name,address;

    public Library() {
    }
    public Library(int ID) {
        this.id = ID;
    }
    public Library(String name, String address) {
        this.name = name;
        this.address = address;
    }
    public Library(int ID, String name, String address) {
        this.id = ID;
        this.name = name;
        this.address = address;
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", name=" + name +
                ", address=" + address +
                '}';
    }
}
