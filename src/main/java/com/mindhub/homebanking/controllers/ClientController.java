package com.mindhub.homebanking.controllers;


import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    // INYECCIÓN DE DEPENDENCIAS (ALGO DEL CONTEXTO DE SPRING)
    @Autowired
    private ClientRepository clientRepository;


    // SERVLET = RESPONDER UNA PETICIÓN ESPECÍFICA
//    @GetMapping("/")
//    public List<Client> getAllClients(){
//        return clientRepository.findAll();
//    }

//    @GetMapping("/")
//    public List<ClientDTO> getAllClients(){
//        return clientRepository.findAll().stream().map(client -> new ClientDTO(client)).collect(toList());
//      USANDO LAMBDA
//      return clientRepository.findAll().stream().map(ClientDTO::new).collect(toList());
//    }


//    @GetMapping("/{id}")
//    public ClientDTO getOneClientById(@PathVariable Long id){
//        Client client = clientRepository.findById(id).orElse(null);
//        return new ClientDTO(client);
//    }


    @GetMapping("/")
    public ResponseEntity<List<ClientDTO>> getAllClients(){
        List<Client> clients = clientRepository.findAll();
        return new ResponseEntity<>(clients.stream().map(client -> new ClientDTO(client)).collect(toList()), HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public  ResponseEntity<?> getOneClientById(@PathVariable Long id){
        Client client = clientRepository.findById(id).orElse(null);
        if(client == null){
            return new ResponseEntity<>("The user with Id: " + id + " is not found in the database.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ClientDTO(client), HttpStatus.OK);
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
