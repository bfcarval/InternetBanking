package com.internet.banking.web.controller;

import com.internet.banking.exceptions.CustomerBadRequestException;
import com.internet.banking.exceptions.CustomerNotFoundException;
import com.internet.banking.exceptions.TransactionBadRequestException;
import com.internet.banking.service.TransactionService;
import com.internet.banking.web.dto.request.TransactionRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.internet.banking.util.Validator.validateTransactionRequestDTO;
import static com.internet.banking.web.mapper.TransactionDTOMapper.toDTO;
import static com.internet.banking.web.mapper.TransactionDTOMapper.toVO;

@RestController
@RequestMapping("/transaction")
@Api(
        description = "API Gerenciamento de Transações",
        tags = ("Internet Banking - Gerenciar Transações")
)
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/withdraw")
    @ApiOperation("Withdraw balance")
    public ResponseEntity makeWithdraw(@RequestBody final TransactionRequestDTO transactionRequestDTO) throws Exception {
        validateTransactionRequestDTO(transactionRequestDTO);

        return new ResponseEntity(
                toDTO(
                        transactionService.makeWithdraw(toVO(transactionRequestDTO))
                )
                , HttpStatus.OK
        );
    }

    @PostMapping("/deposit")
    @ApiOperation("Deposit balance")
    public ResponseEntity makeDeposit(@RequestBody final TransactionRequestDTO transactionRequestDTO) throws CustomerBadRequestException, TransactionBadRequestException, CustomerNotFoundException {
        validateTransactionRequestDTO(transactionRequestDTO);

        return new ResponseEntity(
                toDTO(
                        transactionService.makeDeposit(toVO(transactionRequestDTO))
                )
                , HttpStatus.OK
        );
    }

    @GetMapping("/find")
    @ApiOperation("Find transactions")
    public ResponseEntity findAll(
            @RequestParam(required = false) final String date,
            @RequestParam(required = false, name = "transaction_type") final String transactionType,
            @RequestParam(required = false, name = "page_number", defaultValue = "0") final Integer pageNumber,
            @RequestParam(required = false, defaultValue = "1") final Integer size) {
        return new ResponseEntity(
                transactionService.findAll(date, transactionType, pageNumber, size).stream().map( p ->
                        toDTO(p)
                )
                , HttpStatus.OK
        );
    }
}
