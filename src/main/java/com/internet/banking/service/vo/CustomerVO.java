package com.internet.banking.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomerVO {
    private Long id;
    private String name;
    private boolean exclusivePlan;
    private BigDecimal amount;
    private String accountNumber;
    private LocalDate bornDate;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Boolean hasEnoughAmount(final BigDecimal subAmount) {
        return amount.compareTo(subAmount) == -1;
    }
}
