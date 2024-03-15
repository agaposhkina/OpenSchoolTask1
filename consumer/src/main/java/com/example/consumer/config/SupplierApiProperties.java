package com.example.consumer.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "supplier.api")
@Getter
@Setter
@Validated
public class SupplierApiProperties {
    @NotBlank
    private String url,
            categories, category,
            products, product, filterProducts;
}
