package edu.example.loginapp.dto;

import edu.example.loginapp.model.ItemCondition;
import lombok.Data;

@Data
public class ProductDTO {

    private String productName;

    private String serialNumber;

    private String description;

    private ItemCondition itemCondition;
}
