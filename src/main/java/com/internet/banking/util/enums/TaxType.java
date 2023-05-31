package com.internet.banking.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public enum TaxType {

    ZERO(BigDecimal.ZERO),
    MIN(BigDecimal.valueOf(0.4)),
    MAX(BigDecimal.ONE);

    private BigDecimal taxPercentage;

    public static TaxType defineTax(final BigDecimal amount) {
        if (amount.compareTo(BigDecimal.valueOf(100.00)) != 1)
            return ZERO;

        return amount.compareTo(BigDecimal.valueOf(300.00)) == 1 ? MAX : MIN;
    }
}
