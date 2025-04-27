package com.app.play11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class Play11Application {

	public static void main(String[] args) {
		SpringApplication.run(Play11Application.class, args);
		log.info("XXXXX Play11 Started Successfully XXXXX");
	}

}
