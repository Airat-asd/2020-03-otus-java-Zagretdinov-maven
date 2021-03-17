package ru.otus.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;
import ru.otus.businessLayer.dto.Dto;
import ru.otus.front.FrontendService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserRestController {
    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);
    private final FrontendService frontendService;
    private final SimpMessagingTemplate template;


    @Autowired
    public UserRestController(FrontendService frontendService, SimpMessagingTemplate template) {
        this.frontendService = frontendService;
        this.template = template;
    }

    @PutMapping({"/api/users"})
    public void getUsers() {
        List<Dto> dtoUsers = new ArrayList<>();
        frontendService.getAllUsers(data -> {
            dtoUsers.addAll(data.getListOfDtoUsers());
            dtoUsers.forEach(dtoUser -> {
                template.convertAndSend("/topic/response", dtoUser);
            });
        });
    }
}
