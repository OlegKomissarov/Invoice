package by.bsu.invoice.service;

import java.util.List;

public interface Service<T> {

    List<T> read();

    void remove(long id);

    T getById(long id);

//    List<T> paginate(int pageId, int total);

}
