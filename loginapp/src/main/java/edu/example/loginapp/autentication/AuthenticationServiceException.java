package edu.example.loginapp.autentication;

public class AuthenticationServiceException extends RuntimeException {

    public AuthenticationServiceException() {
        super();
    }

    public AuthenticationServiceException(final String msg) {
        super(msg);
    }
}
