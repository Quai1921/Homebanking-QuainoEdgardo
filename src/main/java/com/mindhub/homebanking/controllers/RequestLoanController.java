package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.LoanAplicationDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
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
public class RequestLoanController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ClientLoanRepository clientLoanRepository;


    @Transactional
    @PostMapping("/loans")
    public ResponseEntity<?> requestLoan (@RequestBody LoanAplicationDTO loanAplicationDTO) {
        String userMail = SecurityContextHolder.getContext().getAuthentication().getName();
        Client client = clientRepository.findByEmail(userMail);

        if(loanAplicationDTO.amount() <= 0){
            return new ResponseEntity<>("The amount field cannot be empty", HttpStatus.FORBIDDEN);
        }

        if(loanAplicationDTO.payments() <= 0){
            return new ResponseEntity<>("The payments field cannot be empty", HttpStatus.FORBIDDEN);
        }

        if(loanAplicationDTO.numberAccount().isBlank()){
            return new ResponseEntity<>("The account field cannot be empty", HttpStatus.FORBIDDEN);
        }

        Loan loan = loanRepository.findById(loanAplicationDTO.id()).orElse(null);

        if(loan == null){
            return new ResponseEntity<>("The selected loan does not exist", HttpStatus.FORBIDDEN);
        }


        if(loanAplicationDTO.amount() > loan.getMaxAmount()){
            return new ResponseEntity<>("The loan amount must be less than " + loan.getMaxAmount(), HttpStatus.FORBIDDEN);
        }

        if(!loan.getPayments().contains(loanAplicationDTO.payments())){
            return new ResponseEntity<>("The amount of payments must be between " + loan.getPayments(), HttpStatus.FORBIDDEN);
        }

        Boolean accountExist = accountRepository.existsByNumberAndAccountHolder(loanAplicationDTO.numberAccount(), client);

        if(!accountExist){
            return new ResponseEntity<>("The destination account is not valid", HttpStatus.FORBIDDEN);
        }


        Account accountCredit = accountRepository.findByNumber(loanAplicationDTO.numberAccount());

        if(accountCredit == null){
            return new ResponseEntity<>("The destination account does not exist " + loan.getPayments(), HttpStatus.FORBIDDEN);
        }



        ClientLoan clientLoan = new ClientLoan(loanAplicationDTO.amount(), loanAplicationDTO.payments());
        Transaction transaction = new Transaction(loanAplicationDTO.amount(),loan.getName(), LocalDateTime.now(), TransactionType.CREDIT);
        client.addLoanClient(clientLoan);
        loan.addClientLoan(clientLoan);
        accountCredit.addTransaction(transaction);
        clientRepository.save(client);
        accountRepository.save(accountCredit);
        transactionRepository.save(transaction);
        loanRepository.save(loan);
        clientLoanRepository.save(clientLoan);


        return new ResponseEntity<>("Loan created", HttpStatus.CREATED);
    }

}
