package com.mindhub.homebanking.controllers;


import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "*")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // INYECCIÓN DE DEPENDENCIAS (ALGO DEL CONTEXTO DE SPRING)
//    @Autowired
//    private ClientRepository clientRepository;


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
        return new ResponseEntity<>(clientService.getAllClientsDTO(), HttpStatus.OK);

//        List<Client> clients = clientRepository.findAll();
//        return new ResponseEntity<>(clients.stream().map(client -> new ClientDTO(client)).collect(toList()), HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public  ResponseEntity<?> getOneClientById(@PathVariable Long id){
        Client client = clientService.getClientById(id);
        if(client == null){
            return new ResponseEntity<>("The user with Id: " + id + " is not found in the database.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }


    @GetMapping("/hello")
    public String getClients(){
        return "Hello Clients!";
    }



    @GetMapping("/current")
    public ResponseEntity<?> getClient(){
        String userMail = SecurityContextHolder.getContext().getAuthentication().getName();
        Client client = clientService.getClientByEmail(userMail);
        return ResponseEntity.ok(new ClientDTO(client));
    }









//    MÉTODOS COMUNES DE ACCESO
//    findAll: Returns all records of the entity.
//    findById: Search for a record by its identifier.
//    save: Saves an entity, either creating a new one or updating an existing one.
//    deleteById: Delete a record by its identifier.
//    count: Returns the total number of records.
//    existsById: Checks if a record exists with the given identifier






}
