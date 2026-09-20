package com.laurentiuspilca.ssia.controllers;

import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello!";
    }

    @GetMapping("/hola")
    public String hola() throws Exception {
        Callable<String> task = () -> {
            SecurityContext context = SecurityContextHolder.getContext();
            return context.getAuthentication().getName();
        };
        //Создаётся пул потоков (кешированный). Задача будет выполняться не в потоке обработки запроса, а в отдельном.
        ExecutorService e = Executors.newCachedThreadPool();
        //Создаётся обёртка DelegatingSecurityContextExecutorService, внутри которой сохраняется:
        //ссылка на исходный пул (newCachedThreadPool);
        //текущий SecurityContext на момент создания обёртки — то есть контекст потока HTTP-запроса, где аутентификация уже есть.
        e = new DelegatingSecurityContextExecutorService(e);
        try {
            return "Hola, " + e.submit(task).get() + "!";
        } finally {
            e.shutdown();
        }
    }
}
