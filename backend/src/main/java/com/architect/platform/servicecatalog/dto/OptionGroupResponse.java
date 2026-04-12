package com.architect.platform.servicecatalog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@Getter
@AllArgsConstructor
public class OptionGroupResponse {
    private Long id;
    private String name;
    private String label;
    private String inputType;
    private boolean isRequired;
    private int sortOrder;
    private List<OptionValueResponse> values;
}
