package com.kosvad9.dto;

import com.kosvad9.database.enums.StatusApplication;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ApplicationDto(Long id,
                             LocalDate date,
                             BigDecimal amount,
                             Integer periodMonth,
                             String description,
                             StatusApplication statusApplication,
                             ClientDto clientDto,
                             CreditProgramDto creditProgramDto) {
}
