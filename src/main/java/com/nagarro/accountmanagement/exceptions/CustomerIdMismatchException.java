package com.nagarro.accountmanagement.exceptions;

public class CustomerIdMismatchException extends RuntimeException{
    public CustomerIdMismatchException(String message) {
        super(message);
    }
}
