package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloController {

    @GetMapping("southello")
    public void hello (){
        System.out.println("Hello world");
    }

    @GetMapping("hello2")
    public ResponseEntity<String> hello2 (){
        return ResponseEntity.ok("HelloWorldMamou");
    }

}
