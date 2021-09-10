package com.ums.repository;

import com.ums.model.account.Account;
import com.ums.model.account.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface AccountRepository<T extends Account> extends JpaRepository<T, Integer> {

    Optional<T> findByEmail(String email);

    Boolean existsByEmail(String email);

    void deleteAccountByEmail(String email);

    @Modifying
    @Query("update Account a set a.status = :status where a.email = :email")
    void changeStatusByEmail(String email, AccountStatus status);

}