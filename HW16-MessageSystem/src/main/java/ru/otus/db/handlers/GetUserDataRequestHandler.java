package ru.otus.db.handlers;

import ru.otus.businessLayer.dto.Dto;
import ru.otus.businessLayer.model.User;
import ru.otus.businessLayer.service.DBServiceUser;
import ru.otus.businessLayer.service.MappingUserToDto;
import ru.otus.messagesystem.message.Message;
import ru.otus.messagesystem.RequestHandler;
import ru.otus.messagesystem.message.MessageBuilder;
import ru.otus.messagesystem.message.MessageHelper;

import java.util.Optional;


public class GetUserDataRequestHandler implements RequestHandler<Dto> {
    private final DBServiceUser dbService;

    public GetUserDataRequestHandler(DBServiceUser dbService) {
        this.dbService = dbService;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        Dto userData = MessageHelper.getPayload(msg);
        User user = dbService.getUser(userData.getLogin()).orElse(new User());
        Dto data = MappingUserToDto.mapToDtoUser(user);
        return Optional.of(MessageBuilder.buildReplyMessage(msg, data));
    }
}
