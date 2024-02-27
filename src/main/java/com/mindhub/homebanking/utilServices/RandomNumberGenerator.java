package com.mindhub.homebanking.utilServices;

import org.springframework.stereotype.Component;

@Component
public class RandomNumberGenerator {

    public int getRandomNumber(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }



}
