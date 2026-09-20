package com.laurentiuspilca.ssia.controllers;

import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
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

    @GetMapping("/ciao")
    public String ciao() throws Exception {
        Callable<String> task = () -> {
            SecurityContext context = SecurityContextHolder.getContext();
            return context.getAuthentication().getName();
        };
        //Создаётся пул потоков (кешированный). Задача будет выполняться не в потоке обработки запроса, а в отдельном.
        ExecutorService e = Executors.newCachedThreadPool();
        try {
            //DelegatingSecurityContextCallable — обёртка из Spring Security.
            // Она захватывает текущий SecurityContext в момент создания обёртки (в потоке запроса, где контекст есть)
            // и перед выполнением задачи устанавливает его в потоке-исполнителе, а после — очищает.
            var contextTask = new DelegatingSecurityContextCallable<>(task);
            return "Ciao, " + e.submit(contextTask).get() + "!";
        } finally {
            e.shutdown();
        }
    }
}
