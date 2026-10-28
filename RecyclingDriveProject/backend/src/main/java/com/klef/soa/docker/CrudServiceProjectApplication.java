package com.klef.soa.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
@SpringBootApplication
public class CrudServiceProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudServiceProjectApplication.class, args);
System.out.println("Crud Service project is Running !!");

	}

}
