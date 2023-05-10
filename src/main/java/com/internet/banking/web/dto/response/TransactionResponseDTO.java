package com.internet.banking.web.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionResponseDTO {
    private BigDecimal amount;
    private String name;

    @JsonProperty("account_number")
    private String accountNumber;

    @JsonProperty("transaction_type")
    private String transactionType;

    @JsonProperty("transaction_date")
    private String transactionDate;
}