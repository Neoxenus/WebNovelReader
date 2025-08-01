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
            NoSuchEntityException.class

    })
    public ProblemDetail handleBadRequestsExceptions(Exception ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler({
            UsernameExistsException.class,
            EntityAlreadyExistsException.class
    })
    public ProblemDetail handleUsernameExistsException(Exception ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
    }
    @ExceptionHandler({
            UnexpectedUnauthenticatedUserException.class
    })
    public ProblemDetail handleUnexpectedUnauthenticatedUserException(Exception ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler({
            AccessDeniedException.class
    })
    public ProblemDetail handleAccessDeniedException(Exception ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
    }
    @ExceptionHandler({
            BookmarkDeletedException.class
    })
    public ProblemDetail handleBookmarkDeletedException() {
        return ProblemDetail.forStatus(HttpStatus.NO_CONTENT);
    }


    @ExceptionHandler({
            AlreadyVotedException.class
    })
    public ProblemDetail handleAlreadyVotedException(Exception ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
    }
}
