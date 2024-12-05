package com.example.product_management_system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TryController {

    @GetMapping("/try")
    public String tryController() {
        System.out.println("tryController");
        return "Hello World";
    }

}
