package com.kosvad9.dto;

import java.math.BigDecimal;
import java.util.List;

public record AccountDto(Long id,
                         String iban,
                         BigDecimal amount,
                         String currency,
                         String status,
                         List<CardDto> cards) {
}
