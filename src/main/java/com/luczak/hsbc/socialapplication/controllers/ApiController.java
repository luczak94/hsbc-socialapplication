package com.luczak.hsbc.socialapplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ApiController {

    @GetMapping()
    public String getDocs() {
        return "redirect:swagger-ui.html";
    }


}
