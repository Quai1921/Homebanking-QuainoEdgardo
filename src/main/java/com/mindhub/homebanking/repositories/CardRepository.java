package com.mindhub.homebanking.repositories;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Card findByNumber(String number);


    Boolean existsCardByTypeAndColorAndClient(CardType type, CardColor color, Client client);

    List<Card> findCardByClient(Client client);

    Boolean existsCardByCardholder(String cardholder);



}
