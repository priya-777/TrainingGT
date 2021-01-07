package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;

@RestController
public class DisplayHello {

//    @RequestMapping(method = RequestMethod.GET)
//    public String homeInit() {
//        return "home";
//    }
    @PostMapping("/")
    public String homeInitpost(@RequestBody String msg) {
        return "home";
    }
}
