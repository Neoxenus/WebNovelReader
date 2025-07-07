package com.neoxenus.webnovelreader.exceptions;

public class UsernameExistsException extends RuntimeException {
    public UsernameExistsException(String message){
        super(message);
    }
}
