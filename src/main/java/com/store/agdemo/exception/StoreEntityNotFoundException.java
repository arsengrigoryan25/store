package com.store.agdemo.exception;

import org.springframework.http.HttpStatus;

public class StoreEntityNotFoundException extends StoreClientException{
    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.NOT_FOUND;
    }

    public StoreEntityNotFoundException(Long id, String entityName) {
       super(entityName + " with id " + id + " not found");
    }
}
