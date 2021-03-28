package com.store.agdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@Profile("prod")
@SpringBootApplication
public class AgDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgDemoApplication.class, args);
    }

}
