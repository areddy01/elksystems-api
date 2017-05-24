package com.elksystems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan	
public class ElkSystemsBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElkSystemsBootApplication.class, args);
	}

/*	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}*/
}
