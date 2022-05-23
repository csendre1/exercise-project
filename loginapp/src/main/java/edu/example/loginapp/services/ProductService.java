package edu.example.loginapp.services;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import edu.example.loginapp.dto.ProductDTO;
import edu.example.loginapp.model.Product;
import edu.example.loginapp.model.Response;

public interface ProductService {

    ResponseEntity<Product> saveProduct(ProductDTO product);

    List<Product> findAllProducts() throws IOException;

    ResponseEntity<Product> updateProduct(Product product);

    ResponseEntity<Response<String>> delete(Long id);

    List<Product> findAllPerPage(int page, int maxNum);

    long numberOfProducts();

    List<Product> filterValues(int page, int maxNum, String filterValue, String column);
}
