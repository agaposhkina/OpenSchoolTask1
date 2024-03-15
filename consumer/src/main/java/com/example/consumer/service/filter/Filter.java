package com.example.consumer.service.filter;

import lombok.Builder;
import lombok.Getter;

@Builder
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
