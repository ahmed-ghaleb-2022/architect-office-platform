package com.architect.platform.servicecatalog.service;


import com.architect.platform.common.exception.ResourceNotFoundException;
import com.architect.platform.servicecatalog.dto.*;
import com.architect.platform.servicecatalog.entity.OptionGroup;
import com.architect.platform.servicecatalog.entity.OptionValue;
import com.architect.platform.servicecatalog.entity.ServiceCategory;
import com.architect.platform.servicecatalog.entity.ServiceEntity;
import com.architect.platform.servicecatalog.repository.OptionGroupRepository;
import com.architect.platform.servicecatalog.repository.OptionValueRepository;
import com.architect.platform.servicecatalog.repository.ServiceCategoryRepository;
import com.architect.platform.servicecatalog.repository.ServiceEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceCatalogService {

    private final ServiceCategoryRepository serviceCategoryRepository;
    private final ServiceEntityRepository serviceEntityRepository;
    private final OptionGroupRepository optionGroupRepository;
    private final OptionValueRepository optionValueRepository;

    public List<ServiceCategoryResponse> getActiveCategories() {
        return serviceCategoryRepository.findByIsActiveTrueOrderByNameAsc()
                .stream()
                .map(this::mapCategory)
                .toList();
    }

    public List<ServiceSummaryResponse> getActiveServices() {
        return serviceEntityRepository.findByIsActiveTrueOrderBySortOrderAscNameAsc()
                .stream()
                .map(this::mapServiceSummary)
                .toList();
    }

    public List<ServiceSummaryResponse> getActiveServicesByCategory(Long categoryId) {
        return serviceEntityRepository.findByCategoryIdAndIsActiveTrueOrderBySortOrderAscNameAsc(categoryId)
                .stream()
                .map(this::mapServiceSummary)
                .toList();
    }

    public ServiceDetailsResponse getServiceDetailsBySlug(String slug) {
        ServiceEntity service = serviceEntityRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found with slug: " + slug));

        List<OptionGroupResponse> optionGroups = optionGroupRepository.findByServiceIdOrderBySortOrderAscIdAsc(service.getId())
                .stream()
                .map(this::mapOptionGroup)
                .toList();

        return new ServiceDetailsResponse(
                service.getId(),
                service.getName(),
                service.getSlug(),
                service.getShortDescription(),
                service.getDescription(),
                service.getBasePrice(),
                service.getCurrency(),
                service.getCategory().getName(),
                service.getCategory().getSlug(),
                optionGroups
        );
    }

    private ServiceCategoryResponse mapCategory(ServiceCategory category) {
        return new ServiceCategoryResponse(
                category.getId(),
                category.getName(),
                category.getSlug(),
                category.getDescription()
        );
    }

    private ServiceSummaryResponse mapServiceSummary(ServiceEntity service) {
        return new ServiceSummaryResponse(
                service.getId(),
                service.getName(),
                service.getSlug(),
                service.getShortDescription(),
                service.getBasePrice(),
                service.getCurrency(),
                service.getCategory().getName(),
                service.getCategory().getSlug()
        );
    }

    private OptionGroupResponse mapOptionGroup(OptionGroup group) {
        List<OptionValueResponse> values = optionValueRepository.findByOptionGroupIdOrderBySortOrderAscIdAsc(group.getId())
                .stream()
                .map(this::mapOptionValue)
                .toList();

        return new OptionGroupResponse(
                group.getId(),
                group.getName(),
                group.getLabel(),
                group.getInputType().name(),
                group.isRequired(),
                group.getSortOrder(),
                values
        );
    }

    private OptionValueResponse mapOptionValue(OptionValue value) {
        return new OptionValueResponse(
                value.getId(),
                value.getValueKey(),
                value.getLabel(),
                value.getDescription(),
                value.getPriceType().name(),
                value.getPriceValue(),
                value.getMinValue(),
                value.getMaxValue(),
                value.isDefault(),
                value.isActive()
        );
    }
}
