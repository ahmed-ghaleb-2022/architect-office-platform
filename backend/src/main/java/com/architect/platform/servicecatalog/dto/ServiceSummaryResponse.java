package com.architect.platform.servicecatalog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ServiceSummaryResponse {
    private Long id;
    private String name;
    private String slug;
    private String shortDescription;
    private BigDecimal basePrice;
    private String currency;
    private String categoryName;
    private String categorySlug;
}
