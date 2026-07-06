package com.lms;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(info=@Info(title="Library Management System",
 version="1.0",
 description="This Application Contains REST-API's related to Library Management System "),
servers= {@Server(url="http://localhost:8081",description="local host")})

public class LibraryManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApplication.class, args);
	}

	
	@Bean
	ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}
//http://localhost:8081/v3/api-docs to get swagger documentation