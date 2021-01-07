package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
@RestController
public class DisplayHello {

    @GetMapping("/")
    public String homeInit() {
        return "Hello";
    }

}
