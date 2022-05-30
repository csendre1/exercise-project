package edu.example.loginapp.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.example.loginapp.dto.ProductDTO;
import edu.example.loginapp.model.Product;
import edu.example.loginapp.model.Response;
import edu.example.loginapp.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> findAll() throws IOException {
        return productService.findAllProducts();
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody ProductDTO productDTO) {
        return productService.saveProduct(productDTO);
    }

    @PutMapping
    public ResponseEntity<Product> update(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> delete(@PathVariable Long id) {
        return productService.delete(id);
    }

    @GetMapping("/paging")
    public List<Product> findAllPerPage(@RequestParam int page, @RequestParam int maxNum) {
        return productService.findAllPerPage(page, maxNum);
    }

    @GetMapping("/numberOfProducts")
    public long getMethodName() {
        return this.productService.numberOfProducts();
    }

    @GetMapping("/filter")
    public List<Product> filterProducts(@RequestParam int pageNum, @RequestParam int maxNum, @RequestParam String value,
            @RequestParam String column) {
        return productService.filterValues(pageNum, maxNum, value, column);
    }

}
