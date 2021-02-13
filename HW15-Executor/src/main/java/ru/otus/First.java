package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class First {
    private static final Logger logger = LoggerFactory.getLogger(First.class);

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private int sharedCounter = 0;

    private boolean direction = true;

    private boolean last = false;

    public static void main(String[] args) {
        new First().go();
    }

    private void go() {
        List<Integer> countThread1 = new ArrayList<>();
        List<Integer> countThread2 = new ArrayList<>();

        new Thread(() -> writer(false, countThread1), "writer-1").start();
        new Thread(() -> writer(true, countThread2), "writer-2").start();
    }

    private void writer(boolean even, List<Integer> countThread) {
        while (!Thread.currentThread().isInterrupted()) {
            lock.writeLock().lock();
            try {
                while (even == last) {
                    int i;
                    if (direction) {
                        i = 1;
                    } else {
                        i = -1;
                    }
                    this.sharedCounter = this.sharedCounter + i;
                    countThread.add(this.sharedCounter);
                    if (this.sharedCounter == 10) {
                        direction = false;
                    } else if (this.sharedCounter == 1) {
                        direction = true;
                    }
                    logger.info("{}", countThread);
                    last = !even;
                    sleep(1);
                }
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