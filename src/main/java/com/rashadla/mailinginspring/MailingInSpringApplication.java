package com.rashadla.mailinginspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MailingInSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailingInSpringApplication.class, args);
    }

}
