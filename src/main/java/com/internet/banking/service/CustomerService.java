package com.internet.banking.service;

import com.internet.banking.database.dao.CustomerDAO;
import com.internet.banking.database.entity.Customer;
import com.internet.banking.exceptions.CustomerNotFoundException;
import com.internet.banking.service.vo.CustomerVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.internet.banking.service.mapper.CustomerVOMapper.toVO;
import static com.internet.banking.service.mapper.CustomerVOMapper.toEntity;

@Service
public class CustomerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private CustomerDAO customerDAO;

    public List<CustomerVO> findAll(final Integer pageNumber, final Integer size) {
        logger.info("[findAll] - Iniciando busca de clientes");

        final Integer alterSize = size == 0 ? 1 : size;

        return customerDAO.findAll(pageNumber, alterSize).stream().map( p ->
                toVO(p)
        ).collect(Collectors.toList());
    }

    public CustomerVO findById(final Long customerId) throws CustomerNotFoundException {
        logger.info("[findById] - Iniciando busca de clientes por id, customerId=:{}", customerId);

        final Optional<Customer> customer = customerDAO.findById(customerId);

        if (customer.isPresent()) {
            return toVO(customer.get());
        }

        throw new CustomerNotFoundException(
                "Cliente não encontrado, customerId=".concat(customerId.toString())
        );
    }

    public CustomerVO findByAccountNumberAndName(final String accountNumber, final String name) throws CustomerNotFoundException {
        logger.info("[findByAccountNumberAndName] - Iniciando busca de clientes por name e accountNumber, name =:{}, accountNumber =:{}", name, accountNumber);

        final Optional<Customer> customer = customerDAO.findByAccountNumberAndName(accountNumber, name);

        if (customer.isPresent()) {
            return toVO(customer.get());
        }

        throw new CustomerNotFoundException(
                "Cliente não encontrado, accountNumber=".concat(accountNumber)
                        .concat(", name=").concat(name)
        );
    }

    public CustomerVO create(final CustomerVO customerVO) {
        logger.info("[create] - Iniciando criacao do cliente, customer = :{}", customerVO);

        return toVO(
                customerDAO.create(
                        toEntity(customerVO)
                )
        );
    }

    public CustomerVO update(final CustomerVO customerVO) {
        logger.info("[update] - Iniciando update do cliente, customer = :{}", customerVO);

        return toVO(
                customerDAO.update(
                        toEntity(customerVO)
                )
        );
    }
}
