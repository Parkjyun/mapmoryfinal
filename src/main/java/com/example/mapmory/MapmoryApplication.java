package com.example.mapmory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class MapmoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MapmoryApplication.class, args);
	}

}
