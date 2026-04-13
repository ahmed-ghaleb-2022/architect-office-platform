package com.architect.platform.pricing.service;


import com.architect.platform.common.exception.BusinessException;
import com.architect.platform.common.exception.ResourceNotFoundException;
import com.architect.platform.pricing.dto.PricingCalculationRequest;
import com.architect.platform.pricing.dto.PricingCalculationResponse;
import com.architect.platform.pricing.dto.PricingLineItemResponse;
import com.architect.platform.pricing.dto.SelectedOptionRequest;
import com.architect.platform.servicecatalog.entity.OptionValue;
import com.architect.platform.servicecatalog.entity.ServiceEntity;
import com.architect.platform.servicecatalog.enums.PriceType;
import com.architect.platform.servicecatalog.repository.OptionGroupRepository;
import com.architect.platform.servicecatalog.repository.OptionValueRepository;
import com.architect.platform.servicecatalog.repository.ServiceEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PricingService {
    private final ServiceEntityRepository serviceEntityRepository;
    private final OptionGroupRepository optionGroupRepository;
    private final OptionValueRepository optionValueRepository;

    public PricingCalculationResponse calculate(PricingCalculationRequest request) {
        ServiceEntity service = serviceEntityRepository.findById(request.getServiceId())
                .orElseThrow(() -> new ResourceNotFoundException("Service not found with id: " + request.getServiceId()));

        if (!service.isActive()) {
            throw new BusinessException("Selected service is not active");
        }

        BigDecimal total = service.getBasePrice();
        List<PricingLineItemResponse> lineItems = new ArrayList<>();
        Set<Long> processedGroupIds = new HashSet<>();

        lineItems.add(new PricingLineItemResponse(
                "BASE_PRICE",
                service.getId(),
                service.getName(),
                "Base service price",
                service.getBasePrice()
        ));

        for (SelectedOptionRequest selectedOption : request.getSelectedOptions()) {
            if (!processedGroupIds.add(selectedOption.getOptionGroupId())) {
                throw new BusinessException(
                        "Duplicate selection detected for option group: " + selectedOption.getOptionGroupId()
                );
            }

            validateOptionBelongsToService(service.getId(), selectedOption);

            OptionValue optionValue = optionValueRepository.findById(selectedOption.getOptionValueId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Option value not found with id: " + selectedOption.getOptionValueId()
                    ));

            if (!optionValue.isActive()) {
                throw new BusinessException("Selected option value is not active: " + optionValue.getLabel());
            }

            BigDecimal amount = calculateOptionAmount(service.getBasePrice(), optionValue);

            total = total.add(amount);

            lineItems.add(new PricingLineItemResponse(
                    "OPTION",
                    optionValue.getId(),
                    optionValue.getLabel(),
                    optionValue.getDescription(),
                    amount
            ));
        }

        validateRequiredGroups(service.getId(), processedGroupIds);

        return new PricingCalculationResponse(
                service.getId(),
                service.getName(),
                service.getSlug(),
                service.getBasePrice(),
                service.getCurrency(),
                lineItems,
                total
        );
    }
    
    private void validateRequiredGroups(Long serviceId, Set<Long> processedGroupIds) {
        List<Long> requiredGroupIds = optionGroupRepository
                .findByServiceIdAndIsRequiredTrueOrderBySortOrderAscIdAsc(serviceId)
                .stream()
                .map(group -> group.getId())
                .toList();

        for (Long requiredGroupId : requiredGroupIds) {
            if (!processedGroupIds.contains(requiredGroupId)) {
                throw new BusinessException("Missing required option group: " + requiredGroupId);
            }
        }
    }
    private void validateOptionBelongsToService(Long serviceId, SelectedOptionRequest selectedOption) {
        boolean groupBelongsToService = optionGroupRepository.existsByIdAndServiceId(
                selectedOption.getOptionGroupId(),
                serviceId
        );

        if (!groupBelongsToService) {
            throw new BusinessException(
                    "Option group " + selectedOption.getOptionGroupId() + " does not belong to service " + serviceId
            );
        }

        boolean valueBelongsToGroup = optionValueRepository.existsByIdAndOptionGroupId(
                selectedOption.getOptionValueId(),
                selectedOption.getOptionGroupId()
        );

        if (!valueBelongsToGroup) {
            throw new BusinessException(
                    "Option value " + selectedOption.getOptionValueId() +
                            " does not belong to option group " + selectedOption.getOptionGroupId()
            );
        }
    }

    private BigDecimal calculateOptionAmount(BigDecimal basePrice, OptionValue optionValue) {
        if (optionValue.getPriceType() == PriceType.FIXED) {
            return optionValue.getPriceValue();
        }

        if (optionValue.getPriceType() == PriceType.PERCENTAGE) {
            return basePrice
                    .multiply(optionValue.getPriceValue())
                    .divide(BigDecimal.valueOf(100));
        }

        if (optionValue.getPriceType() == PriceType.PER_UNIT) {
            throw new BusinessException("PER_UNIT pricing is not supported yet in this version");
        }

        throw new BusinessException("Unsupported price type: " + optionValue.getPriceType());
    }
}
