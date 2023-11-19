package com.kosvad9.service;

import com.kosvad9.IntegrationTestBase;
import com.kosvad9.database.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class AccountServiceTest extends IntegrationTestBase {
    private final AccountService accountService;
    @Test
    @Disabled
    public void testGenerateIBAN(){
        //String ibanDigitsStr = "1134003110202400000000000000556747";
        String iban = "BY76VAKO00000000000000556747";
        //assertThat(iban).isEqualTo(accountService.generateIBAN());
    }
}
