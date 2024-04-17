package com.mindhub.homebanking.services;

import com.mindhub.homebanking.dtos.CardDTO;
import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;

import java.util.List;

public interface CardService {

    List<Card> getAllCards();

    List<CardDTO> getAllCardsDTO();

    List<Card> getAllCardsByClient(Client client);

    List<CardDTO> getAllCardsDTOByClient(Client client);

    Boolean getCardByTypeAndColorAndClient(CardType type, CardColor color, Client client);

    Card getCardByNumber(String number);

    String getCardCVV();

    String getCardNumber();

    void saveCard(Card card);



}
