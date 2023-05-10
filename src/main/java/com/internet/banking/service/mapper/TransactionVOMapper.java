package com.internet.banking.service.mapper;

import com.internet.banking.database.entity.Transaction;
import com.internet.banking.service.vo.TransactionRequestVO;
import com.internet.banking.service.vo.TransactionVO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionVOMapper {

    public static Transaction toEntity(final TransactionRequestVO transactionRequestVO, final BigDecimal alterAmount) {
        final BigDecimal checkedAmount = alterAmount != null? alterAmount : transactionRequestVO.getAmount();

        return Transaction.builder()
                .amount(checkedAmount)
                .name(transactionRequestVO.getName())
                .accountNumber(transactionRequestVO.getAccountNumber())
                .transactionType(transactionRequestVO.getTransactionType())
                .transactionDate(LocalDate.now())
                .build();
    }

    public static TransactionVO toVO(final Transaction transaction) {
        return TransactionVO.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .name(transaction.getName())
                .transactionType(transaction.getTransactionType())
                .accountNumber(transaction.getAccountNumber())
                .transactionDate(transaction.getTransactionDate())
                .createdDate(transaction.getCreatedDate())
                .updatedDate(transaction.getUpdatedDate())
                .build();
    }
}
