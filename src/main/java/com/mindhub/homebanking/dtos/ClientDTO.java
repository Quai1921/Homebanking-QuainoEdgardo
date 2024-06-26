package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Client;



import java.util.List;
import static java.util.stream.Collectors.toList;

public class ClientDTO {
    private Long id;

    private String firstName, lastName, email;

    private List<AccountDTO> accounts;

    private List<ClientLoanDTO> loans;

    private List<CardDTO> cards;


    // CREO UN CONSTRUCTOR QUE RECIBE COMO PARÁMETRO EL OBJETO CLIENTE
    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
//        this.password = client.getPassword();
        this.accounts = client.getAccounts().stream().map(AccountDTO::new).collect(toList());
        this.loans = client.getClientLoans().stream().map(ClientLoanDTO::new).collect(toList());
        this.cards = client.getCards().stream().map(card -> new CardDTO(card)).collect(toList());
        // OTRA MANERA DE DECLARARLO SIN LAMBDA
//      this.accounts = client.getAccounts().stream().map(account -> new AccountDTO(account)).collect(toList());
//
    }

    // GENERO SÓLO LOS GETTERS
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

//    public String getPassword() {
//        return password;
//    }

    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    public List<ClientLoanDTO> getLoans() {
        return loans;
    }

    public List<CardDTO> getCards() {
        return cards;
    }
}
