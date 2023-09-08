package com.example.demo1.entities.objects_library;

public class Category {
    private int id;
    private String name,description;

    public Category() {
    }
    public Category(int ID) {
        this.id = ID;
    }
    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public Category(int ID, String name, String description) {
        this.id = ID;
        this.name = name;
        this.description = description;
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name=" + name +
                ", description=" + description +
                '}';
    }
}
