package com.mindhub.homebanking.services.implementsService;

import com.mindhub.homebanking.dtos.CardDTO;
import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.services.CardService;
import com.mindhub.homebanking.utilServices.RandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceimplements implements CardService {


    @Autowired
    private CardRepository cardRepository;

    @Override
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public List<CardDTO> getAllCardsDTO() {
        return getAllCards().stream().map(CardDTO::new).toList();
    }

    @Override
    public List<Card> getAllCardsByClient(Client client) {
        return cardRepository.findCardByClient(client);
    }

    @Override
    public List<CardDTO> getAllCardsDTOByClient(Client client) {
        return getAllCardsByClient(client).stream().map(CardDTO::new).toList();
    }


    @Override
    public Boolean getCardByTypeAndColorAndClient(CardType type, CardColor color, Client client) {
        return cardRepository.existsCardByTypeAndColorAndClient(type, color, client);
    }

    @Override
    public Card getCardByNumber(String number) {
        return cardRepository.findByNumber(number);
    }

    @Override
    public String getCardCVV() {
        return String.format("%03d", RandomNumberGenerator.getRandomNumber(0, 1000));
    }

    @Override
    public String getCardNumber() {
        String cardNumber;
        cardNumber = String.format("%04d", RandomNumberGenerator.getRandomNumber(0, 10000)) + "-"
                + String.format("%04d", RandomNumberGenerator.getRandomNumber(0, 10000)) + "-"
                + String.format("%04d", RandomNumberGenerator.getRandomNumber(0, 10000)) + "-"
                + String.format("%04d", RandomNumberGenerator.getRandomNumber(0, 10000));
        return cardNumber;
    }

    @Override
    public void saveCard(Card card) {
        cardRepository.save(card);

    }






}
