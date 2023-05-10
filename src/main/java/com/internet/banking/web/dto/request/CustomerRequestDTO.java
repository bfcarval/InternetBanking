package com.internet.banking.web.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerRequestDTO {

    private String name;

    @JsonProperty("exclusive_plan")
    private boolean exclusivePlan;

    private BigDecimal amount;

    @JsonProperty("account_number")
    private String accountNumber;

    @JsonProperty("born_date")
    private String bornDate;
}
