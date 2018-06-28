package com.example.demo.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiyController {


    @RequestMapping("/")
    public String test() {
        return "ok";
    }

}
