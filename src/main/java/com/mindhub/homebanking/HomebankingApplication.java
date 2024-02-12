package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository, CardRepository cardRepository) {
		return args -> {

			Client morel = new Client("Melba", "Morel", "melba@mindhub.com");
			Client quaino = new Client("Edgardo", "Quaino", "equaino.ir@gmail.com");

			clientRepository.save(morel);
			clientRepository.save(quaino);


			Account morelAccount1 = new Account("VIN001", LocalDate.now(), 5000.00);
			Account morelAccount2 = new Account("VIN002", LocalDate.now().plusDays(1), 7500.00);

			Account quainoAccount1 = new Account("VIN003", LocalDate.now(), 10000.85);
			Account quainoAccount2 = new Account("VIN004", LocalDate.now().plusDays(2),5000.25);

			morel.addAccount(morelAccount1);
			morel.addAccount(morelAccount2);

			quaino.addAccount(quainoAccount1);
			quaino.addAccount(quainoAccount2);

			accountRepository.save(morelAccount1);
			accountRepository.save(morelAccount2);
			accountRepository.save(quainoAccount1);
			accountRepository.save(quainoAccount2);


			Transaction vin001Transaction01 = new Transaction(-1500, "Transfer Sent", LocalDateTime.now(), TransactionType.DEBIT);
			Transaction vin001Transaction02 = new Transaction(2000, "Transfer Received", LocalDateTime.now(), TransactionType.CREDIT);
			Transaction vin002Transaction01 = new Transaction(-500, "Transfer Sent", LocalDateTime.now(), TransactionType.DEBIT);
			Transaction vin002Transaction02 = new Transaction(800, "Transfer Received", LocalDateTime.now(), TransactionType.CREDIT);

			Transaction vin003Transaction01 = new Transaction(1800, "Transfer Received", LocalDateTime.now(), TransactionType.CREDIT);
			Transaction vin003Transaction02 = new Transaction(-500, "Transfer Sent", LocalDateTime.now(), TransactionType.DEBIT);
			Transaction vin004Transaction01 = new Transaction(5000, "Transfer Received", LocalDateTime.now(), TransactionType.CREDIT);

			morelAccount1.addTransaction(vin001Transaction01);
			morelAccount1.addTransaction(vin001Transaction02);
			morelAccount2.addTransaction(vin002Transaction01);
			morelAccount2.addTransaction(vin002Transaction02);

			quainoAccount1.addTransaction(vin003Transaction01);
			quainoAccount1.addTransaction(vin003Transaction02);
			quainoAccount2.addTransaction(vin004Transaction01);


			transactionRepository.save(vin001Transaction01);
			transactionRepository.save(vin001Transaction02);
			transactionRepository.save(vin002Transaction01);
			transactionRepository.save(vin002Transaction02);
			transactionRepository.save(vin003Transaction01);
			transactionRepository.save(vin003Transaction02);
			transactionRepository.save(vin004Transaction01);


//			List <Integer> mortgage1 = List.of(12,24);

			Loan mortgage = new Loan("Mortgage", 500000, List.of(12,24,36,48,60));
			Loan personal = new Loan("Personal", 100000, List.of(6,12,24));
			Loan automotive = new Loan("Automotive", 300000, List.of(6,12,24,36));

			loanRepository.save(mortgage);
			loanRepository.save(personal);
			loanRepository.save(automotive);



			ClientLoan clientMorel1 = new ClientLoan(400000.0, 60);
			ClientLoan clientMorel2 = new ClientLoan(50000.0, 12);

			ClientLoan clientQuaino1 = new ClientLoan(100000.0, 24);
			ClientLoan clientQuaino2 = new ClientLoan(200000.0, 36);


			morel.addLoanClient(clientMorel1);
			morel.addLoanClient(clientMorel2);
			quaino.addLoanClient(clientQuaino1);
			quaino.addLoanClient(clientQuaino2);

			mortgage.addClientLoan(clientMorel1);
			personal.addClientLoan(clientMorel2);
			personal.addClientLoan(clientQuaino1);
			automotive.addClientLoan(clientQuaino2);


			clientLoanRepository.save(clientMorel1);
			clientLoanRepository.save(clientMorel2);
			clientLoanRepository.save(clientQuaino1);
			clientLoanRepository.save(clientQuaino2);


			Card morelDebitCardGold = new Card(CardType.DEBIT, CardColor.GOLD, "4815-5009-0177-2190", "807", LocalDate.now());
			Card morelCreditCardTitanium = new Card(CardType.CREDIT, CardColor.TITANIUM, "3625-8532-0845-0003", "009", LocalDate.now());

			Card quainoCreditCardSilver = new Card(CardType.CREDIT, CardColor.SILVER, "1921-1492-0920-2198", "357", LocalDate.now());
			Card quainoDebitCardTitanium = new Card(CardType.DEBIT, CardColor.TITANIUM, "2879-4569-0514-3214", "963", LocalDate.now());

			// OTRA OPCIÓN SETARLE LOS NOMBRES UNA VEZ CREADA LA CARD. HAY QUE REMOVER LA ÚLTIMA LINEA DEL MÉTODO addCardClient.
			// morel.addCardClient(morelDebitCardGold);
			// morelDebitCardGold.setCardholder(morelDebitCardGold.getClient().getFirstName() + " " + morelDebitCardGold.getClient().getLastName());

			morel.addCardClient(morelDebitCardGold);
			morel.addCardClient(morelCreditCardTitanium);

			quaino.addCardClient(quainoCreditCardSilver);
			quaino.addCardClient(quainoDebitCardTitanium);

			cardRepository.save(morelDebitCardGold);
			cardRepository.save(morelCreditCardTitanium);
			cardRepository.save(quainoCreditCardSilver);
			cardRepository.save(quainoDebitCardTitanium);



			System.out.println(morel);
			System.out.println(quaino);




		};





	}
}
