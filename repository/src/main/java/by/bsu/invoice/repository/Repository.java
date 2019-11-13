package by.bsu.invoice.repository;

import java.util.List;

public interface Repository<T> {

    List<T> getAll();

    void save(T model);

    void update(T model);

    void remove(long id);

    T getById(long id);
}
