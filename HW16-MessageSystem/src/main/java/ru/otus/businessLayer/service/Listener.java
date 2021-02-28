package ru.otus.businessLayer.service;

public interface Listener {
    void notify(String action) throws InterruptedException;
}
