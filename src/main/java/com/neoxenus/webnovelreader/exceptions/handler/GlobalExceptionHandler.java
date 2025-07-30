package com.neoxenus.webnovelreader.exceptions.handler;

import com.neoxenus.webnovelreader.exceptions.*;
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

    @ExceptionHandler({
            BookmarkDeletedException.class
    })
    public ProblemDetail handleBookmarkDeletedException() {
        return ProblemDetail.forStatus(HttpStatus.NO_CONTENT);
    }
}
