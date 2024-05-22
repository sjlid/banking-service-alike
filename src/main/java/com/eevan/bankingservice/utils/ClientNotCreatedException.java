package com.eevan.bankingservice.utils;

public class ClientNotCreatedException extends RuntimeException {

    public ClientNotCreatedException(String message) {
        super(message);
    }
}
