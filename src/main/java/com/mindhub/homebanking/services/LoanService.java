package com.mindhub.homebanking.services;

import com.mindhub.homebanking.dtos.LoanDTO;
import com.mindhub.homebanking.models.Loan;
import java.util.List;

public interface LoanService {

    List<Loan> getAllLoans();

    List<LoanDTO> getAllLoansDTO();

    Loan getLoanById(Long id);

    Loan getLoanByName(String name);


    Boolean existsLoanByName(String name);

    void saveLoan(Loan loan);


}
