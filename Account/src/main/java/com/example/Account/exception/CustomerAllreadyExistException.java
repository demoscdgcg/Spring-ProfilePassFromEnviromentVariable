package com.example.Account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerAllreadyExistException extends RuntimeException{

    public CustomerAllreadyExistException(String message){
        super(message);
    }
}
