package com.ums.service.auth;

import com.ums.model.auth.AccountDetails;
import com.ums.model.entity.account.Account;
import com.ums.repository.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;


@Service
public class AccountDetailsService implements UserDetailsService {

    private AccountRepository accountRepository;


    public AccountDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
        return AccountDetails.fromAccount(account);
    }
}
