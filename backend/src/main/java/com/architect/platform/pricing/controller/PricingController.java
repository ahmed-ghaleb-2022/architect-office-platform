package com.architect.platform.pricing.controller;


import com.architect.platform.common.response.ApiResponse;
import com.architect.platform.common.response.ResponseBuilder;
import com.architect.platform.pricing.dto.PricingCalculationRequest;
import com.architect.platform.pricing.dto.PricingCalculationResponse;
import com.architect.platform.pricing.service.PricingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pricing")
@RequiredArgsConstructor
public class PricingController {
    private final PricingService pricingService;

    @PostMapping("/calculate")
    public ApiResponse<PricingCalculationResponse> calculate(
            @Valid @RequestBody PricingCalculationRequest request
    ) {
        return ResponseBuilder.success(
                "Pricing calculated successfully",
                pricingService.calculate(request)
        );
    }
}
