package com.example.supplier.service.filter;

import lombok.Getter;

import java.util.List;

@Getter
public class FilterRequest {
    private List<Filter> filters;
    private Integer pageNumber, pageSize;
}
