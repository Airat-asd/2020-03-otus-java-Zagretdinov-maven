package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private int sharedCounter = 0;

    private final Pool pool = new Pool();

    public static void main(String[] args) {
        new Main().go();
    }

    private void go() {

        new Thread(() -> writer(false), "writer-1").start();
        new Thread(() -> writer(true), "writer-2").start();
    }

    private void writer(boolean even) {
        while (!Thread.currentThread().isInterrupted()) {
            lock.writeLock().lock();
            try {
                int i;
                if (this.sharedCounter < 10) {
                    i = 1;
                } else {
                    i = -1;
                }
                this.sharedCounter = this.sharedCounter + i;
                if ((this.sharedCounter % 2) == 0) {
                    if (even) {
                        logger.info("{}", sharedCounter);
                    }
                } else {
                    if (!even) {
                        logger.info("{}", sharedCounter);
                    }

                }
                sleep(1);
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

    private static void sleep(int seconds) {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}

class Pool {
    boolean state = false;
    int count = 0;
}
