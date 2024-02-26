package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "*")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/")
    public List<AccountDTO> getAllAccounts(){
        return accountRepository.findAll().stream().map(account -> new AccountDTO(account)).collect(toList());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getOneClientById(@PathVariable Long id){
        Account account = accountRepository.findById(id).orElse(null);
        if(account == null){
            return new ResponseEntity<>("The account with Id: " + id + " is not found in the database.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new AccountDTO(account), HttpStatus.OK);
    }



}
