package com.internet.banking.database.dao;

import com.internet.banking.database.entity.Transaction;
import com.internet.banking.database.entity.enums.TransactionType;
import com.internet.banking.database.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionDAO {

    @Autowired
    private TransactionRepository transactionRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public Transaction create(final Transaction transaction) {
        try {
            return transactionRepository.save(transaction);
        } catch (Exception ex) {
            logger.error("[create] - Erro nao mapeado ao persitir a transacao", ex);
            throw ex;
        }
    }

    public List<Transaction> findAll(final LocalDate date, final TransactionType transactionType, final Integer pageNumber, final Integer size) {
        try {
            return transactionRepository.findTransactionByTransactionDateAndTransactionType(
                    date,
                    transactionType,
                    PageRequest.of(pageNumber, size)
            );
        } catch (Exception ex) {
            logger.error("[findAll] - Erro nao mapeado ao buscar as transacoes", ex);
            throw ex;
        }
    }
}
