package ru.otus.front;

import ru.otus.businessLayer.dto.Dto;
import ru.otus.messagesystem.client.MessageCallback;

public interface FrontendService {

//    void getUserData(String login, MessageCallback<Dto> dataConsumer);

    void getAllUsers(MessageCallback<Dto> dataConsumer);

    void saveUserData(Dto dto, MessageCallback<Dto> dataConsumer);
}

