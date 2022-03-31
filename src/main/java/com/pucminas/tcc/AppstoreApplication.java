package com.pucminas.tcc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppstoreApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(AppstoreApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
	}

}
