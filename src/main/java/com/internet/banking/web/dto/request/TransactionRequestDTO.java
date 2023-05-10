package com.internet.banking.web.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.internet.banking.database.entity.enums.TransactionType;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Builder
public class TransactionRequestDTO {

    private BigDecimal amount;

    @JsonProperty("transaction_type")
    private TransactionType transactionType;

    @JsonProperty("account_number")
    private String accountNumber;

    private String name;
}
