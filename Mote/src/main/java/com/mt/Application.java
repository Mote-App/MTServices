package com.mt;
 
import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The <code>Application</code> is the Social Stairway Algorithm application entry point.
 * 
 * EnableScheduling ensures that a background task executor is created, without it nothing gets scheduled.
 * 
 * @author gibranecastillo
 *
 */
@Configuration
@EnableAutoConfiguration
@EnableScheduling
@ComponentScan
public class Application extends SpringBootServletInitializer {
	
	/**
	 * A standard method that allows the Java connection for an application entry point.
	 * This main method delegates to Spring Boot's SpringApplication class by calling the run.
	 * SpringApplication will bootstrap the mt.jar or mote service, starting Spring which will in turn
	 * start auto-configured Tomcat web server
	 * 
	 * @param args a String array passed through to expose any command-line arguments.
	 */
    public static void main(String[] args) {
        SpringApplication.run(applicationClass, args);
    }
    
    /**
     * 
     * @param application
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }
    
    private static Class<Application> applicationClass = Application.class;
    
    /**
     * 
     * @return
     */
    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("20MB");
        factory.setMaxRequestSize("20MB");
        return factory.createMultipartConfig();
    }
}