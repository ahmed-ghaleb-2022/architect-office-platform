package com.architect.platform.servicecatalog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;


@Getter
@AllArgsConstructor
public class ServiceDetailsResponse {
    private Long id;
    private String name;
    private String slug;
    private String shortDescription;
    private String description;
    private BigDecimal basePrice;
    private String currency;
    private String categoryName;
    private String categorySlug;
    private List<OptionGroupResponse> optionGroups;
}
