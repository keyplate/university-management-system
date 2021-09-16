package com.ums.security.filter;

import com.ums.service.auth.JWTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.security.sasl.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Slf4j
@Component
public class AuthFilter extends GenericFilterBean {

    private JWTService JWTService;

    public AuthFilter(JWTService JWTService) {
        this.JWTService = JWTService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = JWTService.resolveToken((HttpServletRequest) servletRequest);
        try {
            if ((token != null) && JWTService.isValid(token)) {
                Authentication auth = JWTService.getAuthentication(token);
                if (auth != null) {
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            log.warn("Invalid jwt token!");
            throw new AuthenticationException("Invalid jwt token");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
