package org.jwt.auth.utility.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handler(Exception ex) {
        return new ResponseEntity("error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
