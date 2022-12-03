package com.afidal.peliculasservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PeliculasServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PeliculasServiceApplication.class, args);
    }
}
