package com.kubernetes.basket.exception;

public class BasketNotFoundException extends RuntimeException {

    public BasketNotFoundException(String message) {
        super(message);
    }
}
