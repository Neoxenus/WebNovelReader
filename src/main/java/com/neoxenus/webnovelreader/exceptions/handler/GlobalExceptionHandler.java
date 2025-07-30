package com.neoxenus.webnovelreader.exceptions.handler;

import com.neoxenus.webnovelreader.exceptions.ChapterWithNumberExistsException;
import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;
import com.neoxenus.webnovelreader.exceptions.UnexpectedUnauthenticatedUserException;
import com.neoxenus.webnovelreader.exceptions.UsernameExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            UsernameExistsException.class,
            NoSuchEntityException.class,
            ChapterWithNumberExistsException.class
    })
    public ProblemDetail handleBadRequestsExceptions(Exception ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler({
            UnexpectedUnauthenticatedUserException.class,
            AccessDeniedException.class
    })
    public ProblemDetail handleUnexpectedUnauthenticatedUserException(Exception ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
    }

}
