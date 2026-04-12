package com.architect.platform.servicecatalog.entity;

import com.architect.platform.common.entity.BaseEntity;
import com.architect.platform.servicecatalog.enums.InputType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "option_groups")
public class OptionGroup extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceEntity service;

    @Column(name = "name", nullable = false, length = 120)
    private String name;

    @Column(name = "label", nullable = false, length = 150)
    private String label;

    @Enumerated(EnumType.STRING)
    @Column(name = "input_type", nullable = false, length = 50)
    private InputType inputType;

    @Column(name = "is_required", nullable = false)
    private boolean isRequired = false;

    @Column(name = "sort_order", nullable = false)
    private int sortOrder = 0;
}
