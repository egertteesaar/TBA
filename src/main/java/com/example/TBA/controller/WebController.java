package com.example.TBA.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class WebController {

    @RequestMapping("/welcome")
    public String welcome(Map<String, Object> model) {
        model.put("message", "Welcome");
        return "welcome";
    }
}