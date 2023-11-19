package com.riat.inventory.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ItemDto {

    @Size(min = 5, max = 50)
    String name;

    @Min(value = 0)
    int count;

    String description;
    double price;
}
