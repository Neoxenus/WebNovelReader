package com.neoxenus.webnovelreader.exceptions;

public class NoSuchBookException extends RuntimeException {
    public NoSuchBookException(String message){
        super(message);
    }
}
