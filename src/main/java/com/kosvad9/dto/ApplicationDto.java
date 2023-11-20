package com.kosvad9.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ApplicationDto(Long id,
                             LocalDate date,
                             BigDecimal amount,
                             Integer periodMonth,
                             ClientDto clientDto,
                             CreditProgramDto creditProgramDto) {
}
