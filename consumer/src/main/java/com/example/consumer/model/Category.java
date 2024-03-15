package com.example.consumer.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Category {
    private Long id;
    private String name;
}
