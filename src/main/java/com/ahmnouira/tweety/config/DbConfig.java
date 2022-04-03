package com.ahmnouira.tweety.config;

import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/* Java Database Connectivity(JDBC) API is still blocking
 and synchronous.
there are proposals to make it fully non-blocking and asynchronous */

@Configuration
public class DbConfig {

    private Integer connectionPoolSize;

    public DbConfig(@Value("${spring.datasource.maximum-pool-size:10}") Integer connectionPoolSize) {
        this.connectionPoolSize = connectionPoolSize;
    }

    // the dbScheduler() method create a Scheduler. bean, which encoapsuactes a
    // standard
    // java fixed-size thread of pool executive tasks
    @Bean
    public Scheduler dbScheduler() {
        return Schedulers.fromExecutor(Executors.newFixedThreadPool(connectionPoolSize));
    }
}