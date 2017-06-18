package com.microservice.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * Created by kivi on 18.06.2017.
 */
@EnableEurekaServer
@SpringBootApplication
public class Server {

    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }
}
