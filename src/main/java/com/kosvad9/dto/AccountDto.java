package com.kosvad9.dto;

import java.math.BigDecimal;

public record AccountDto(Long id,
                         String iban,
                         BigDecimal amount,
                         String currency,
                         String status) {
}
