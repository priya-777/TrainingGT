package com.priya.training.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
@RestController
public class TestController {

    @GetMapping("/gettest")
    public String getTest() {
        return "Get Mapping done";
    }
    @PostMapping("/posttest")
    public String postTest(){
        return "Post Mapping done";
    }
}
