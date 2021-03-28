package com.store.agdemo.exception;

import org.springframework.http.HttpStatus;

public abstract class StoreClientException extends RuntimeException{

    public StoreClientException(String message) {
        super(message);
    }

    public abstract HttpStatus getStatusCode();
}
