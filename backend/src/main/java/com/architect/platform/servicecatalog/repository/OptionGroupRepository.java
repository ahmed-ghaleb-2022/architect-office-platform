package com.architect.platform.servicecatalog.repository;

import com.architect.platform.servicecatalog.entity.OptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionGroupRepository extends JpaRepository<OptionGroup, Long> {
    List<OptionGroup> findByServiceIdOrderBySortOrderAscIdAsc(Long serviceId);
    List<OptionGroup> findByServiceIdAndIsRequiredTrueOrderBySortOrderAscIdAsc(Long serviceId);
    boolean existsByIdAndServiceId(Long id, Long serviceId);
}
