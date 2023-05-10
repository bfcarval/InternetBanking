package com.internet.banking.database.repository;

import com.internet.banking.database.entity.Transaction;
import com.internet.banking.database.entity.enums.TransactionType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findTransactionByTransactionDateAndTransactionType(
            final LocalDate transactionDate,
            final TransactionType transactionType,
            final Pageable pageable
    );
}
