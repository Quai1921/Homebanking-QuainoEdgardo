package com.mindhub.homebanking.controllers;


import com.mindhub.homebanking.dtos.CardDTO;
import com.mindhub.homebanking.dtos.CardRequestDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.utilServices.RandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/clients/current")
@CrossOrigin(origins = "*")
public class CardController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CardRepository cardRepository;


    @Autowired
    private RandomNumberGenerator randomNumber;


    @GetMapping("/cards")
    public ResponseEntity<?> getAccounts() {
        String userMail = SecurityContextHolder.getContext().getAuthentication().getName();
        Client client = clientRepository.findByEmail(userMail);

        List<Card> cards = client.getCards().stream().toList();

        return ResponseEntity.ok(cards.stream().map(CardDTO::new).toList());
    }

    @PostMapping("/cards")
    public ResponseEntity<?> createCard (@RequestBody CardRequestDTO cardRequestDTO){
        String userMail = SecurityContextHolder.getContext().getAuthentication().getName();
        Client client = clientRepository.findByEmail(userMail);



        if(cardRequestDTO.type().isBlank()){
            return new ResponseEntity<>("The type field cannot be empty", HttpStatus.FORBIDDEN);

        }

        if(cardRequestDTO.color().isBlank()){
            return new ResponseEntity<>("The color field cannot be empty", HttpStatus.FORBIDDEN);
        }


//        OTRA MANERA DE COMPROBAR SI EXISTEN LAS TARJETAS
//        List<Card> cards = client.getCards();
//        List <Boolean> cardsTypeColor = cards.stream().map(card -> card.getType() == CardType.valueOf(cardRequestDTO.type()) && card.getColor()== CardColor.valueOf(cardRequestDTO.color())).toList();
//        if(cardsTypeColor.contains(true)){
//            return new ResponseEntity<>("You already have one card with type " + cardRequestDTO.type() + " and color " + cardRequestDTO.color(), HttpStatus.FORBIDDEN);
//        }

        Boolean cardExist = cardRepository.existsCardByTypeAndColorAndClient(CardType.valueOf(cardRequestDTO.type()), CardColor.valueOf(cardRequestDTO.color()), client);

        if(cardExist){
            return new ResponseEntity<>("You already have one card with type " + cardRequestDTO.type() + " and color " + cardRequestDTO.color(), HttpStatus.FORBIDDEN);
        }


        String cardNumber;
        do {
            cardNumber = String.format("%04d", randomNumber.getRandomNumber(0, 1001)) + " - "
                       + String.format("%04d", randomNumber.getRandomNumber(0, 1001)) + " - "
                       + String.format("%04d", randomNumber.getRandomNumber(0, 1001)) + " - "
                       + String.format("%04d", randomNumber.getRandomNumber(0, 1001));
        } while (cardRepository.findByNumber(cardNumber) != null);

        String cvv = String.format("%03d", randomNumber.getRandomNumber(0, 1000));



        Card card = new Card(CardType.valueOf(cardRequestDTO.type()), CardColor.valueOf(cardRequestDTO.color()), cardNumber, cvv, LocalDate.now());
        client.addCardClient(card);
        clientRepository.save(client);
        cardRepository.save(card);

        return new ResponseEntity<>("Card created", HttpStatus.CREATED);
    }



}
