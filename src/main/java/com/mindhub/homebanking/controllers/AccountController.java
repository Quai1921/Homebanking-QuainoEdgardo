package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/accounts")

public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public List<AccountDTO> getAllAccounts(){
        return accountService.getAllAccountsDTO();
    }

}














//@CrossOrigin(origins = "*")



//    @Autowired
//    private AccountRepository accountRepository;



//@GetMapping("/")
//public List<AccountDTO> getAllAccounts(){
//        return accountRepository.findAll().stream().map(account -> new AccountDTO(account)).collect(toList());
//}