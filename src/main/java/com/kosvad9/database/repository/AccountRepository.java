package com.kosvad9.database.repository;

import com.kosvad9.database.entity.Account;
import com.kosvad9.service.AccountService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query(nativeQuery = true,
           value = "SELECT nextval('account_number_seq')")
    BigInteger getNewAccountNumber();
}
