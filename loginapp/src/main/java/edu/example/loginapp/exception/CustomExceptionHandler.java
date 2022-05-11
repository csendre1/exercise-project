package edu.example.loginapp.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import edu.example.loginapp.model.ErrorResponse;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { RuntimeException.class })
    public ResponseEntity<Object> handleRuntimeExceptions(RuntimeException ex) {
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
