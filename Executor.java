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

    public static void main(String[] args) {
        new Main().go();
    }

    private void go() {
        new Thread(this::writer, "writer-1").start();
        new Thread(this::writer, "writer-2").start();
    }

    private void writer() {
        while (!Thread.currentThread().isInterrupted()) {
            lock.writeLock().lock();
            try {

                if ((this.sharedCounter % 2) != 0) {
                    logger.info("{}", sharedCounter);
                }
                this.sharedCounter++;

                logger.info("write:{}", sharedCounter);
            } finally {
                lock.writeLock().unlock();
            }
            sleep(2);
        }
    }

    private void reader() {
        while (!Thread.currentThread().isInterrupted()) {
            // logger.info("before lock");
            lock.readLock().lock();
            try {
                logger.info("read:{}", this.sharedCounter);
            } finally {
                lock.readLock().unlock();
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
