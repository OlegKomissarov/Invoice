package by.bsu.invoice.service;

import by.bsu.invoice.entity.User;

import java.util.List;

public interface UserService extends Service<User> {
    Integer getIdByEmail(String email);

    User getByEmail(String email);

    List<User> getBySearchQuery(String searchQuery);

    void update(String email, String name, String phone);

    void create(String email, String password, String name, String phone);
}
