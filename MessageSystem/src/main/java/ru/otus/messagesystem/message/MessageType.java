package ru.otus.messagesystem.message;

public enum MessageType {
    USER_DATA("UserData"),
    LIST_USER_DATA("ListUserData"),
    SAVE_USER_DATA("SaveUserData");

    private final String name;

    MessageType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
