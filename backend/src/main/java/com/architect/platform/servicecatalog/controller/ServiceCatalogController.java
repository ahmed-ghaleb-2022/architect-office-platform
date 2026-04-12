package com.architect.platform.servicecatalog.controller;

import com.architect.platform.common.response.ApiResponse;
import com.architect.platform.common.response.ResponseBuilder;
import com.architect.platform.servicecatalog.dto.ServiceCategoryResponse;
import com.architect.platform.servicecatalog.dto.ServiceDetailsResponse;
import com.architect.platform.servicecatalog.dto.ServiceSummaryResponse;
import com.architect.platform.servicecatalog.service.ServiceCatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceCatalogController {


    private final ServiceCatalogService serviceCatalogService;

    @GetMapping("/categories")
    public ApiResponse<List<ServiceCategoryResponse>> getCategories() {
        return ResponseBuilder.success(
                "Service categories retrieved successfully",
                serviceCatalogService.getActiveCategories()
        );
    }

    @GetMapping
    public ApiResponse<List<ServiceSummaryResponse>> getServices(
            @RequestParam(required = false) Long categoryId
    ) {
        List<ServiceSummaryResponse> data = (categoryId == null)
                ? serviceCatalogService.getActiveServices()
                : serviceCatalogService.getActiveServicesByCategory(categoryId);

        return ResponseBuilder.success("Services retrieved successfully", data);
    }

    @GetMapping("/{slug}")
    public ApiResponse<ServiceDetailsResponse> getServiceDetails(@PathVariable String slug) {
        return ResponseBuilder.success(
                "Service details retrieved successfully",
                serviceCatalogService.getServiceDetailsBySlug(slug)
        );
    }

}
