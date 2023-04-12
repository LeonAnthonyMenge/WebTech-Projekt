package com.example.WebTech.Projekt.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class HelloWorldController {
    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello World";
    }
}
