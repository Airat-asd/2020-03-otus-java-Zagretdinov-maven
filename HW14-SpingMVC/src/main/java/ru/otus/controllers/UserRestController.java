package ru.otus.controllers;

import org.springframework.web.bind.annotation.*;
import ru.otus.businessLayer.model.User;
import ru.otus.businessLayer.service.DBServiceUser;

@RestController
public class UserRestController {

    private final DBServiceUser dbServiceUser;

    public UserRestController(DBServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    @GetMapping("/api/user/{login}")
    public User getUserByLogin(@PathVariable(name = "login") String login) {
        User user = dbServiceUser.getUser(login).orElse(new User());
        return user;
    }

}
