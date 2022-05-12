package com.example.authentification.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @GetMapping
    public String index() {
        return "index";
    }
    @GetMapping("/authenticated")
    public String au() {
        return "authenticated";
    }
}
