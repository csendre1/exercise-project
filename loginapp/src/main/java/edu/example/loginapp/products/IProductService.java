package edu.example.loginapp.products;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import edu.example.loginapp.products.entities.Product;
import edu.example.loginapp.products.entities.dto.ProductDTO;

public interface IProductService {

    ResponseEntity<Product> saveProduct(ProductDTO product);

    List<Product> findAllProducts() throws IOException;

    ResponseEntity<Product> updateProduct(Product product);

    ResponseEntity<String> delete(Product product);

    List<Product> findAllPerPage(int page, int maxNum);

    long numberOfProducts();

    List<Product> filterValues(int page, int maxNum, String filterValue, String column);
}
