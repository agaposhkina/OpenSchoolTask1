package com.example.supplier.service.filter;

import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import static java.lang.Boolean.TRUE;

@RequiredArgsConstructor
public class FilterSpecification<T> implements Specification<T> {
    private final FilterRequest filterRequest;
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.equal(criteriaBuilder.literal(TRUE), TRUE);

        for (Filter filter : filterRequest.getFilters()) {
            Predicate andPredicate;
            switch (filter.getOperator()) {
                case EQUAL -> andPredicate = criteriaBuilder.equal(fieldValue(root, filter),
                        filter.getValue());
                case MATCH -> andPredicate = criteriaBuilder.like(fieldValue(root, filter),
                        "%"+filter.getValue()+"%");
                case GREATER -> andPredicate = criteriaBuilder.greaterThan(fieldValue(root, filter),
                        filter.getValue().toString());
                case LESS -> andPredicate = criteriaBuilder.lessThan(fieldValue(root, filter),
                        filter.getValue().toString());
                case GREATER_EQUAL -> andPredicate = criteriaBuilder.greaterThanOrEqualTo(fieldValue(root, filter),
                        filter.getValue().toString());
                case LESS_EQUAL -> andPredicate = criteriaBuilder.lessThanOrEqualTo(fieldValue(root, filter),
                        filter.getValue().toString());
                default -> throw new IllegalStateException("Unexpected value: " + filter.getOperator());
            }

            predicate = criteriaBuilder.and(andPredicate, predicate);
        }
        return predicate;
    }

    private static <R> Expression<R> fieldValue(Root<?> root, Filter filter) {
        if (filter.getJoinTable() != null)
           return root.join(filter.getJoinTable()).get(filter.getKey());
       return root.get(filter.getKey());
    }
}
