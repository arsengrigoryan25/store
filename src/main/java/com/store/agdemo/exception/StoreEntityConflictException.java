package com.store.agdemo.exception;

import org.springframework.http.HttpStatus;

public class StoreEntityConflictException extends StoreClientException{
    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.CONFLICT;
    }
    public StoreEntityConflictException(String username) {        // TODO petqa anun@ poxel
        super("username - " +   username + " already exist");
    }
}
