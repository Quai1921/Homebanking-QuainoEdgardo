package com.mindhub.homebanking.dtos;


import com.mindhub.homebanking.models.ClientLoan;
import com.mindhub.homebanking.models.Loan;


import java.util.ArrayList;
import java.util.List;


public class LoanDTO {

    private Long id;

    private String name;

    private double maxAmount;

    private List<Integer> payments = new ArrayList<>();


    private List<ClientLoan> clientLoans;


    public LoanDTO(Loan loan) {
        this.id = loan.getId();
        this.name = loan.getName();
        this.maxAmount = loan.getMaxAmount();
        this.payments = loan.getPayments();
        this.clientLoans = loan.getClientLoans();
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

    public List<ClientLoan> getClientLoans() {
        return clientLoans;
    }
}
