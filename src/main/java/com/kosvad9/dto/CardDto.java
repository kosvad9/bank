package com.kosvad9.dto;

import com.kosvad9.database.enums.BillingSystem;
import com.kosvad9.database.enums.StatusCard;

import java.time.LocalDate;

public record CardDto(Long id,
                      String billingSystem,
                      String number,
                      LocalDate expDate,
                      String cvv,
                      String status) {
}
