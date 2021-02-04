package ru.otus.controllers;

import org.springframework.web.bind.annotation.*;
import ru.otus.businessLayer.model.User;
import ru.otus.businessLayer.service.DBServiceUser;

import java.util.Optional;

@RestController
public class UserRestController {

    private final DBServiceUser dbServiceUser;

    public UserRestController(DBServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    @GetMapping("/api/client")
    public User getClientByName(@RequestParam(name = "name") String name) {
        Optional<User> user = dbServiceUser.getUser(name);
        return user.orElse(new User());
    }

    @PostMapping("/api/client")
    public User saveClient(@RequestBody User user) {
        dbServiceUser.saveUser(user);
        return user;
    }
}
