package edu.example.loginapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.example.loginapp.model.Product;

public interface IProductRepository extends JpaRepository<Product, Long> {

    Product findBySerialNumber(String serialNumber);

    Optional<Product> findById(Long id);
}
