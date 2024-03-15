package com.example.consumer.service.filter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class FilterRequest {
    private List<Filter> filters = new ArrayList<>();
    private final Integer pageNumber, pageSize;

    public void add(Filter filter) {
        filters.add(filter);
    }
}
