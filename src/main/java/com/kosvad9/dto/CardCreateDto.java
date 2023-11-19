package com.kosvad9.dto;

import com.kosvad9.database.enums.BillingSystem;
import com.kosvad9.database.enums.StatusCard;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.LocalDate;

public record CardCreateDto(BillingSystem billingSystem,
                            Integer periodYears) {
}
