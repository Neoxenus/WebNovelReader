package com.neoxenus.webnovelreader.exceptions;

public class EntityAlreadyInUseException extends RuntimeException{
    public EntityAlreadyInUseException(String message) {
        super(message);
    }
}
