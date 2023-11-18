package com.kosvad9.dto;

import java.math.BigDecimal;

public record CreditProgramDto(Integer id,
                               String description,
                               Integer interestRate,
                               BigDecimal maxAmount,
                               Integer maxPeriodMonth) {
}
