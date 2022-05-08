package com.ironhack.TheDataLayerHomework3;

import com.ironhack.TheDataLayerHomework3.repository.SalesRepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TheDataLayerHomework3Application implements CommandLineRunner {

	@Autowired
	MainMenu menu;

	//Spring Boot applications need this to start
	public static void main(String[] args) {
		SpringApplication.run(TheDataLayerHomework3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		menu.menu();
	}
}
