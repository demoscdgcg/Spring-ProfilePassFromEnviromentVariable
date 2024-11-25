package com.example.Account.exception;

public class RescourceNotFoundException extends RuntimeException{

    public RescourceNotFoundException(String message,String feildName,String feildValue){
        super(String.format("%s not found with given input data %s:%s",message,feildName,feildValue));
    }

}
