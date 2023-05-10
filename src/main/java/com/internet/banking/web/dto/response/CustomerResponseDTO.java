package com.internet.banking.web.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponseDTO {
    private Long id;
    private String name;

    @JsonProperty("exclusive_plan")
    private Boolean exclusivePlan;

    private BigDecimal amount;

    @JsonProperty("account_number")
    private String accountNumber;

    @JsonProperty("born_date")
    private String bornDate;

    private String createdDate;
    private String updatedDate;
}
