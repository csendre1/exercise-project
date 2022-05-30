package edu.example.loginapp.products;

public class ProductServiceException extends RuntimeException {

    public ProductServiceException() {
        super();
    }

    public ProductServiceException(String msg) {
        super(msg);
    }

}
