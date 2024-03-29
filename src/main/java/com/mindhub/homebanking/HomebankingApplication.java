package com.mindhub.homebanking;

import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository) {
		return args -> {
			Client Melba = new Client("Melba", "Morel", "melba@mindhub.com");
			Client Edgardo = new Client("Edgardo", "Quaino", "equaino.ir@gmail.com");

			clientRepository.save(Melba);
			clientRepository.save(Edgardo);

			System.out.println(Melba);
			System.out.println(Edgardo);


		};


	}
}
