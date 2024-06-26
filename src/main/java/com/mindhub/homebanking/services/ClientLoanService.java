package com.mindhub.homebanking.services;

import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.ClientLoan;
import com.mindhub.homebanking.models.Loan;

public interface ClientLoanService {

    Boolean getClientLoanByLoanAndClient(Loan loan, Client client);

    void saveClientLoan(ClientLoan clientLoan);
}
