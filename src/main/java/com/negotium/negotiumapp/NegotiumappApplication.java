package com.negotium.negotiumapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableConfigurationProperties(AppProperties.class)
public class NegotiumappApplication {

    public static void main(String[] args) {
        SpringApplication.run(NegotiumappApplication.class, args);
    }

}