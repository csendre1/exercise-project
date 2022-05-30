package edu.example.loginapp.security;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import edu.example.loginapp.autentication.AuthenticationServiceException;
import edu.example.loginapp.filter.FilterServiceException;
import edu.example.loginapp.products.ProductServiceException;
import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@Log4j2
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { AuthenticationServiceException.class })
    public ResponseEntity<Object> handleAuthenticationExceptions(final RuntimeException ex) {
        log.error("Error occurred in authentication service, {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), LocalDateTime.now(),
                HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { ProductServiceException.class })
    public ResponseEntity<Object> handleProductExceptions(final RuntimeException ex) {
        log.error("Error occurred in authentication service, {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), LocalDateTime.now(),
                HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { FilterServiceException.class })
    public ResponseEntity<Object> handleFilterExceptions(final RuntimeException ex) {
        log.error("Error occurred in authentication service, {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), LocalDateTime.now(),
                HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), LocalDateTime.now(), status);
        return new ResponseEntity<>(errorResponse, status);
    }

}
