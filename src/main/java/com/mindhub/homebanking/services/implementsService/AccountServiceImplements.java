package com.mindhub.homebanking.services.implementsService;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImplements implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public List<AccountDTO> getAllAccountsDTO() {
        return getAllAccounts().stream().map(AccountDTO::new).toList();
    }

    @Override
    public List<Account> getAllAccountsByAccountHolder(Client accountHolder) {
        return accountRepository.findAccountByAccountHolder(accountHolder);
    }

    @Override
    public List<AccountDTO> getAllAccountsDTOByAccountHolder(Client accountHolder) {
        return getAllAccountsByAccountHolder(accountHolder).stream().map(AccountDTO::new).toList();
    }

    @Override
    public Boolean getAccountByNumberAndAccountHolder(String number, Client accountHolder) {
        return accountRepository.existsByNumberAndAccountHolder(number, accountHolder);
    }

    @Override
    public Account getAccountByNumber(String number) {
        return accountRepository.findByNumber(number);
    }

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(account);

    }
}
