package edu.example.loginapp.products;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import edu.example.loginapp.filter.IFilterService;
import edu.example.loginapp.products.entities.Product;
import edu.example.loginapp.products.entities.dto.ProductDTO;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IFilterService filterService;

    @Override
    public ResponseEntity<Product> saveProduct(final ProductDTO newProduct) {
        log.info("Started adding new product with name : {}", newProduct.getProductName());

        if (newProduct.getProductName() == null)
            throw new ProductServiceException("The new product name can't be null.");

        if (!checkUniqueSerialNumber(newProduct.getSerialNumber()))
            throw new ProductServiceException(
                    "The product serial number is not unique.");

        try {
            final Product product = productRepository.save(buildProduct(newProduct));

            log.info("Product with name {} added in the db at {} ", product.getProductName(), product.getAddedTime());

            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception exception) {
            throw new ProductServiceException("Failed to save product in the database.");
        }
    }

    @Override
    public List<Product> findAllProducts() throws IOException {
        return productRepository.findAll();
    }

    @Override
    public ResponseEntity<Product> updateProduct(final Product product) {

        if (product == null)
            throw new ProductServiceException("Product can't be null.");
        try{
            productRepository.save(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        catch(Exception e){
            throw new ProductServiceException("Error updating the product.")
        }
    }

    @Override
    public ResponseEntity<String> delete(final Product product) {
        try {
            productRepository.delete(product);
            return new ResponseEntity<>("Product deleted", HttpStatus.OK);
        } catch (Exception e) {
            throw new ProductServiceException("Error occurred when deleting the product. Because: " + e.getMessage());
        }
    }

    @Override
    public List<Product> findAllPerPage(final int pageNum, final int maxNum) {

        Pageable page = PageRequest.of(pageNum, maxNum);

        return productRepository.findAll(page).getContent();
    }

    @Override
    public long numberOfProducts() {
        return this.productRepository.count();
    }

    @Override
    public List<Product> filterValues(int page, int maxNum, String filterValue, String column) {

        return checkFilterValue(filterValue, column)
                ? filterService.filter(filterValue, column, Product.class, page, maxNum)
                : this.findAllPerPage(page, maxNum);
    }

    private Product buildProduct(final ProductDTO productDTO) {
        return Product.builder()
                .productName(productDTO.getProductName())
                .serialNumber(productDTO.getSerialNumber())
                .description(productDTO.getDescription())
                .addedTime(LocalDateTime.now())
                .itemCondition(productDTO.getItemCondition())
                .build();
    }

    private boolean checkUniqueSerialNumber(String serialNumber) {
        return productRepository.findBySerialNumber(serialNumber) == null;
    }

    private boolean checkFilterValue(String filterValue, String column) {
        return StringUtils.hasText(column) && StringUtils.hasText(filterValue) && !filterValue.equals("null");
    }

}
