package com.architect.platform.servicecatalog.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ServiceCategoryResponse {
    private Long id;
    private String name;
    private String slug;
    private String description;
}
