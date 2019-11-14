package by.bsu.invoice.repository;

import by.bsu.invoice.entity.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@org.springframework.stereotype.Repository
public interface UserRepository extends Repository<User> {

    @Query(value = "SELECT * FROM invoice.user WHERE email=?", nativeQuery = true)
    User getByEmail(String email);

    @Query(value = "SELECT id FROM invoice.user WHERE email=?", nativeQuery = true)
    Integer getIdByEmail(String email);

    @Query(value = "SELECT id, email, name FROM user WHERE user.name LIKE CONCAT('%', ?, '%') " +
            "OR email LIKE CONCAT('%', ?, '%') ORDER BY name;", nativeQuery = true)
    List<User> getBySearchQuery(String searchQuery);
}
