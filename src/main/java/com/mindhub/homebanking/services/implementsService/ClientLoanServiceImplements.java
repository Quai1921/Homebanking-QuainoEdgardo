package com.mindhub.homebanking.services.implementsService;

import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.ClientLoan;
import com.mindhub.homebanking.models.Loan;
import com.mindhub.homebanking.repositories.ClientLoanRepository;
import com.mindhub.homebanking.services.ClientLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientLoanServiceImplements implements ClientLoanService {

    @Autowired
    private ClientLoanRepository clientLoanRepository;

    public Boolean getClientLoanByLoanAndClient(Loan loan, Client client) {
        return clientLoanRepository.existsByLoanAndClient(loan, client);
    }

    @Override
    public void saveClientLoan(ClientLoan clientLoan) {
        clientLoanRepository.save(clientLoan);
    }
}
