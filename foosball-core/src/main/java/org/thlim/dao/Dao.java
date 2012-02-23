package org.thlim.dao;

import java.io.Serializable;
import java.util.List;

public interface Dao<T extends Serializable> {

    void delete(T o);

    T load(long id);

    void save(T o);

    void update(T o);

    List<T> findAll();

    int countAll();
}
