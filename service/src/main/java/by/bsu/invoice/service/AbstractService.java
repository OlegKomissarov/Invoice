package by.bsu.invoice.service;


import by.bsu.invoice.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public abstract class AbstractService<T, R extends Repository<T>> implements Service<T> {

    protected R repository;

    @Autowired
    public AbstractService(R repository) {
        this.repository = repository;
    }

    @Override
    public List<T> getAll() {
        return (List<T>) repository.findAll();
    }

    @Override
    public T getById(Integer id) {
        return repository.findById(id).get();
    }
}
