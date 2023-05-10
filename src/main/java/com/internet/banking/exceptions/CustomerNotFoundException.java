package com.internet.banking.exceptions;

public class CustomerNotFoundException extends Exception {

    public CustomerNotFoundException(final Exception exception, final String message) {
        super(message, exception);
    }

    public CustomerNotFoundException(final String message) {
        super(message);
    }
}
