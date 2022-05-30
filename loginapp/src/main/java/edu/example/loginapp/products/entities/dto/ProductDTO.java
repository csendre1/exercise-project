package edu.example.loginapp.products.entities.dto;

import edu.example.loginapp.products.entities.ItemCondition;
import lombok.Data;

@Data
public class ProductDTO {

    private String productName;

    private String serialNumber;

    private String description;

    private ItemCondition itemCondition;
}
