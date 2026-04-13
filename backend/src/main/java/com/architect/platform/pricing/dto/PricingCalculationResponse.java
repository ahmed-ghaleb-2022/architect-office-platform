package com.architect.platform.pricing.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class PricingCalculationResponse {
    private Long serviceId;
    private String serviceName;
    private String serviceSlug;
    private BigDecimal basePrice;
    private String currency;
    private List<PricingLineItemResponse> lineItems;
    private BigDecimal totalPrice;
}
