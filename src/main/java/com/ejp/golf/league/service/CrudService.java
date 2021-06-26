package com.ejp.golf.league.service;

import java.util.List;

public interface CrudService<E> {
    List<E> findAll();
    List<E> findAll(String filter);
    void delete(E value);
    void save(E entry);
    void update(E entry);
}