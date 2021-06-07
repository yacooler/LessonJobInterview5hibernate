package ru.vyazankin.repository;

import org.hibernate.Session;

import java.util.List;

public interface BaseRepo <T, I>{
    void setSession(Session session);

    T findByID(I id);
    T update(T entity);
    T save(T entity);
    void deleteById(I id);

    List<T> findAll();
}
