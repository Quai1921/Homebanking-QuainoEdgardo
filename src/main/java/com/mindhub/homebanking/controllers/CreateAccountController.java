package com.mindhub.homebanking.controllers;


import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.services.AccountService;
import com.mindhub.homebanking.services.ClientService;
import com.mindhub.homebanking.utilServices.RandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/clients/current")
public class CreateAccountController {


    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;


    @GetMapping("/accounts")
    public ResponseEntity<?> getAccounts() {
        String userMail = SecurityContextHolder.getContext().getAuthentication().getName();
        Client client = clientService.getClientByEmail(userMail);

        return ResponseEntity.ok(accountService.getAllAccountsDTOByAccountHolder(client));

    }


    @PostMapping("/accounts")
    public ResponseEntity<?> createAccount(){
        String userMail = SecurityContextHolder.getContext().getAuthentication().getName();
        Client client = clientService.getClientByEmail(userMail);

        List<Account> accounts = accountService.getAllAccountsByAccountHolder(client);

        if(accounts.size() < 3){
            String accountNumber;
            do {
                accountNumber = "VIN-" + String.format("%08d", RandomNumberGenerator.getRandomNumber(0, 100000000));
            } while (accountService.getAccountByNumber(accountNumber) != null);


            Account account = new Account(accountNumber, LocalDate.now(), 0);
            client.addAccount(account);
            clientService.saveClient(client);
            accountService.saveAccount(account);
            return new ResponseEntity<> ("Account created", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<> ("Dear client, you reached the maximum number of accounts allowed (3)", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/accounts/{id}")
    public  ResponseEntity<?> getAccountById(@PathVariable Long id){
        String userMail = SecurityContextHolder.getContext().getAuthentication().getName();
        Client client = clientService.getClientByEmail(userMail);

        Account account = accountService.findById(id);
        if(account != null && account.getAccountHolder().equals(client)){
            return ResponseEntity.ok(new AccountDTO(account));
        }

        return new ResponseEntity<>("The account with Id: " + id + " is not found in the database.", HttpStatus.NOT_FOUND);
    }

}




//    @GetMapping("/accounts/{id}")
//    public  ResponseEntity<?> getAccountById(@PathVariable Long id){
//        Account account = accountService.findById(id);
//        if(account == null){
//            return new ResponseEntity<>("The account with Id: " + id + " is not found in the database.", HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(account, HttpStatus.OK);
//    }


//    @Autowired
//    private ClientRepository clientRepository;


//    @Autowired
//    private AccountRepository accountRepository;


//@GetMapping("/accounts")
//public ResponseEntity<?> getAccounts() {
//    String userMail = SecurityContextHolder.getContext().getAuthentication().getName();
//    Client client = clientService.getClientByEmail(userMail);
//
//    List<Account> accounts = client.getAccounts().stream().toList();
//
//    return ResponseEntity.ok(accounts.stream().map(AccountDTO::new).toList());
//}




//@PostMapping("/accounts")
//public ResponseEntity<?> createAccount(){
//    String userMail = SecurityContextHolder.getContext().getAuthentication().getName();
//    Client client = clientService.getClientByEmail(userMail);
//
////      List<Account> accounts = client.getAccounts().stream().toList();
//
//    if(accounts.size() < 3){
//        String accountNumber;
//        do {
//            accountNumber = "VIN" + String.format("%08d", randomNumber.getRandomNumber(0, 100000000));
//        } while (accountService.getAccountByNumber(accountNumber) != null);
//
//
//        Account account = new Account(accountNumber, LocalDate.now(), 0);
//        client.addAccount(account);
//        clientService.saveClient(client);
//        accountService.saveAccount(account);
//        return new ResponseEntity<> ("Account created", HttpStatus.CREATED);
//    } else {
//        return new ResponseEntity<> ("Dear client, you reached the maximum number of accounts allowed (3)", HttpStatus.FORBIDDEN);
//    }
//}