package com.internet.banking.database.dao;

import com.internet.banking.database.entity.Customer;
import com.internet.banking.database.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerDAO {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAll(final Integer pageNumber, final Integer size) {
        try {
            return customerRepository.findAllCustomers(PageRequest.of(pageNumber, size));
        } catch (Exception ex) {
            logger.error("[findAll] - Erro nao mapeado ao buscar os clientes", ex);
            throw ex;
        }
    }

    public Optional<Customer> findById(final Long customerId) {
        try {
            return customerRepository.findById(customerId);
        } catch (Exception ex) {
            logger.error("[findById] - Erro nao mapeado ao buscar os clientes", ex);
            throw ex;
        }
    }

    public Optional<Customer> findByAccountNumberAndName(final String accountNumber, final String name) {
        try {
            return customerRepository.findByAccountNumberAndName(accountNumber, name);
        } catch (Exception ex) {
            logger.error("[findByAccountNumberAndName] - Erro nao mapeado ao buscar os clientes", ex);
            throw ex;
        }
    }

    public Customer create(final Customer customer) {
        try {
            final Optional<Customer> customerSearched = findByAccountNumberAndName(customer.getAccountNumber(), customer.getName());

            if (customerSearched.isPresent())
                return customerSearched.get();

            return customerRepository.save(customer);
        } catch (Exception ex) {
            logger.error("[create] - Erro nao mapeado ao persitir o cliente", ex);
            throw ex;
        }
    }

    public Customer update(final Customer customer) {
        try {
            return customerRepository.save(customer);
        } catch (Exception ex) {
            logger.error("[update] - Erro nao mapeado ao atualizar o cliente", ex);
            throw ex;
        }
    }
}
