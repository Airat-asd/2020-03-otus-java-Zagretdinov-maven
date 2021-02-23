package ru.otus.db.handlers;

import ru.otus.businessLayer.dto.Dto;
import ru.otus.businessLayer.model.User;
import ru.otus.businessLayer.service.DBServiceUser;
import ru.otus.businessLayer.service.MappingUserToDto;
import ru.otus.messagesystem.RequestHandler;
import ru.otus.messagesystem.message.Message;
import ru.otus.messagesystem.message.MessageBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GetListOfUserDataRequestHandler implements RequestHandler<Dto> {
    private final DBServiceUser dbService;

    public GetListOfUserDataRequestHandler(DBServiceUser dbService) {
        this.dbService = dbService;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        List<Dto> listOfDtoUser = new ArrayList<>();
        List<User> users = dbService.getAllUsers().orElse(new ArrayList<>());
        users.forEach(user -> listOfDtoUser.add(MappingUserToDto.mapToDtoUser(user)));
        Dto data = new Dto(listOfDtoUser);
        return Optional.of(MessageBuilder.buildReplyMessage(msg, data));
    }
}
