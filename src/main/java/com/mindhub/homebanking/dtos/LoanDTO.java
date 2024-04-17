package com.mindhub.homebanking.dtos;


import com.mindhub.homebanking.models.ClientLoan;
import com.mindhub.homebanking.models.Loan;


import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;


public class LoanDTO {

    private Long id;

    private String name;

    private double maxAmount;

    private List<Integer> payments = new ArrayList<>();


//    private List<ClientLoanDTO> clientLoans;


    public LoanDTO(Loan loan) {
        this.id = loan.getId();
        this.name = loan.getName();
        this.maxAmount = loan.getMaxAmount();
        this.payments = loan.getPayments();
//        this.clientLoans = loan.getClientLoans().stream().map(ClientLoanDTO::new).collect(toList());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public List<Integer> getPayments() {
        return payments;
    }

//    public List<ClientLoanDTO> getClientLoans() {
//        return clientLoans;
//    }
}
