package com.internet.banking.exceptions;

import com.internet.banking.exceptions.data.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            Object body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        return null;
    }

    @ExceptionHandler( value = Exception.class)
    public ResponseEntity genericException(Exception exception) {
        return new ResponseEntity(
                ErrorResponse.builder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(exception.getMessage())
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(value = CustomerBadRequestException.class)
    public ResponseEntity customerBadRequestException(CustomerBadRequestException exception) {
        return new ResponseEntity(
                ErrorResponse.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message(exception.getMessage())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(value = CustomerNotFoundException.class)
    public ResponseEntity customerNotFoundException(CustomerNotFoundException exception) {
        return new ResponseEntity(
                ErrorResponse.builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message(exception.getMessage())
                        .build(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(value = TransactionBadRequestException.class)
    public ResponseEntity transactionBadRequestException(TransactionBadRequestException exception) {
        return new ResponseEntity(
                ErrorResponse.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message(exception.getMessage())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }
}
