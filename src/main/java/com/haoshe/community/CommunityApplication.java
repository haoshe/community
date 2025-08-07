package com.haoshe.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
@SpringBootApplication marks the class as the main entry point of a Spring Boot application.
It serves as a configuration class, enables autoconfiguration, and activates component scanning.
It is a convenience annotation that combines three key Spring annotations
1. @Configuration: marks the class as a source of bean definitions (like a configuration file in Spring).
2. @EnableAutoConfiguration: tells Spring Boot to automatically configure beans based on the classpath and existing beans.
3. @ComponentScan: Enables scanning of components (like @Controller, @Service, @Repository) in the package and sub-packages.
   Business service -> @Service
   Handling HTTP request -> @Controller
   Access Data base -> @Repository
   Generic  -> @Component
 */
@SpringBootApplication
public class CommunityApplication {

	public static void main(String[] args) {
		/*
		1. Spring Boot initializes and starts the Spring application context(IoC container).
		2. All beans (classes annotated with @Component, @Service, @Repository, @Controller, or beans
		   defined in @Configuration) are scanned, created, and managed by the container.
		3. Autoconfiguration is applied, and any embedded server(Tomcat) is started.
		 */
		SpringApplication.run(CommunityApplication.class, args);
	}
}
