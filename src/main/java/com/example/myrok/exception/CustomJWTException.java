package com.example.myrok.exception;

public class CustomJWTException extends RuntimeException{
    public CustomJWTException(String msg){
        super(msg);
    }
}