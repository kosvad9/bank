package com.kosvad9.service;

import com.kosvad9.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class CardServiceTest extends IntegrationTestBase {
    private final CardService cardService;

    @Test
    @Disabled
    void testGenerateCardNumber(){
//        String cardNumber = cardService.generateCardNumber('5');
//        System.out.println(cardNumber);
    }

    @Test
    @Disabled
    void isValidGenerateChecksum(){
        //char checkSum = cardService.generateChecksum("456126121234546");
        //assertThat(checkSum).isEqualTo('7');
    }
}
