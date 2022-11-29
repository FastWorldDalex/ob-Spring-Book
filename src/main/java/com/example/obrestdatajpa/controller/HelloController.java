package com.example.obrestdatajpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/holamundo")
    public String holaMundo(){
        return "Hola Mundo que tal?";
    }
}
