package com.example.telemetryapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class TelemetryAPIApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelemetryAPIApplication.class, args);
    }
}