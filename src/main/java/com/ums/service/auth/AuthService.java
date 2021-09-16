package com.ums.service.auth;

import com.ums.exceptions.AccountNotFountException;
import com.ums.model.entity.account.Account;
import com.ums.model.request.SignInRequest;
import com.ums.model.response.JWTTokenResponse;
import com.ums.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService {
    private AuthenticationManager authenticationManager;
    private AccountRepository accountRepository;
    private JWTService jwtService;

    public AuthService(AuthenticationManager authenticationManager, AccountRepository accountRepository, JWTService jwtService) {
        this.authenticationManager = authenticationManager;
        this.accountRepository = accountRepository;
        this.jwtService = jwtService;
    }

    public JWTTokenResponse authenticate(SignInRequest signInRequest) {
        String email = signInRequest.getEmail();
        String password = signInRequest.getPassword();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        Account account = accountRepository.findByEmail(email).orElseThrow(AccountNotFountException::new);
        String token = jwtService.generateJWT(email, account.getRole());
        log.info("Generating token for " + email);
        return new JWTTokenResponse(email, token);
    }
}
