package com.neoxenus.webnovelreader.exceptions;

public class NoSuchUserException extends RuntimeException {
    public NoSuchUserException(String message){
        super(message);
    }
}
