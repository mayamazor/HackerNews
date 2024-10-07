package com.mayamazor.hackernewsAPI.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    // This method will handle GET requests to the "/" endpoint
    @GetMapping("/")
    public String helloWorld() {
        return "Hello World!";
    }
}