package com.kosvad9.database.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BillingSystem {
    VISA('4'), MASTERCARD('5');

    private final char billingSystemDigit;

    public char getBillingSystemDigit() {
        return billingSystemDigit;
    }
}
