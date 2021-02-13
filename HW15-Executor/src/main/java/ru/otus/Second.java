package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Second {

    private static final Logger logger = LoggerFactory.getLogger(Second.class);

    private int sharedCounter = 0;

    private boolean direction = true;

    private boolean last = false;

    public static void main(String[] args) {
        new Second().go();
    }

    private void go() {
        List<Integer> countThread1 = new ArrayList<>();
        List<Integer> countThread2 = new ArrayList<>();

        new Thread(() -> writer(true, countThread1), "Thread 1").start();
        new Thread(() -> writer(false, countThread2), "Thread 2").start();
    }

    private synchronized void writer(boolean even, List<Integer> countThread) {
        while (true) {
            try {
                while (even == last) {
                    this.wait();
                }
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
                last = even;
                sleep(1);
                notifyAll();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                throw new NotInterestingException(ex);
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

    private static class NotInterestingException extends RuntimeException {
        NotInterestingException(InterruptedException ex) {
            super(ex);
        }
    }
}