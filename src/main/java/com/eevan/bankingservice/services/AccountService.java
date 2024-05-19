package com.eevan.bankingservice.services;

import com.eevan.bankingservice.repositories.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountsRepository accountsRepository;

    @Autowired
    public AccountService(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }
}
