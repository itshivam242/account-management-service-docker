package com.nagarro.accountmanagement.exceptions;

public class BalanceInsufficientException extends RuntimeException{
    public BalanceInsufficientException(String message) {
        super(message);
    }
}
