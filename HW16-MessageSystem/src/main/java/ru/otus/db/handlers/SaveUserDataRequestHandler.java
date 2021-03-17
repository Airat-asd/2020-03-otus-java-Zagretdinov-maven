package ru.otus.db.handlers;

import ru.otus.businessLayer.dto.Dto;
import ru.otus.businessLayer.model.User;
import ru.otus.businessLayer.service.DBServiceUser;
import ru.otus.businessLayer.service.MappingUserToDto;
import ru.otus.messagesystem.RequestHandler;
import ru.otus.messagesystem.message.Message;
import ru.otus.messagesystem.message.MessageBuilder;
import ru.otus.messagesystem.message.MessageHelper;

import java.util.Optional;

public class SaveUserDataRequestHandler implements RequestHandler<Dto> {
    private final DBServiceUser dbService;

    public SaveUserDataRequestHandler(DBServiceUser dbService) {
        this.dbService = dbService;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        Dto userData = MessageHelper.getPayload(msg);
        User user = MappingUserToDto.mapToUser(userData);
        dbService.saveUser(user);
        return Optional.of(MessageBuilder.buildReplyMessage(msg, userData));
    }
}
