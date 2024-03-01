package com.mindhub.homebanking.controllers;


import com.mindhub.homebanking.dtos.TransactionRequestDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;


@RestController
@RequestMapping("/api/clients/current")
@CrossOrigin(origins = "*")
public class TransactionController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    @Transactional
    @PostMapping("/transactions")
    public ResponseEntity<?> createTransactionCredit (@RequestBody TransactionRequestDTO transactionRequestDTO){
        String userMail = SecurityContextHolder.getContext().getAuthentication().getName();
        Client client = clientRepository.findByEmail(userMail);


        if(transactionRequestDTO.description().isBlank()){
            return new ResponseEntity<>("The description field cannot be empty", HttpStatus.FORBIDDEN);
        }

        if(transactionRequestDTO.amount() <= 0){
            return new ResponseEntity<>("The amount entered is not valid", HttpStatus.FORBIDDEN);
        }


        if(transactionRequestDTO.numberDebit().isBlank()){
            return new ResponseEntity<>("The origin account field cannot be empty", HttpStatus.FORBIDDEN);
        }


        if(transactionRequestDTO.numberCredit().isBlank()){
            return new ResponseEntity<>("The destination account field cannot be empty", HttpStatus.FORBIDDEN);
        }


//        OTRA MANERA DE HACERLO
//        if(!client.getAccounts().stream().anyMatch(account -> account.getNumber().equals(transactionRequestDTO.numberDebit()))){
//            return new ResponseEntity<>("The origin account is not valid", HttpStatus.FORBIDDEN);
//        }

        Boolean accountExist = accountRepository.existsByNumberAndAccountHolder(transactionRequestDTO.numberDebit(), client);

        if(!accountExist){
            return new ResponseEntity<>("The origin account is not valid", HttpStatus.FORBIDDEN);
        }


        Account accountCredit = accountRepository.findByNumber(transactionRequestDTO.numberCredit());

        if(accountCredit == null){
            return new ResponseEntity<>("The account entered does not exist", HttpStatus.FORBIDDEN);
        }

        if(transactionRequestDTO.numberDebit().equals(transactionRequestDTO.numberCredit())){
            return new ResponseEntity<>("Invalid operation. The accounts entered are the same", HttpStatus.FORBIDDEN);
        }


        Account accountDebit = accountRepository.findByNumber(transactionRequestDTO.numberDebit());

        if(accountDebit.getBalance() < transactionRequestDTO.amount()){
            return new ResponseEntity<>("Insufficient funds. Please indicate a valid amount", HttpStatus.FORBIDDEN);
        }

        accountDebit.setBalance(accountDebit.getBalance() - transactionRequestDTO.amount());
        accountCredit.setBalance(accountCredit.getBalance() + transactionRequestDTO.amount());

        Transaction transactionDebit = new Transaction(-(transactionRequestDTO.amount()), transactionRequestDTO.description(), LocalDateTime.now(), TransactionType.DEBIT);
        Transaction transactionCredit = new Transaction(transactionRequestDTO.amount(), transactionRequestDTO.description(), LocalDateTime.now(), TransactionType.CREDIT);
        accountDebit.addTransaction(transactionDebit);
        accountCredit.addTransaction(transactionCredit);
        clientRepository.save(client);
        accountRepository.save(accountDebit);
        accountRepository.save(accountCredit);
        transactionRepository.save(transactionDebit);
        transactionRepository.save(transactionCredit);


        return new ResponseEntity<>("Transaction created", HttpStatus.CREATED);

    }



}
