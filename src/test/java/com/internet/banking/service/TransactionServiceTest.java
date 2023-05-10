package com.internet.banking.service;

import com.internet.banking.boot.Boot;
import com.internet.banking.database.dao.CustomerDAO;
import com.internet.banking.database.dao.TransactionDAO;
import com.internet.banking.database.entity.Customer;
import com.internet.banking.database.entity.Transaction;
import com.internet.banking.database.entity.enums.TransactionType;
import com.internet.banking.database.repository.CustomerRepository;
import com.internet.banking.database.repository.TransactionRepository;
import com.internet.banking.exceptions.CustomerBadRequestException;
import com.internet.banking.service.vo.TransactionRequestVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

import static com.internet.banking.service.factory.CustomerVOMother.buildCustomers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = Boot.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @InjectMocks
    private CustomerService customerService;

    @InjectMocks
    private CustomerDAO customerDAO;

    @Resource
    private CustomerRepository customerRepository;

    @InjectMocks
    private TransactionDAO transactionDAO;

    @Resource
    private TransactionRepository transactionRepository;

    @Test
    public void shouldMakeWithdrawWithTaxZero() throws Exception {
        final String accountNumber = "99999";
        final String name = "Osvaldo Cruz";

        transactionService.makeWithdraw(
                TransactionRequestVO.builder()
                        .amount(BigDecimal.TEN)
                        .transactionType(TransactionType.WITHDRAW)
                        .accountNumber(accountNumber)
                        .name(name)
                        .build()
        );

        final Transaction transaction = transactionRepository.findAll().get(0);
        final Customer customer = customerRepository.findAll().get(0);

        assertTrue(customer.getAmount().compareTo(BigDecimal.valueOf(990)) == 0);
        assertTrue(customer.getAmount().add(BigDecimal.TEN).compareTo(BigDecimal.valueOf(1000)) == 0);
        assertEquals(transaction.getTransactionType().name(), TransactionType.WITHDRAW.name());

        customerRepository.deleteAll();
    }

    @Test
    public void shouldMakeWithdrawWithMinTax() throws Exception {
        final String accountNumber = "99999";
        final String name = "Osvaldo Cruz";

        transactionService.makeWithdraw(
                TransactionRequestVO.builder()
                        .amount(BigDecimal.valueOf(101.00))
                        .transactionType(TransactionType.WITHDRAW)
                        .accountNumber(accountNumber)
                        .name(name)
                        .build()
        );

        final Transaction transaction = transactionRepository.findAll().get(0);
        final Customer customer = customerRepository.findAll().get(0);

        assertTrue(customer.getAmount().compareTo(BigDecimal.valueOf(898.60)) == 0);
        assertTrue(customer.getAmount().add(BigDecimal.valueOf(101.40)).compareTo(BigDecimal.valueOf(1000)) == 0);
        assertEquals(transaction.getTransactionType().name(), TransactionType.WITHDRAW.name());
    }

    @Test
    public void shouldMakeWithdrawWithMaxTax() throws Exception {
        final String accountNumber = "99999";
        final String name = "Osvaldo Cruz";

        transactionService.makeWithdraw(
                TransactionRequestVO.builder()
                        .amount(BigDecimal.valueOf(900.00))
                        .transactionType(TransactionType.WITHDRAW)
                        .accountNumber(accountNumber)
                        .name(name)
                        .build()
        );

        final Transaction transaction = transactionRepository.findAll().get(0);
        final Customer customer = customerRepository.findAll().get(0);

        assertTrue(customer.getAmount().compareTo(BigDecimal.valueOf(91.00)) == 0);
        assertTrue(customer.getAmount().add(BigDecimal.valueOf(909.00)).compareTo(BigDecimal.valueOf(1000)) == 0);
        assertEquals(transaction.getTransactionType().name(), TransactionType.WITHDRAW.name());
    }

    @Test
    public void shouldMakeWithdrawWithExclusivePlan() throws Exception {
        final String accountNumber = "10254";
        final String name = "Osvaldo Orlando";

        transactionService.makeWithdraw(
                TransactionRequestVO.builder()
                        .amount(BigDecimal.valueOf(900.00))
                        .transactionType(TransactionType.WITHDRAW)
                        .accountNumber(accountNumber)
                        .name(name)
                        .build()
        );

        final Transaction transaction = transactionRepository.findAll().get(0);
        final Customer customer = customerRepository.findAll().get(1);

        assertTrue(customer.getAmount().compareTo(BigDecimal.valueOf(100.00)) == 0);
        assertTrue(customer.getAmount().add(BigDecimal.valueOf(900.00)).compareTo(BigDecimal.valueOf(1000)) == 0);
        assertEquals(transaction.getTransactionType().name(), TransactionType.WITHDRAW.name());
    }

    @Test
    public void shouldThrowExceptionErrorAmountInWithdraw() throws Exception {
        final String accountNumber = "10254";
        final String name = "Osvaldo Orlando";

        final Exception exception = assertThrows(CustomerBadRequestException.class, () ->
                transactionService.makeWithdraw(
                        TransactionRequestVO.builder()
                                .amount(BigDecimal.valueOf(1100.00))
                                .transactionType(TransactionType.WITHDRAW)
                                .accountNumber(accountNumber)
                                .name(name)
                                .build()
                ));

        assertEquals(exception.getMessage(), "Cliente não tem saldo suficiente para operação, saldo=R$ 1000.00, valor de retirada= R$1100.00");
    }

    @BeforeEach
    public void initVariables() {
        ReflectionTestUtils.setField(transactionService, "transactionDAO", transactionDAO);
        ReflectionTestUtils.setField(transactionService, "customerService", customerService);

        ReflectionTestUtils.setField(transactionDAO, "transactionRepository", transactionRepository);

        ReflectionTestUtils.setField(customerService, "customerDAO", customerDAO);
        ReflectionTestUtils.setField(customerDAO, "customerRepository", customerRepository);

        final List<Customer> customersToPersist = buildCustomers();

        transactionRepository.deleteAll();
        customerRepository.deleteAll();
        customerRepository.saveAll(customersToPersist);
    }
}
