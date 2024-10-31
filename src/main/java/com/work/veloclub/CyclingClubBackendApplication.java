package com.work.veloclub;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.TimeZone;

@SpringBootApplication
        //(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class CyclingClubBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CyclingClubBackendApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowedOrigins("http://localhost:8082", "http://localhost:4200", "http://localhost:8080",
                                "http://172.19.10.207:8082", "http://172.19.10.207:4200", "http://172.19.10.207:8080",
                                "http://172.19.10.207:80", "http://localhost:80"
                                )
                        .allowedHeaders("*")
                        .allowedMethods("*");
            }
        };
    }

    @PostConstruct
    public void init(){
        // Setting Spring Boot SetTimeZone
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Moscow"));
    }


}
