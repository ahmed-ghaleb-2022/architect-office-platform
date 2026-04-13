package com.architect.platform.servicecatalog.repository;

import com.architect.platform.servicecatalog.entity.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Long> {

    Optional<ServiceCategory> findBySlug(String slug);
    List<ServiceCategory> findByIsActiveTrueOrderByNameAsc();

}
