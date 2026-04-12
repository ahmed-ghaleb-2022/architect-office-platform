INSERT INTO service_categories (name, slug, description, is_active)
VALUES
('Residential Design', 'residential-design', 'Architectural services for residential projects', TRUE),
('Interior Design', 'interior-design', 'Interior design and space planning services', TRUE);

INSERT INTO services (category_id, name, slug, short_description, description, base_price, currency, is_active, sort_order)
VALUES
(1, 'Villa Design', 'villa-design', 'Custom villa design service', 'Full architectural villa design service', 10000.00, 'EUR', TRUE, 1),
(2, 'Interior Design Package', 'interior-design-package', 'Complete interior design package', 'Interior design service for residential and commercial spaces', 5000.00, 'EUR', TRUE, 1);

INSERT INTO option_groups (service_id, name, label, input_type, is_required, sort_order)
VALUES
(1, 'number_of_floors', 'Number of Floors', 'SINGLE_SELECT', TRUE, 1),
(1, 'roof_type', 'Roof Type', 'SINGLE_SELECT', TRUE, 2),
(1, 'interior_design', 'Interior Design', 'BOOLEAN', FALSE, 3);

INSERT INTO option_values (option_group_id, value_key, label, description, price_type, price_value, sort_order, is_default, is_active)
VALUES
(1, 'one_floor', 'One Floor', 'Single floor villa', 'FIXED', 0.00, 1, TRUE, TRUE),
(1, 'two_floors', 'Two Floors', 'Two-floor villa', 'FIXED', 4000.00, 2, FALSE, TRUE),
(1, 'three_floors', 'Three Floors', 'Three-floor villa', 'FIXED', 8000.00, 3, FALSE, TRUE),

(2, 'flat_roof', 'Flat Roof', 'Modern flat roof design', 'FIXED', 1000.00, 1, TRUE, TRUE),
(2, 'gable_roof', 'Gable Roof', 'Classic gable roof', 'FIXED', 2500.00, 2, FALSE, TRUE),

(3, 'no', 'No', 'Without interior design', 'FIXED', 0.00, 1, TRUE, TRUE),
(3, 'yes', 'Yes', 'Include interior design', 'FIXED', 6000.00, 2, FALSE, TRUE);