package com.laurentiuspilca.ssia.controllers;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello!";
    }

    @GetMapping("/bye")
    @Async
    public void goodbye() {
        System.out.println("start method goodbye");
        SecurityContext context = SecurityContextHolder.getContext();
        //возникнет NullPointerException, т.к. метод теперь выполняется в другом потоке, который не наследует контекст безопасности.
        //ps для этого кейса необходимо удалить конфиг AsyncConfig
        String username = context.getAuthentication().getName();
        System.out.println(username);
    }
}
