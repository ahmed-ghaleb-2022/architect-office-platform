package com.architect.platform.servicecatalog.repository;


import com.architect.platform.servicecatalog.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceEntityRepository extends JpaRepository<ServiceEntity, Long> {

    Optional<ServiceEntity> findBySlug(String slug);
    List<ServiceEntity> findByIsActiveTrueOrderBySortOrderAscNameAsc();
    List<ServiceEntity> findByCategoryIdAndIsActiveTrueOrderBySortOrderAscNameAsc(Long categoryId);
}
