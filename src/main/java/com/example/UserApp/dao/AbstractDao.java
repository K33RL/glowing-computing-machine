package com.example.UserApp.dao;

import java.util.Collection;

public interface AbstractDao<T> {
    void create(T t);

    T getByID(int id);

    Collection<T> getAll();

    boolean update(T t);

    boolean delete(int id);
}
