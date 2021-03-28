package com.store.agdemo.exception;

import org.springframework.http.HttpStatus;

public class IllegalUserStateException extends StoreClientException{
    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.FORBIDDEN;
    }
    public IllegalUserStateException(String username) {
        super(username + "- this user is blocked");
    }


}
