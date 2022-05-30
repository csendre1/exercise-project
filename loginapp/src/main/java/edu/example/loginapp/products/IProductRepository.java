package edu.example.loginapp.products;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.example.loginapp.products.entities.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    Product findBySerialNumber(String serialNumber);

    Optional<Product> findById(Long id);
}
