package by.bsu.invoice.controller;

import by.bsu.invoice.entity.User;
import by.bsu.invoice.service.UserService;
import by.bsu.invoice.util.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;
    private SessionUser sessionUser;

    @Autowired
    public UserController(UserService userService, SessionUser sessionUser) {
        this.userService = userService;
        this.sessionUser = sessionUser;
    }

    @GetMapping("/search/{searchQuery}")
    public ResponseEntity<List<User>> getBySearchQuery(@PathVariable("searchQuery") String searchQuery) {
        return new ResponseEntity<>(userService.getBySearchQuery(searchQuery), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<User> create(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("phone") String phone
    ) {
        // Recomment when implement login
//        if (sessionUser == null) {
            userService.create(email, password, name, phone);
            return new ResponseEntity<>(HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login() {
        // TODO: implement login
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
