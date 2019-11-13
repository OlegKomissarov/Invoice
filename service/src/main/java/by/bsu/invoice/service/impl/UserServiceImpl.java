package by.bsu.invoice.service.impl;

import by.bsu.invoice.entity.User;
import by.bsu.invoice.repository.UserRepository;
import by.bsu.invoice.service.AbstractService;
import by.bsu.invoice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl extends AbstractService<User, UserRepository> implements UserService {

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder passwordEncoder, UserRepository repository) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
    }

    public long getIdByEmail(String email) {
        return repository.getIdByEmail(email);
    }

    public User getByEmail(String email) {
        return repository.getByEmail(email);
    }

    public List<User> getBySearchQuery(String searchQuery) {
        return repository.getBySearchQuery(searchQuery);
    }

    public void update (String email, String name, String phone) {
        repository.update(new User(email, name, phone));
    }

    public void create (String email, String password, String name, String phone) {
        if (repository.getIdByEmail(email) == -1) {
            User user = new User(email, name, phone);
            user.setPassword(passwordEncoder.encode(password));
            repository.save(user);
        }
    }
}
