package com.example.consumer.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Product {
    private Long id;

    private String name;

    private String description;

    private Double price;

    private Category category;
}
