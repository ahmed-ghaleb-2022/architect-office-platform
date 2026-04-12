CREATE TABLE service_categories (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    slug VARCHAR(120) NOT NULL UNIQUE,
    description TEXT,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE services (
    id BIGSERIAL PRIMARY KEY,
    category_id BIGINT NOT NULL,
    name VARCHAR(150) NOT NULL,
    slug VARCHAR(180) NOT NULL UNIQUE,
    short_description VARCHAR(300),
    description TEXT,
    base_price NUMERIC(12, 2) NOT NULL DEFAULT 0,
    currency VARCHAR(10) NOT NULL DEFAULT 'EUR',
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    sort_order INT NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_services_category
        FOREIGN KEY (category_id) REFERENCES service_categories(id)
);

CREATE TABLE option_groups (
    id BIGSERIAL PRIMARY KEY,
    service_id BIGINT NOT NULL,
    name VARCHAR(120) NOT NULL,
    label VARCHAR(150) NOT NULL,
    input_type VARCHAR(50) NOT NULL,
    is_required BOOLEAN NOT NULL DEFAULT FALSE,
    sort_order INT NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_option_groups_service
        FOREIGN KEY (service_id) REFERENCES services(id) ON DELETE CASCADE
);

CREATE TABLE option_values (
    id BIGSERIAL PRIMARY KEY,
    option_group_id BIGINT NOT NULL,
    value_key VARCHAR(100) NOT NULL,
    label VARCHAR(150) NOT NULL,
    description TEXT,
    price_type VARCHAR(50) NOT NULL,
    price_value NUMERIC(12, 2) NOT NULL DEFAULT 0,
    min_value NUMERIC(12, 2),
    max_value NUMERIC(12, 2),
    sort_order INT NOT NULL DEFAULT 0,
    is_default BOOLEAN NOT NULL DEFAULT FALSE,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_option_values_group
        FOREIGN KEY (option_group_id) REFERENCES option_groups(id) ON DELETE CASCADE
);