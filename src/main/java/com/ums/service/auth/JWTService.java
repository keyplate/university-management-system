package com.ums.service.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
@Slf4j
public class JWTService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiresIn;

    private Algorithm algorithm;

    private AccountDetailsService accountDetailsService;

    public JWTService(AccountDetailsService accountDetailsService) {
        this.accountDetailsService = accountDetailsService;
    }

    @PostConstruct
    public void init() {
        algorithm = Algorithm.HMAC256(secret.getBytes());
    }

    public String generateJWT(String username, String role) {
        Date now = new Date();
        Date expiresAt = new Date(now.getTime() + expiresIn);
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(now)
                .withExpiresAt(expiresAt)
                .withClaim("role", role)
                .sign(algorithm);
    }

    public boolean isValid(String token) {
        token = token.replace("Bearer", "").strip();
        return JWT.require(algorithm).build().verify(token).getExpiresAt().after(new Date());
    }

    public Authentication getAuthentication(String token) {
        token = token.replace("Bearer", "").strip();
        String email = JWT.require(algorithm).build().verify(token).getSubject();
        log.debug(email + "trying to authenticate");
        UserDetails accountDetails = accountDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(accountDetails.getUsername(), null, accountDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest req) {
        return req.getHeader(HttpHeaders.AUTHORIZATION);
    }
}
