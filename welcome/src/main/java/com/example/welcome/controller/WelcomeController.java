package com.example.welcome.controller;

import com.example.welcome.feign.MessageClient;
import com.example.welcome.feign.RandomClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @Autowired
    private MessageClient messageClient;

    @Autowired
    private RandomClient randomClient;

    @GetMapping(value = "/hello")
    public ResponseEntity<String> welcome() {
        String message = messageClient.getMessage();
        Integer randomNumber = randomClient.getRandomNumber();
        String finalMessage = "Hello world from " + message + "! Random number: " + randomNumber;
        return new ResponseEntity<>(finalMessage, HttpStatus.OK);
    }
}
