package com.internet.banking.web.controller;

import com.internet.banking.exceptions.CustomerBadRequestException;
import com.internet.banking.exceptions.CustomerNotFoundException;
import com.internet.banking.exceptions.TransactionBadRequestException;
import com.internet.banking.service.CustomerService;
import com.internet.banking.web.dto.request.CustomerRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.internet.banking.util.Validator.validateCustomerRequestDTO;
import static com.internet.banking.web.mapper.CustomerDTOMapper.toDTO;
import static com.internet.banking.web.mapper.CustomerDTOMapper.toVO;

@RestController
@RequestMapping("/customer")
@Api(
        description = "API Gerenciamento de Clientes",
        tags = ("Internet Banking - Gerenciar Clientes")
)
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/find")
    @ApiOperation("Find All Customers")
    public ResponseEntity findAll(
            @RequestParam(required = false, name = "page_number", defaultValue = "0") final Integer pageNumber,
            @RequestParam(required = false, defaultValue = "1") final Integer size
    ) {
        return new ResponseEntity(customerService.findAll(pageNumber, size), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    @ApiOperation("Find Customer By Id")
    public ResponseEntity findById(@Validated @PathVariable("id") final Long customerId) throws CustomerNotFoundException {
        return new ResponseEntity(customerService.findById(customerId), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Create Customer")
    public ResponseEntity create(@Validated @RequestBody final CustomerRequestDTO customerRequestDTO) throws CustomerBadRequestException, TransactionBadRequestException {
        validateCustomerRequestDTO(customerRequestDTO);

        return new ResponseEntity(
                toDTO(customerService.create(
                        toVO(customerRequestDTO))
                ), HttpStatus.CREATED
        );
    }
}
