package ru.otus.businessLayer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.controllers.UserController;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SubscriberImpl implements Subscriber {
    private final List<SoftReference<Listener>> listOfListener = new ArrayList<>();
    private UserController userController;

//    public SubscriberImpl() {
//        Listener listener = new Listener() {
//            @Override
//            public void notify(String action) throws InterruptedException {
//                userController.addUser();
//            }
//        };
//        addListener(listener);
//
//    }

    @Autowired
    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    @Override
    public void notify(String action) {
        forEachListeners(action);
    }

    @Override
    public void addListener(Listener listener) {
        listOfListener.add(new SoftReference<>(listener));
    }

    @Override
    public void removeListener(Listener listener) {
        listOfListener
                .forEach(element -> {
                    var elementOfListOfListener = element.get();
                    if (elementOfListOfListener != null) {
                        if (elementOfListOfListener.equals(listener)) {
                            listOfListener.remove(element);
                        } else {
                            throw new NoSuchElementException("The listener to be removed is null");
                        }
                    }
                });
    }

    private void forEachListeners(String action) {
        listOfListener.forEach(listeners -> {
            Listener listener = listeners.get();
            if (listener != null) {
                try {
                    listener.notify(action);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                listOfListener.remove(listeners);
            }
        });
    }
}
