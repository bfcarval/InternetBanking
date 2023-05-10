package com.internet.banking.util;

import com.internet.banking.exceptions.CustomerBadRequestException;
import com.internet.banking.exceptions.TransactionBadRequestException;
import com.internet.banking.web.dto.request.CustomerRequestDTO;
import com.internet.banking.web.dto.request.TransactionRequestDTO;
import lombok.experimental.UtilityClass;
import org.springframework.util.StringUtils;

@UtilityClass
public class Validator {

    public static void validateCustomerRequestDTO(final CustomerRequestDTO customerRequestDTO) throws CustomerBadRequestException, TransactionBadRequestException {
        validateField(customerRequestDTO.getName(), "name", ControllerType.CUSTOMER);
        validateField(customerRequestDTO.isExclusivePlan(), "exclusivePlan" , ControllerType.CUSTOMER);
        validateField(customerRequestDTO.getAmount(), "amount", ControllerType.CUSTOMER);
        validateField(customerRequestDTO.getAccountNumber(), "accountNumber", ControllerType.CUSTOMER);
        validateField(customerRequestDTO.getBornDate(), "bornDate", ControllerType.CUSTOMER);
    }

    public static void validateTransactionRequestDTO(final TransactionRequestDTO transactionRequestDTO) throws CustomerBadRequestException, TransactionBadRequestException {
        validateField(transactionRequestDTO.getAmount(), "amount", ControllerType.TRANSACTION);
        validateField(transactionRequestDTO.getTransactionType(), "transactionType" , ControllerType.TRANSACTION);
        validateField(transactionRequestDTO.getAccountNumber(), "accountNumber", ControllerType.TRANSACTION);
        validateField(transactionRequestDTO.getName(), "name", ControllerType.TRANSACTION);
    }

    private void validateField(final Object value, final String field, final ControllerType controllerType) throws CustomerBadRequestException, TransactionBadRequestException {
        switch (controllerType) {
            case CUSTOMER -> {
                if(value == null || StringUtils.isEmpty(value))
                    throw new CustomerBadRequestException("Campo " + field + " não é válido");
            }

            case TRANSACTION -> {
                if(value == null || StringUtils.isEmpty(value))
                    throw new TransactionBadRequestException("Campo " + field + " não é válido");
            }
        }
    }

    private enum ControllerType {
        CUSTOMER, TRANSACTION
    }
}
