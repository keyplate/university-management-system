package com.ums.model.auth;

import com.ums.model.entity.account.Account;
import com.ums.model.entity.account.AccountStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.*;

public class AccountDetails implements UserDetails {
    private String userName;
    private String password;
    private List<GrantedAuthority> authorities;
    private AccountStatus accountStatus;

    public AccountDetails(String userName, String password, List<GrantedAuthority> authorities, AccountStatus accountStatus) {
        this.userName = userName;
        this.password = password;
        this.authorities = authorities;
        this.accountStatus = accountStatus;
    }

    public static UserDetails fromAccount(Account account) {
        List<GrantedAuthority> role = new ArrayList<>();
        role.add(new SimpleGrantedAuthority(account.getRole()));
        return new User(
                account.getEmail(),
                account.getPassword(),
                true,
                true,
                true,
                true,
                role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return accountStatus.equals(AccountStatus.ACTIVE);
    }
}
