package com.architect.platform.servicecatalog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;


@Getter
@AllArgsConstructor
public class OptionValueResponse {
    private Long id;
    private String valueKey;
    private String label;
    private String description;
    private String priceType;
    private BigDecimal priceValue;
    private BigDecimal minValue;
    private BigDecimal maxValue;
    private boolean isDefault;
    private boolean isActive;
}
