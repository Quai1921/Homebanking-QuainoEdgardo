package com.mindhub.homebanking;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.LocalDate;



@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository) {
		return args -> {

			Client morel = new Client("Melba", "Morel", "melba@mindhub.com");
			Client quaino = new Client("Edgardo", "Quaino", "equaino.ir@gmail.com");

			Account morelAccount1 = new Account("VIN001", LocalDate.now(), 5000.00);
			Account morelAccount2 = new Account("VIN002", LocalDate.now().plusDays(1), 7500.00);

			Account quainoAccount1 = new Account("VIN003", LocalDate.now(), 10000.85);
			Account quainoAccount2 = new Account("VIN004", LocalDate.now().plusDays(2),5000.25);

			morel.addAccount(morelAccount1);
			morel.addAccount(morelAccount2);

			quaino.addAccount(quainoAccount1);
			quaino.addAccount(quainoAccount2);

			clientRepository.save(morel);
			clientRepository.save(quaino);


			accountRepository.save(morelAccount1);
			accountRepository.save(morelAccount2);
			accountRepository.save(quainoAccount1);
			accountRepository.save(quainoAccount2);

			System.out.println(morel);
			System.out.println(quaino);



		};


	}
}
