package com.api.versioning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/test")
public class Test {

    @GetMapping("/getMessage")
    public String getMessage(){
        return "Hi I am Up now!!";
    }
}
