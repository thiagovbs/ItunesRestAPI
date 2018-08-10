package com.desafio.stefanini.itunesrestclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ItunesRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItunesRestApiApplication.class, args);
	}
}
