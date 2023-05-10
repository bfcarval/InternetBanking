package com.internet.banking.service.vo;

import com.internet.banking.database.entity.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class TransactionRequestVO {
    private BigDecimal amount;
    private TransactionType transactionType;
    private String accountNumber;
    private String name;
}
