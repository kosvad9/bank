package com.kosvad9.database.repository;

import com.kosvad9.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class AccountRepositoryTest extends IntegrationTestBase {
    private final AccountRepository accountRepository;

    @Test
    public void getNewAccountNumberFromSequence(){
        BigInteger number = accountRepository.getNewAccountNumber();
        assertThat(number).isEqualTo(556747);
        System.out.println(number);
    }
}
