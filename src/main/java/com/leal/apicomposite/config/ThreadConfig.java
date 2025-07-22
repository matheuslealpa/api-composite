package com.leal.apicomposite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableAsync
public class ThreadConfig {

    @Bean(name = "traditionalExecutor")
    public Executor traditionalExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("traditional-");
        executor.setQueueCapacity(100);
        executor.setMaxPoolSize(10);
        executor.setCorePoolSize(10);
        executor.initialize();
        return executor;
    }

    @Bean(name = "virtualExecutor")
    public Executor virtualExecutor() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }
}
