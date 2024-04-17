package com.mindhub.homebanking.models;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName, lastName, email, password;

    @OneToMany(mappedBy="accountHolder", fetch = FetchType.EAGER)
    private List<Account> accounts = new ArrayList<>();

    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    private List<ClientLoan> clientLoans = new ArrayList<>();


    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    private List<Card> cards = new ArrayList<>();


    // CONSTRUCTOR VACÍO
    public Client() {
    }

    // CONSTRUCTOR SIN ID PORQUE SE VA A GENERAR DESDE LA BASE DE DATOS
    public Client(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }


    public List<ClientLoan> getClientLoans() {
        return clientLoans;
    }

    public void setClientLoans(List<ClientLoan> clientLoans) {
        this.clientLoans = clientLoans;
    }


    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // GENERACIÓN DE UN MÉTODO PARA AGREGARLE CUENTAS AL OBJETO CLIENTE QUE ESTOY CREANDO
    // EL CLIENTE QUE CREE VA A LLAMAR AL MÉTODO ADDACCOUNT PARA AGREGAR ESA CUENTA A LA LISTA DE CUENTAS
    public void addAccount(Account account){
        account.setAccountHolder(this);
        this.accounts.add(account);
    }


    // GENERACIÓN DE UN MÉTODO PARA AGREGARLE PRÉSTAMOS AL OBJETO CLIENTE QUE ESTOY CREANDO
    // EL CLIENTE QUE CREE, VA A LLAMAR AL MÉTODO ADDLOANCLIENT PARA AGREGAR ESE PRESTAMO A LA LISTA DE PRESTAMOS
    public void addLoanClient(ClientLoan clientLoan){
        clientLoan.setClient(this);
        this.clientLoans.add(clientLoan);
    }



    // GENERACIÓN DE UN MÉTODO PARA AGREGARLE TARJETAS AL OBJETO CLIENTE QUE ESTOY CREANDO
    // A LA CARD LE SETEO EL NOMBRE Y EL APELLIDO
    // EL CLIENTE QUE CREE, VA A LLAMAR AL MÉTODO ADDCARDCLIENT PARA AGREGAR ESA TARJETA A LA LISTA DE TARJETAS
    //

    public void addCardClient(Card card){
        card.setClient(this);
        this.cards.add(card);
        card.setCardholder(card.getClient().getFirstName() + " " + card.getClient().getLastName());
    }




    // MÉTODO PARA DEVOLVER LA LISTA DE PRESTAMOS DE UN CLIENTE
//    public List<Loan> getLoans() {
//        return clientLoans.stream().map(client -> client.getLoanToClients()).collect(toList());
//    }





    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", accounts=" + accounts +
                ", clientLoans=" + clientLoans +
                ", cards=" + cards +
//                ", password=" + password +
                '}';
    }
}
