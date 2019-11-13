package by.bsu.invoice.repository;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Getter
@Setter
@org.springframework.stereotype.Repository
public abstract class AbstractRepository<T> implements Repository<T> {

    private Class<T> clazz;
    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public void save(T model) {
        entityManager.persist(model);
    }

    @Override
    public void update(T model) {
        entityManager.merge(model);
    }

    @Override
    public void remove(long id) {
        entityManager.remove(getById(id));
    }

    @Override
    public T getById(long id) {
        return entityManager.find(clazz, id);
    }
}
