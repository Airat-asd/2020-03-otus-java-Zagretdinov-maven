package ru.otus.businessLayer.service;

public interface Subscriber {
    void notify(String action);

    void addListener(Listener listener);

    void removeListener(Listener listener);
}
