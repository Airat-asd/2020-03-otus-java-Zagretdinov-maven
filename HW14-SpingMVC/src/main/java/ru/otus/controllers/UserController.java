package ru.otus.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.businessLayer.dto.DtoUser;
import ru.otus.businessLayer.model.User;
import ru.otus.businessLayer.service.DBServiceUser;
import ru.otus.businessLayer.service.MappingUserToDto;

import java.util.ArrayList;
import java.util.Collections;
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
        List<DtoUser> dtoUsers = new ArrayList<>(users.size());
        users.forEach(user -> dtoUsers.add(MappingUserToDto.mapToDtoUser(user)));
        model.addAttribute("dtoUsers", dtoUsers);
        return "usersList.html";
    }

    @GetMapping("/user/create")
    public String clientCreateView(Model model) {
        model.addAttribute("dtoUser", new DtoUser());
        return "userCreate.html";
    }

    @PostMapping("/user/save")
    public RedirectView clientSave(@ModelAttribute DtoUser dtoUser) {
        dbServiceUser.saveUser(MappingUserToDto.mapToUser(dtoUser));
        return new RedirectView("/", true);
    }
}
