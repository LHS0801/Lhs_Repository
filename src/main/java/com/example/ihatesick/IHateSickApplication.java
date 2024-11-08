package com.example.ihatesick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class IHateSickApplication{
	public static void main(String[] args) {
		SpringApplication.run(IHateSickApplication.class, args);
	}

}





