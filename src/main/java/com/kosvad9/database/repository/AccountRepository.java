package com.kosvad9.database.repository;

import com.kosvad9.database.entity.Account;
import com.kosvad9.service.AccountService;
import jakarta.persistence.LockModeType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query(nativeQuery = true,
           value = "SELECT nextval('account_number_seq')")
    BigInteger getNewAccountNumber();

    Optional<Account> findAccountByIban(String iban);

    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Query("update Account a set a.amount = a.amount - :sum where a.id = :accountId")
    void subtractMoney(Long accountId, BigDecimal sum);

    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Query("update Account a set a.amount = a.amount + :sum where a.id = :accountId")
    void addMoney(Long accountId, BigDecimal sum);
}
