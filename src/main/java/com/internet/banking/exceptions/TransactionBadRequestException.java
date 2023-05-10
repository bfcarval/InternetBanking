package com.internet.banking.exceptions;

public class TransactionBadRequestException extends Exception {

    public TransactionBadRequestException(final Exception exception, final String message) {
        super(message, exception);
    }

    public TransactionBadRequestException(final String message) {
        super(message);
    }
}
