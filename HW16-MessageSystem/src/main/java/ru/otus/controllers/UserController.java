package ru.otus.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
    private final FrontendService frontendService;
    private final SimpMessagingTemplate template;

    @Autowired
    public UserController(FrontendService frontendService, SimpMessagingTemplate template) {
        this.frontendService = frontendService;
        this.template = template;
    }

    @GetMapping({"/"})
    public String usersListView(Model model) throws InterruptedException {
        List<Dto> dtoUsers = new ArrayList<>();
        frontendService.getAllUsers(data -> dtoUsers.addAll(data.getListOfDtoUsers()));
        Thread.sleep(100);
        model.addAttribute("dtoUsers", dtoUsers);
        return "usersList";
    }

    @MessageMapping("/addUser")
    @SendTo("/topic/response")
    public Dto addUserSecondOption(Dto dtoUser) {
        frontendService.saveUserData(dtoUser, data -> logger.info("Save user: {}", data));
        logger.info("Send new user");
        return dtoUser;
    }

    public void addUser(Dto dtoUser) {
        logger.info("Send new user");
        template.convertAndSend("/topic/response", dtoUser);
    }

    @GetMapping("/user/create")
    public String clientCreateView(Model model) {
        model.addAttribute("dtoUser", new Dto());
        return "userCreate";
    }

    @GetMapping("/user/createSecondOption")
    public String clientCreateSecondOptionView(Model model) {
        model.addAttribute("dtoUser", new Dto());
        return "userCreateSecondOption";
    }

    @PostMapping("/user/save")
    public RedirectView clientSave(@ModelAttribute Dto dtoUser) {
        frontendService.saveUserData(dtoUser, data -> addUser(data));
        return new RedirectView("/user/create", true);
    }
}
