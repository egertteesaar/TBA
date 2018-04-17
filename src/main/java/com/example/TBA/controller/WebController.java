package com.example.TBA.controller;

import com.example.TBA.service.UserInfoExtracter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class WebController {

    @Autowired
    UserInfoExtracter extracter;


    @RequestMapping("/privacy")
    public String welcome(Map<String, Object> model) {
        return "privacy";
    }

    @RequestMapping("/")
    public String index(HttpServletRequest request){
        extracter.extract(request);

        return "index";
    }

    @RequestMapping("/cart")
    public String cart() {
        return "cart";
    }

    @RequestMapping("/contact")
    public String contact() {
        return "contact";
    }
}
