package by.bsu.invoice.service.impl;

import by.bsu.invoice.entity.User;
import by.bsu.invoice.entity.UserPrincipal;
import by.bsu.invoice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        User user = userService.getByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User with email '" + email + "' not found");
        }
        return new UserPrincipal(user);
    }
}
