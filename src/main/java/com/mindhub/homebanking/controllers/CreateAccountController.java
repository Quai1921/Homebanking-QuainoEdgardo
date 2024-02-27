package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.utilServices.RandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/clients/current")
public class CreateAccountController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RandomNumberGenerator randomNumber;


    @GetMapping("/accounts")
    public ResponseEntity<?> getAccounts() {
        String userMail = SecurityContextHolder.getContext().getAuthentication().getName();
        Client client = clientRepository.findByEmail(userMail);

        List<Account> accounts = client.getAccounts().stream().toList();

        return ResponseEntity.ok(accounts.stream().map(AccountDTO::new).toList());
    }


    @PostMapping("/accounts")
    public ResponseEntity<?> createAccount(){
        String userMail = SecurityContextHolder.getContext().getAuthentication().getName();
        Client client = clientRepository.findByEmail(userMail);

        List<Account> accounts = client.getAccounts().stream().toList();

        if(accounts.size() < 3){
            String accountNumber;
            do {
                accountNumber = "VIN" + String.format("%08d", randomNumber.getRandomNumber(0, 99999999));
            } while (accountRepository.findByNumber(accountNumber) != null);




            Account account = new Account(accountNumber, LocalDate.now(), 0);
            client.addAccount(account);
            clientRepository.save(client);
            accountRepository.save(account);
            return new ResponseEntity<> ("Account created", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<> ("Dear client, you reached the maximum number of accounts allowed (3)", HttpStatus.FORBIDDEN);
        }
    }
}
