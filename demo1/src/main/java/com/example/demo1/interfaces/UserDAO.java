package com.example.demo1.interfaces;

import com.example.demo1.entities.module.Admin;

import java.util.List;

public interface UserDAO<T> {

    public List<T> select();
    public int insert(T t);
    public int update(T t);
    public int validateUser(T t);
}
