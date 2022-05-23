package edu.example.loginapp.services.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.example.loginapp.dto.ProductDTO;
import edu.example.loginapp.exception.NotUniqueException;
import edu.example.loginapp.model.Product;
import edu.example.loginapp.model.Response;
import edu.example.loginapp.repositories.IProductRepository;
import edu.example.loginapp.services.FilterService;
import edu.example.loginapp.services.ProductService;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private FilterService filterService;

    @Override
    public ResponseEntity<Product> saveProduct(ProductDTO newProduct) {
        log.info("Started adding new product with name : {}", newProduct.getProductName());

        if (newProduct.getProductName() == null)
            throw new NullPointerException("The new product name can't be null.");

        if (!checkUniqueSerialNumber(newProduct.getSerialNumber()))
            throw new NotUniqueException(
                    "The product serial number is not unique.");
        Product product = null;
        try {
            product = productRepository.save(buildProduct(newProduct));
        } catch (Exception exception) {
            throw new IllegalArgumentException("Failed to save product in the database.");
        }

        log.info("Product with name {} added in the db at {} ", product.getProductName(), product.getAddedTime());

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Override
    public List<Product> findAllProducts() throws IOException {
        log.info("Returning all products.");
        return productRepository.findAll();
    }

    @Override
    public ResponseEntity<Product> updateProduct(Product productToUpdate) {

        if (productToUpdate == null)
            throw new NullPointerException("Product can't be null.");
        Product product = productRepository.findById(productToUpdate.getId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Product to update with id " + productToUpdate.getId() + " not found!"));

        if (productToUpdate.getProductName() != null)
            product.setProductName(productToUpdate.getProductName());

        if (productToUpdate.getSerialNumber() != null)
            product.setSerialNumber(productToUpdate.getSerialNumber());

        if (productToUpdate.getDescription() != null)
            product.setDescription(productToUpdate.getDescription());

        if (productToUpdate.getItemCondition() != null)
            product.setItemCondition(productToUpdate.getItemCondition());

        productRepository.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response<String>> delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Product with id " + id + " not found in the database."));
        productRepository.delete(product);
        return new ResponseEntity<>(buildResponse("Product deleted with id " + id, ""), HttpStatus.OK);
    }

    private Product buildProduct(ProductDTO productDTO) {
        return Product.builder()
                .productName(productDTO.getProductName())
                .serialNumber(productDTO.getSerialNumber())
                .description(productDTO.getDescription())
                .addedTime(LocalDateTime.now())
                .itemCondition(productDTO.getItemCondition())
                .build();
    }

    private Response<String> buildResponse(String message, String value) {
        return new Response<String>(message, value);
    }

    private boolean checkUniqueSerialNumber(String serialNumber) {
        return productRepository.findBySerialNumber(serialNumber) == null;
    }

    @Override
    public List<Product> findAllPerPage(int pageNum, int maxNum) {

        Pageable page = PageRequest.of(pageNum, maxNum);

        return productRepository.findAll(page).getContent();
    }

    @Override
    public long numberOfProducts() {
        return this.productRepository.count();
    }

    @Override
    public List<Product> filterValues(int page, int maxNum, String filterValue, String column) {
        return filterService.filter(filterValue, column, Product.class);
    }

}
