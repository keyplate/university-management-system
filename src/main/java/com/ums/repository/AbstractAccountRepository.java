package com.ums.repository;

import com.ums.model.entity.account.Account;
import com.ums.model.entity.account.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import java.util.Optional;

@NoRepositoryBean
public interface AbstractAccountRepository<T extends Account> extends JpaRepository<T, Integer> {

    Optional<T> findByEmail(String email);

    Boolean existsByEmail(String email);

    void deleteAccountByEmail(String email);

    @Modifying
    @Query("update Account a set a.status = :status where a.email = :email")
    void changeStatusByEmail(String email, AccountStatus status);

}
