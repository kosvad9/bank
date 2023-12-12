package com.kosvad9.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreditDto(Long id,
                        BigDecimal amount,
                        BigDecimal debt,
                        LocalDate dateEnd,
                        Integer interestRate,
                        BigDecimal nextPaymentAmount) {
}
