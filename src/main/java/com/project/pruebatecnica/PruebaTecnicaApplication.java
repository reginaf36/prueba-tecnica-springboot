package com.project.pruebatecnica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.project.pruebatecnica.repository.IdentityDocumentRepository;

@SpringBootApplication
@EnableMongoRepositories
public class PruebaTecnicaApplication implements CommandLineRunner{
	
	@Autowired
	IdentityDocumentRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(PruebaTecnicaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {	
		
		System.out.println("Running");
	}

}
