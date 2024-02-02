package com.mindhub.homebanking;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.LocalDate;
import java.time.LocalDateTime;


@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository) {
		return args -> {

			Client morel = new Client("Melba", "Morel", "melba@mindhub.com");
			Client quaino = new Client("Edgardo", "Quaino", "equaino.ir@gmail.com");

			Account morelAccount1 = new Account("VIN001", LocalDate.now(), 5000.00);
			Account morelAccount2 = new Account("VIN002", LocalDate.now().plusDays(1), 7500.00);

			Account quainoAccount1 = new Account("VIN003", LocalDate.now(), 10000.85);
			Account quainoAccount2 = new Account("VIN004", LocalDate.now().plusDays(2),5000.25);

			Transaction vin001Transaction01 = new Transaction(-1500, "Transfer Sent", LocalDateTime.now(), TransactionType.DEBIT);
			Transaction vin001Transaction02 = new Transaction(2000, "Transfer Received", LocalDateTime.now(), TransactionType.CREDIT);
			Transaction vin002Transaction01 = new Transaction(-500, "Transfer Sent", LocalDateTime.now(), TransactionType.DEBIT);
			Transaction vin002Transaction02 = new Transaction(800, "Transfer Received", LocalDateTime.now(), TransactionType.CREDIT);

			Transaction vin003Transaction01 = new Transaction(1800, "Transfer Received", LocalDateTime.now(), TransactionType.CREDIT);
			Transaction vin003Transaction02 = new Transaction(-500, "Transfer Sent", LocalDateTime.now(), TransactionType.DEBIT);
			Transaction vin004Transaction01 = new Transaction(5000, "Transfer Received", LocalDateTime.now(), TransactionType.CREDIT);


			morel.addAccount(morelAccount1);
			morel.addAccount(morelAccount2);

			quaino.addAccount(quainoAccount1);
			quaino.addAccount(quainoAccount2);

			morelAccount1.addTransaction(vin001Transaction01);
			morelAccount1.addTransaction(vin001Transaction02);
			morelAccount2.addTransaction(vin002Transaction01);
			morelAccount2.addTransaction(vin002Transaction02);

			quainoAccount1.addTransaction(vin003Transaction01);
			quainoAccount1.addTransaction(vin003Transaction02);
			quainoAccount2.addTransaction(vin004Transaction01);


			clientRepository.save(morel);
			clientRepository.save(quaino);


			accountRepository.save(morelAccount1);
			accountRepository.save(morelAccount2);
			accountRepository.save(quainoAccount1);
			accountRepository.save(quainoAccount2);

			transactionRepository.save(vin001Transaction01);
			transactionRepository.save(vin001Transaction02);
			transactionRepository.save(vin002Transaction01);
			transactionRepository.save(vin002Transaction02);
			transactionRepository.save(vin003Transaction01);
			transactionRepository.save(vin003Transaction02);
			transactionRepository.save(vin004Transaction01);


			System.out.println(morel);
			System.out.println(quaino);


		};





	}
}
