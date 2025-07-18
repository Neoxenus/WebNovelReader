package com.neoxenus.webnovelreader.exceptions;

public class UnexpectedUnauthenticatedUserException extends RuntimeException {
    public UnexpectedUnauthenticatedUserException(String message) {
        super(message);
    }
}
