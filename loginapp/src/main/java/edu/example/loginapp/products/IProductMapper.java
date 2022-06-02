package edu.example.loginapp.products;

import org.mapstruct.Mapper;

import edu.example.loginapp.products.entities.Product;
import edu.example.loginapp.products.entities.dto.ProductDTO;

@Mapper
public interface IProductMapper {
    Product dtoToProduct(ProductDTO entity);

    ProductDTO productToDto(Product entity);
}
