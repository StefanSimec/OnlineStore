package ch.roche.ss.onlinestore.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main SpringBoot bootstrapping class
 */
@SpringBootApplication
public class OnlineStoreApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(OnlineStoreApplication.class, args);
    }

}
