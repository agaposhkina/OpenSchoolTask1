package com.example.consumer.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static java.lang.String.format;

@JsonIgnoreProperties("pageable")
public class ConsumerPage<T> extends PageImpl<T> {
    @JsonCreator
    public ConsumerPage(@JsonProperty("content") List<T> content,
                        @JsonProperty("number") int number,
                        @JsonProperty("size") int size,
                        @JsonProperty("totalElements") long totalElements) {
        super(content, PageRequest.of(number, size), totalElements);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(format("Page %s of %d containing %s elements", this.getNumber(), this.getTotalPages(), this.getContent().size()));
        sb.append("\n");
        for (T item : getContent()) {
            sb.append(item);
            sb.append("\n");
        }
        return sb.toString();
    }
}
