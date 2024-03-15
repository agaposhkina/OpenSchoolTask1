package com.example.supplier.service.filter;

import lombok.Getter;

@Getter
public class Filter {
    private String key;
    private String joinTable;
    private Operator operator;
    private Object value;
    public enum Operator {
        EQUAL, MATCH, GREATER, LESS, GREATER_EQUAL, LESS_EQUAL
    }
}
