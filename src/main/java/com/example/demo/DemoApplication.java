package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@SpringBootApplication
public class DemoApplication {
    //implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // Client
//    @GetMapping("/{name}")
//    public String hello(@RequestHeader(required = false) String authorization, @PathVariable String name) {
//        System.out.println(authorization);
//        return "Welcome to Github!," + name + ", Hello!";
//    }
}
