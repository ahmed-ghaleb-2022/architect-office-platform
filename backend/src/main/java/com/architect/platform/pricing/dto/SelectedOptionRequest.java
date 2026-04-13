package com.architect.platform.pricing.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectedOptionRequest {
    @NotNull(message = "Option group id is required")
    private Long optionGroupId;

    @NotNull(message = "Option value id is required")
    private Long optionValueId;
}
