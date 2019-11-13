package by.bsu.invoice.service;


import by.bsu.invoice.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@org.springframework.stereotype.Service
public abstract class AbstractService<T, R extends Repository<T>> implements Service<T> {

    protected R repository;

    @Autowired
    public AbstractService(R repository) {
        this.repository = repository;
    }

    @Override
    public List<T> read() {
        return (List<T>) repository.getAll();
    }

    @Override
    public void remove(long id) {
        repository.remove(id);
    }

    @Override
    public T getById(long id) {
        return repository.getById(id);
    }

    // TODO: add pagination
//    @Override
//    public List<T> paginate(int pageId, int total)  {
//        return repository.getAll(PageRequest.of(pageId - 1, total, Sort.by("id"))).getContent();
//    }
}
