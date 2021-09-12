package com.ums.service;

import com.ums.repository.AccountRepository;

public class AccountService {
    AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

}
