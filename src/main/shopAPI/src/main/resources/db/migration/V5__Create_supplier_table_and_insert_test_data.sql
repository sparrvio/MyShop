CREATE TABLE IF NOT EXISTS supplier (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address_id BIGINT NOT NULL,
    phone_number VARCHAR(255) DEFAULT 'default_value'
);

INSERT INTO supplier (name, address_id, phone_number) VALUES
    ('Acme Corp', 1, '123-456-7890'),
    ('Globex Inc.', 2, '987-654-3210'),
    ('Initech Ltd.', 3, '555-555-5555');
