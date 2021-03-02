package com.workshop.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    private String index() {
        return "index";
    }

    @GetMapping("/home")
    private String home() {
        return "index";
    }
}
