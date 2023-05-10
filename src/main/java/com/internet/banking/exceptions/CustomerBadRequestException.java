package com.internet.banking.exceptions;

public class CustomerBadRequestException extends Exception {

    public CustomerBadRequestException(final Exception exception, final String message) {
        super(message, exception);
    }

    public CustomerBadRequestException(final String message) {
        super(message);
    }
}
