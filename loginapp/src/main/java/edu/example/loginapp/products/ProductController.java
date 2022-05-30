package edu.example.loginapp.products;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.example.loginapp.products.entities.Product;
import edu.example.loginapp.products.entities.dto.ProductDTO;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public List<Product> findAll() throws IOException {
        return productService.findAllProducts();
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody final ProductDTO productDTO) {
        return productService.saveProduct(productDTO);
    }

    @PutMapping
    public ResponseEntity<Product> update(@RequestBody final Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestBody final Product product) {
        return productService.delete(product);
    }

    @GetMapping("/paging")
    public List<Product> findAllPerPage(@RequestParam final int page, @RequestParam final int maxNum) {
        return productService.findAllPerPage(page, maxNum);
    }

    @GetMapping("/numberOfProducts")
    public long getMethodName() {
        return this.productService.numberOfProducts();
    }

    @GetMapping("/filter")
    public List<Product> filterProducts(@RequestParam final int pageNum, @RequestParam final int maxNum,
            @RequestParam String value,
            @RequestParam String column) {
        return productService.filterValues(pageNum, maxNum, value, column);
    }

}
