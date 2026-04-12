package com.architect.platform.servicecatalog.entity;

import com.architect.platform.common.entity.BaseEntity;
import com.architect.platform.servicecatalog.enums.PriceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "option_values")
public class OptionValue extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "option_group_id", nullable = false)
    private OptionGroup optionGroup;

    @Column(name = "value_key", nullable = false, length = 100)
    private String valueKey;

    @Column(name = "label", nullable = false, length = 150)
    private String label;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "price_type", nullable = false, length = 50)
    private PriceType priceType;

    @Column(name = "price_value", nullable = false, precision = 12, scale = 2)
    private BigDecimal priceValue = BigDecimal.ZERO;

    @Column(name = "min_value", precision = 12, scale = 2)
    private BigDecimal minValue;

    @Column(name = "max_value", precision = 12, scale = 2)
    private BigDecimal maxValue;

    @Column(name = "sort_order", nullable = false)
    private int sortOrder = 0;

    @Column(name = "is_default", nullable = false)
    private boolean isDefault = false;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;
}
