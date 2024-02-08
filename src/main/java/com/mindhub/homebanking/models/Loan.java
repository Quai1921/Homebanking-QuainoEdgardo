package com.mindhub.homebanking.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double maxAmount;

    @ElementCollection
    @Column(name="payments")
    private List<Integer> payments = new ArrayList<>();


    @OneToMany(mappedBy="loan", fetch=FetchType.EAGER)
    private List<ClientLoan> clientLoans = new ArrayList<>();


    public Loan() {
    }

    public Loan(String name, double maxAmount, List<Integer> payments) {
        this.name = name;
        this.maxAmount = maxAmount;
        this.payments = payments;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public void setPayments(List<Integer> payments) {
        this.payments = payments;
    }

    public List<ClientLoan> getClientLoans() {
        return clientLoans;
    }

    public void setClientLoans(List<ClientLoan> clientLoans) {
        this.clientLoans = clientLoans;
    }

    // GENERACIÓN DE UN MÉTODO PARA AGREGARLE CLIENTES AL OBJETO PRESTAMO QUE ESTOY CREANDO
    // EL PRESTAMO QUE CREE, VA A LLAMAR AL MÉTODO ADDCLIENTWITHLOAN PARA AGREGAR ESE CLIENTE A LA LISTA DE CLIENTES
    public void addClientLoan(ClientLoan clientLoan){
        clientLoan.setLoan(this);
        this.clientLoans.add(clientLoan);
    }



    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxAmount=" + maxAmount +
                ", payments=" + payments +
                '}';
    }


}


// MÉTODO PARA DEVOLVER LA LISTA DE CLIENTES DE UN PRÉSTAMO
//    public List<Client> getClients() {
//        return clientLoans.stream().map(client -> client.getClientWithLoan()).collect(toList());
//    }
