package com.cl;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application  extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(applicationClass, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }
    
    private static Class<Application> applicationClass = Application.class;
}