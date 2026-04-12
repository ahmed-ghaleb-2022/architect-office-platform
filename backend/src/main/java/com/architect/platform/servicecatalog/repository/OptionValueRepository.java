package com.architect.platform.servicecatalog.repository;

import com.architect.platform.servicecatalog.entity.OptionValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionValueRepository extends JpaRepository<OptionValue, Long> {
    List<OptionValue> findByOptionGroupIdOrderBySortOrderAscIdAsc(Long optionGroupId);
}
