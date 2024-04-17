package com.mindhub.homebanking.models;

import com.mindhub.homebanking.repositories.*;
import com.mindhub.homebanking.utilServices.RandomNumberGenerator;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

// hamcrest FRAMEWORK ENTRE MUCHOS OTROS DISPONIBLES

@DataJpaTest                //PORQUE SE USAN REPOSITORIOS Y PARA QUE TENGAN EL CONTEXTO DE JPA
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoriesTest {

//    @Autowired
//    private ClientRepository clientRepository;
//
//    @Autowired
//    private AccountRepository accountRepository;
//
//    @Autowired
//    private CardRepository cardRepository;
//
//    @Autowired
//    private LoanRepository loanRepository;
//
//    @Autowired
//    private TransactionRepository transactionRepository;
//
//
//
//    // TEST UNITARIOS --------------------------------------------------------------------------------------------------
//    @Test       // @RepeatedTest(1000)
//    void testGenertateCVV(){
//        int min = 0;
//        int max = 1000;
//        String cvv = String.format("%03d", RandomNumberGenerator.getRandomNumber(min, max));
//        assertThat(cvv, is(notNullValue()));
//        assertThat(cvv.length(), equalTo(3));
//    }
//
//
//    @Test       // @RepeatedTest(1000)
//    void testGenertateAccountNumber(){
//        int min = 0;
//        int max = 100000000;
//        String cvv = String.format("%08d", RandomNumberGenerator.getRandomNumber(min, max));
//        assertThat(cvv, is(notNullValue()));
//        assertThat(cvv.length(), equalTo(8));
//    }
//
//
//    @Test       // @RepeatedTest(1000)
//    void testGenertateCardNumber(){
//        int min = 0;
//        int max = 10000;
//        String cardNumber = String.format("%04d", RandomNumberGenerator.getRandomNumber(min, max)) + "-"
//                          + String.format("%04d", RandomNumberGenerator.getRandomNumber(min, max)) + "-"
//                          + String.format("%04d", RandomNumberGenerator.getRandomNumber(min, max)) + "-"
//                          + String.format("%04d", RandomNumberGenerator.getRandomNumber(min, max));
//        assertThat(cardNumber, is(notNullValue()));
//        assertThat(cardNumber.length(), equalTo(19));
//    }
//
//
//
//
//
//
//    // TEST INTEGRADORES -----------------------------------------------------------------------------------------------
//    // CARDS -----------------------------------------------------------------------------------------------------------
//    @Test
//    public void testHaveCards(){
//        List<Card> cards = cardRepository.findAll();
//        assertThat(cards,is(not(empty())));
//        assertThat(cards.stream().map(Card::getCardholder).noneMatch((cardholder-> cardholder.isBlank())), is(true));
//        assertThat(cards.stream().map(Card::getThruDate).noneMatch(thruDate -> thruDate == null || thruDate.isBefore(LocalDate.now())), is(true));
//        assertThat(cards.stream().map(Card::getNumber).allMatch((number-> number.length() == 19)), is(true));
//        assertThat(cards.stream().map(Card::getCvv).allMatch((cvv-> cvv.length() == 3)), is(true));
//    }
//
//    @Test
//    public void testClientHaveCards(){
//        Boolean card = cardRepository.existsCardByCardholder("Melba Morel");
//        assertThat(card,is(true));
//    }
//
//    @Test
//    public void testFindCardByNumber(){
//        Card card = cardRepository.findByNumber("4815-5009-0177-2190");
//        assertThat(card.getNumber(),equalTo("4815-5009-0177-2190"));
//    }
//
//
//
//    // LOANS -----------------------------------------------------------------------------------------------------------
//    @Test
//    public void testHaveLoans(){
//        List<Loan> loans = loanRepository.findAll();
//        assertThat(loans,is(not(empty())));
//        assertThat(loans.stream().map(Loan::getPayments).noneMatch(payments -> payments == null || payments.isEmpty()), is(true));
//    }
//
//    @Test
//    public void testExistLoan(){
//        Boolean loan = loanRepository.existsLoanByName("Personal");
//        assertThat(loan,is(true));
//    }
//
//    @Test
//    public void testExistAutomotivelLoan(){
//        List<Loan> loans = loanRepository.findAll();
//        assertThat(loans, hasItem(hasProperty("name", is("Automotive"))));
//
//    }
//
//
//    // ACCOUNTS --------------------------------------------------------------------------------------------------------
//    @Test
//    public void testHaveAccounts(){
//        List<Account> accounts = accountRepository.findAll();
//        assertThat(accounts,is(not(empty())));
//        assertThat(accounts.stream().map(Account::getNumber).allMatch((number-> number.startsWith("VIN-"))), is(true));
//        assertThat(accounts.stream().map(Account::getNumber).allMatch(number -> number.length() == 12), is(true));
//    }
//
//
//    @Test
//    public void testExistAccountByNumber(){
//        Boolean account = accountRepository.existsByNumber("VIN-00000001");
//        assertThat(account,is(true));
//    }
//
//    @Test
//    public void testExistsByNumberAndAccountHolder(){
//        Client client = clientRepository.findByEmail("equaino.ir@gmail.com");
//        Boolean account = accountRepository.existsByNumberAndAccountHolder("VIN-00000003", client);
//        assertThat(account,is(true));
//    }
//
//
//    // CLIENTS ---------------------------------------------------------------------------------------------------------
//    @Test
//    public void testHaveClients(){
//        List<Client> clients = clientRepository.findAll();
//        assertThat(clients,is(not(empty())));
//        assertThat(clients.stream().map(Client::getFirstName).noneMatch((firstName-> firstName.isBlank())), is(true));
//        assertThat(clients.stream().map(Client::getLastName).noneMatch((lastName-> lastName.isBlank())), is(true));
//        assertThat(clients.stream().map(Client::getEmail).noneMatch(email -> email.isBlank() || !email.contains("@")), is(true));
//    }
//
//    @Test
//    public void testExistClientByEmail(){
//        Boolean account = clientRepository.existsByEmail("melba@mindhub.com");
//        assertThat(account,is(true));
//    }
//
//    @Test
//    public void testExistClientByFirstNameAndLastName(){
//        Boolean account = clientRepository.existsByFirstNameAndLastName("Melba", "Morel");
//        assertThat(account,is(true));
//    }
//
//// TRANSACTIONS --------------------------------------------------------------------------------------------------------
//
//    @Test
//    public void testHaveTransactions() {
//        List<Transaction> transactions = transactionRepository.findAll();
//        assertThat(transactions, is(not(empty())));
//        assertThat(transactions.stream().map(Transaction::getDescription).noneMatch((description-> description.isBlank())), is(true));
//    }
//
//    @Test
//    public void testExistTransactionByDescription(){
//        Boolean transaction = transactionRepository.existsByDescription("Transfer Sent");
//        assertThat(transaction,is(true));
//    }

}