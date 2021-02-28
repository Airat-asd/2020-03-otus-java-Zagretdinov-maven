package ru.otus.front;

import ru.otus.businessLayer.dto.Dto;
import ru.otus.businessLayer.service.Subscriber;
import ru.otus.messagesystem.client.MessageCallback;
import ru.otus.messagesystem.message.Message;
import ru.otus.messagesystem.message.MessageType;
import ru.otus.messagesystem.client.MsClient;

public class FrontendServiceImpl implements FrontendService {

    private final MsClient msClient;
    private final String databaseServiceClientName;
//    private final Subscriber subscriber;

    public FrontendServiceImpl(MsClient msClient, String databaseServiceClientName, Subscriber subscriber) {
        this.msClient = msClient;
        this.databaseServiceClientName = databaseServiceClientName;
//        this.subscriber = subscriber;
    }

//    @Override
//    public void getUserData(String login, MessageCallback<Dto> dataConsumer) {
//        Message outMsg = msClient.produceMessage(databaseServiceClientName, new Dto(login),
//                MessageType.USER_DATA, dataConsumer);
//        msClient.sendMessage(outMsg);
//    }

    @Override
    public void getAllUsers(MessageCallback<Dto> dataConsumer) {
        Message outMsg = msClient.produceMessage(databaseServiceClientName, new Dto(),
                MessageType.LIST_USER_DATA, dataConsumer);
        msClient.sendMessage(outMsg);
    }

    @Override
    public void saveUserData(Dto dto, MessageCallback<Dto> dataConsumer) {
        Message outMsg = msClient.produceMessage(databaseServiceClientName, dto,
                MessageType.SAVE_USER_DATA, dataConsumer);
        msClient.sendMessage(outMsg);
//        subscriber.notify("Save user");
    }
}
