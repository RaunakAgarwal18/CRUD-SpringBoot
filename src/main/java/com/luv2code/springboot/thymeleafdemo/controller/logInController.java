package com.luv2code.springboot.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class logInController {
    
    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage(){
        return "fancy-login";
    }
    @GetMapping("/access-denied")
    public String noAccess(){
        return "noAccess";
    }
}
