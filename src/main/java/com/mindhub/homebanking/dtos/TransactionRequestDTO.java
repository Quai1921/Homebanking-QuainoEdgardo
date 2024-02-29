package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.TransactionType;

import java.time.LocalDateTime;

public record TransactionRequestDTO (double amount, String description, String numberDebit, String numberCredit) {
}
