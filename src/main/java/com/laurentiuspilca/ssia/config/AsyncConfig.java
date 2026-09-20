package com.laurentiuspilca.ssia.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    //подменяем стандартный executor Spring (тот самый SimpleAsyncTaskExecutor, создающий новый поток на каждый вызов)
    @Override
    public Executor getAsyncExecutor() {
        //Это Spring-обёртка над java.util.concurrent.ThreadPoolExecutor — стандартный пул потоков.
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("async-");
        executor.initialize();

        // DelegatingSecurityContextAsyncTaskExecutor — это декоратор над вашим ThreadPoolTaskExecutor. Он не заменяет пул, а оборачивает его. Когда Spring отправляет задачу (ваш @Async метод) в этот executor, обёртка:
        //
        //В момент отправки задачи (в потоке HTTP-запроса, где SecurityContext ещё доступен) захватывает текущий SecurityContext из SecurityContextHolder.
        //
        //Оборачивает исходный Runnable/Callable в специальный DelegatingSecurityContextRunnable/Callable, который несёт в себе этот контекст.
        //
        //Внутри рабочего потока (async-N) перед запуском задачи устанавливает сохранённый контекст в SecurityContextHolder, выполняет задачу, а после — очищает.
        return new DelegatingSecurityContextAsyncTaskExecutor(executor);
    }
}
