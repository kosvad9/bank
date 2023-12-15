package com.kosvad9.dto;

import com.kosvad9.database.entity.CreditProgram;
import com.kosvad9.dto.ClientDto;
import com.kosvad9.dto.CreditProgramDto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ApplicationCreateDto(Integer creditProgramId,
                                   BigDecimal amount,
                                   Integer periodMonth) {
}
