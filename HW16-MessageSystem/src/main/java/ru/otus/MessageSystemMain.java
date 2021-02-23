package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessageSystemMain {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(MessageSystemMain.class, args);
//        ApplicationContext ctx = new AnnotationConfigApplicationContext(WebConfig.class);
//
//        Thread.sleep(500);
//        messageSystem.dispose();
//        logger.info("done");

    }

}
