package ru.otus.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.businessLayer.model.User;
import ru.otus.businessLayer.service.DBServiceUser;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private final DBServiceUser dbServiceUser;

    public UserController(DBServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    @GetMapping({"/", "/user/list"})
    public String clientsListView(Model model) {
        List<User> users =  dbServiceUser.getAllUsers().orElse(new ArrayList<>());
        model.addAttribute("users", users);
        return "usersList.html";
    }

    @GetMapping("/user/create")
    public String clientCreateView(Model model) {
        model.addAttribute("user", new User());
        return "userCreate.html";
    }

    @PostMapping("/client/save")
    public RedirectView clientSave(@ModelAttribute User user) {
        dbServiceUser.saveUser(user);
        return new RedirectView("/", true);
    }

}
