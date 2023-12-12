package com.kosvad9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.logging.log4j2.SpringBootConfigurationFactory;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class ApplicationRunner {
    public static void main(String[] args) {
        var context = SpringApplication.run(ApplicationRunner.class,args);
    }
}
