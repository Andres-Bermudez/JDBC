package com.platzi.repository;

import java.util.List;

public interface Repository<T> {

    List<T> findAll();
    T getById(Long id);
    void update(Long id, T t);
    void save(T t);
    void deleteById(Long id);
}
