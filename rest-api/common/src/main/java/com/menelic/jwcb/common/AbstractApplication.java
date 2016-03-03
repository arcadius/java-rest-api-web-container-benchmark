package com.menelic.jwcb.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { "com.menelic" })
@SpringBootApplication
public abstract class AbstractApplication extends SpringBootServletInitializer {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(AbstractApplication.class, args);
    }
}