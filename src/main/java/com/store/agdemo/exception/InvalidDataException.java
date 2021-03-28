package com.store.agdemo.exception;

import org.springframework.http.HttpStatus;

public class InvalidDataException extends StoreClientException{
    @Override
    public HttpStatus getStatusCode() {
        return null;
    }

    public InvalidDataException(String message){
        super(message);
    }

}
