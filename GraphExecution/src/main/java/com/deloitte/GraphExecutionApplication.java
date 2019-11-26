package com.deloitte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class GraphExecutionApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(GraphExecutionApplication.class, args);
		//applicationContext.getBean(RequestMappingHandlerMapping.class).getHandlerMethods().forEach( () -> s.);
	}

	@Bean
	public ObjectMapper getObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		return mapper;
	}

}
