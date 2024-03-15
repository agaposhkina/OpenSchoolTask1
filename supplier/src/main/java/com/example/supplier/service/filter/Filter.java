package com.example.supplier.service.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class Filter {
    @Schema(description = "Поле фильтрации", example = "name", required = true)
    private String key;
    @Schema(description = "Опциональное имя связанной таблицы, в которой находится поле фильтрации",
            example = "category", required = false)
    private String joinTable;
    @Schema(description = "Оператор", example = "MATCH", required = true)
    private Operator operator;
    @Schema(description = "Значение", example = "SPORT", required = true)
    private Object value;
    public enum Operator {
        EQUAL, MATCH, GREATER, LESS, GREATER_EQUAL, LESS_EQUAL
    }
}
