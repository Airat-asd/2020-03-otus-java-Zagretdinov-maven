package ru.otus.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.businessLayer.dto.Dto;
import ru.otus.front.FrontendService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    FrontendService frontendService;

    @Autowired
    public UserController(FrontendService frontendService) {
        this.frontendService = frontendService;
    }

    @GetMapping({"/"})
    public String clientsListView(Model model) throws InterruptedException {
        List<Dto> dtoUsers = new ArrayList<>();
        frontendService.getAllUsers(data -> dtoUsers.addAll(data.getListOfDtoUsers()));
        Thread.sleep(100);
        model.addAttribute("dtoUsers", dtoUsers);
        return "usersList";
    }

    @GetMapping("/user/create")
    public String clientCreateView(Model model) {
        model.addAttribute("dtoUser", new Dto());
        return "userCreate";
    }

    @PostMapping("/user/save")
    public RedirectView clientSave(@ModelAttribute Dto dtoUser) {
        frontendService.saveUserData(dtoUser, data -> logger.info("Save user:{}", data.getName()));
        return new RedirectView("/user/create", true);

    }
}
