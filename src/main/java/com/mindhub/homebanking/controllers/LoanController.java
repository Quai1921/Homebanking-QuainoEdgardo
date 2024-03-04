package com.mindhub.homebanking.controllers;


import com.mindhub.homebanking.dtos.LoanDTO;
import com.mindhub.homebanking.repositories.LoanRepository;
import com.mindhub.homebanking.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;


    @GetMapping("/")
    public List<LoanDTO> getAllLoans(){
        return loanService.getAllLoansDTO();
    }



}




//  @CrossOrigin(origins = "*")


//  @Autowired
//  private LoanRepository loanRepository;


//@GetMapping("/")
//public List<LoanDTO> getAllLoans(){
//    return loanRepository.findAll().stream().map(loan -> new LoanDTO(loan)).collect(toList());
//}