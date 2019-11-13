package by.bsu.invoice.util;

import by.bsu.invoice.entity.UserPrincipal;
import by.bsu.invoice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SessionUser {

    private UserService userService;

    @Autowired
    public SessionUser(UserService userService) {
        this.userService = userService;
    }

    public long getId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return userPrincipal.getId();
    }
}
