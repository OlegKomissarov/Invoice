package by.bsu.invoice.service;

import java.util.List;

public interface Service<T> {

    List<T> getAll();

    T getById(Integer id);
}
