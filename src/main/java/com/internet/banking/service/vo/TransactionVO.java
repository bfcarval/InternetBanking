package com.internet.banking.service.vo;

import com.internet.banking.database.entity.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class TransactionVO {
    private Long id;
    private BigDecimal amount;
    private String name;
    private TransactionType transactionType;
    private String accountNumber;
    private LocalDate transactionDate;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
