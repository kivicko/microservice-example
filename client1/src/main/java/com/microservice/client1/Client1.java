package com.microservice.client1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by kivi on 18.06.2017.
 */
@EnableDiscoveryClient
@SpringBootApplication
public class Client1 {

    public static void main(String[] args) {
        SpringApplication.run(Client1.class, args);
    }
}
