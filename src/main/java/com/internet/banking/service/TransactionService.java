package com.internet.banking.service;

import com.internet.banking.database.dao.TransactionDAO;
import com.internet.banking.database.entity.enums.TransactionType;
import com.internet.banking.exceptions.CustomerNotFoundException;
import com.internet.banking.service.vo.CustomerVO;
import com.internet.banking.service.vo.TransactionRequestVO;
import com.internet.banking.service.vo.TransactionVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static com.internet.banking.service.mapper.TransactionVOMapper.toEntity;
import static com.internet.banking.service.mapper.TransactionVOMapper.toVO;
import static com.internet.banking.util.DateUtil.toLocalDate;
import static com.internet.banking.util.MoneyUtil.calcAmount;
import static com.internet.banking.util.MoneyUtil.subAmount;

@Service
public class TransactionService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @Autowired
    private CustomerService customerService;

    @Autowired
    private TransactionDAO transactionDAO;

    public TransactionVO makeWithdraw(final TransactionRequestVO transactionRequestVO) throws Exception {

        logger.info("[makeWithdraw] - Iniciando processamento de saque, transactionRequest = :{}", transactionRequestVO);

        try {
            final CustomerVO customer = customerService.findByAccountNumberAndName(transactionRequestVO.getAccountNumber(), transactionRequestVO.getName());
            final BigDecimal amountWithTax = calcAmount(customer, transactionRequestVO);
            final BigDecimal newAmount = subAmount(customer.getAmount(), amountWithTax);

            customer.setAmount(newAmount);

            final TransactionVO transactionVO = toVO(
                    transactionDAO.create(toEntity(transactionRequestVO, amountWithTax)
                    )
            );

            customerService.update(customer);

            return transactionVO;
        } catch (Exception ex) {
            logger.error("[makeWithdraw] - Problema nao esperado no processamento do saque", ex);
            throw ex;
        }
    }

    public TransactionVO makeDeposit(final TransactionRequestVO transactionRequestVO) throws CustomerNotFoundException {

        logger.info("[makeDeposit] - Iniciando processamento de deposito, transactionRequest = :{}", transactionRequestVO);

        try {
            final CustomerVO customer = customerService.findByAccountNumberAndName(transactionRequestVO.getAccountNumber(), transactionRequestVO.getName());
            final BigDecimal newAmount = customer.getAmount().add(transactionRequestVO.getAmount());

            customer.setAmount(newAmount);

            final TransactionVO transactionVO = toVO(
                    transactionDAO.create(toEntity(transactionRequestVO, null))
            );

            customerService.update(customer);

            return transactionVO;
        } catch (Exception ex) {
            logger.error("[makeDeposit] - Problema nao esperado no processamento do deposito", ex);
            throw ex;
        }
    }

    public List<TransactionVO> findAll(final String date, final String transactionType, final Integer pageNumber, final Integer size) {
        logger.info("[findAll] - Buscando todas as transacoes por data e transactionType, date = :{}, transactionType = :{}", date, transactionType);

        final Integer alterSize = size == 0 ? 1 : size;

        return transactionDAO.findAll(
                        toLocalDate(date),
                        TransactionType.valueOf(transactionType),
                        pageNumber,
                        alterSize)
                .stream().map(p ->
                        toVO(p)
                ).collect(Collectors.toList());
    }
}
