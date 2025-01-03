package com.three.gyangriha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "index"; // Maps to src/main/resources/templates/index.html
    }

    @GetMapping("/users")
    public String users() {
        return "user"; // Maps to src/main/resources/templates/user.html
    }
}
