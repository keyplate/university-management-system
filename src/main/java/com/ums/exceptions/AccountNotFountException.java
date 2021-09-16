package com.ums.exceptions;

public class AccountNotFountException extends RuntimeException{

    public AccountNotFountException() {
        super();
    }

    public AccountNotFountException(String message) {
        super(message);
    }
}
