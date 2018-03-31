package com.example.TBA.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class WebController {

    @RequestMapping("/privacy")
    public String welcome(Map<String, Object> model) {
        return "privacy";
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/cart")
    public String cart() {
        return "cart";
    }
}
