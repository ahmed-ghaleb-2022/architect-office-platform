package com.architect.platform.pricing.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class PricingLineItemResponse {

    private String type;
    private Long referenceId;
    private String name;
    private String description;
    private BigDecimal amount;
}
