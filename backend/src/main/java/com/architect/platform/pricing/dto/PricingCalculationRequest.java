package com.architect.platform.pricing.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PricingCalculationRequest {
    @NotNull(message = "Service id is required")
    private Long serviceId;

    @Valid
    @NotEmpty(message = "At least one selected option is required")
    private List<SelectedOptionRequest> selectedOptions;
}
