package com.priority.main;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;



@SpringBootApplication(scanBasePackages="com")
@EntityScan("com.priority.model")
public class PriorityApplication {

    public static void main(String[] args) {
		SpringApplication.run(PriorityApplication.class, args);
	}
 }
