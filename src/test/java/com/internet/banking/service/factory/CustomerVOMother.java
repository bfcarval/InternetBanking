package com.internet.banking.service.factory;

import com.internet.banking.database.entity.Customer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.internet.banking.util.DateUtil.toLocalDate;

public class CustomerVOMother {

    public static final List<Customer> buildCustomers() {
        return List.of(
                Customer.builder()
                        .name("Osvaldo Cruz")
                        .updatedDate(LocalDateTime.now())
                        .createdDate(LocalDateTime.now())
                        .bornDate(toLocalDate("1990-05-05"))
                        .accountNumber("99999")
                        .amount(BigDecimal.valueOf(1000))
                        .exclusivePlan(false)
                        .build(),
                Customer.builder()
                        .name("Osvaldo Orlando")
                        .updatedDate(LocalDateTime.now())
                        .createdDate(LocalDateTime.now())
                        .bornDate(toLocalDate("1990-05-05"))
                        .accountNumber("10254")
                        .amount(BigDecimal.valueOf(1000))
                        .exclusivePlan(true)
                        .build()
        );
    }
}
