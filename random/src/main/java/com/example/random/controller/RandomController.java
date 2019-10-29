package com.example.random.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
public class RandomController {
    @GetMapping(value = "/display")
    public ResponseEntity<Integer> message() {
        Integer random = getRandomNumber();
        return new ResponseEntity<>(random, HttpStatus.OK);
    }

    private Integer getRandomNumber() {
        return ThreadLocalRandom.current().nextInt(1, 101);
    }
}
