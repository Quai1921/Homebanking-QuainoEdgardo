package com.mindhub.homebanking.controllers;


import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    // INYECCIÓN DE DEPENDENCIAS (ALGO DEL CONTEXTO DE SPRING)
    @Autowired
    private ClientRepository clientRepository;


    // SERVLET = RESPONDER UNA PETICIÓN ESPECÍFICA
    @GetMapping("/")
    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Client getOneClientById(@PathVariable Long id){
        return clientRepository.findById(id).orElse(null);
    }

    @GetMapping("/hello")
    public String getClients(){
        return "Hello Clients!";
    }

//    MÉTODOS COMUNES DE ACCESO
//    findAll: Returns all records of the entity.
//    findById: Search for a record by its identifier.
//    save: Saves an entity, either creating a new one or updating an existing one.
//    deleteById: Delete a record by its identifier.
//    count: Returns the total number of records.
//    existsById: Checks if a record exists with the given identifier






}
