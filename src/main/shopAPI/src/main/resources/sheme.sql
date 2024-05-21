CREATE TABLE IF NOT EXISTS address (
    id SERIAL PRIMARY KEY NOT NULL,
    country VARCHAR(255) NOT NULL DEFAULT 'RUSSIA',
    city VARCHAR(255)  NOT NULL DEFAULT 'MOSCOW',
    street VARCHAR(255) NOT NULL DEFAULT 'Tverskaya Street'
);

CREATE TABLE IF NOT EXISTS client (
    id SERIAL PRIMARY KEY,
    client_name VARCHAR(255) NOT NULL,
    client_surname VARCHAR(255),
    birthday DATE,
    gender CHAR(1) CHECK (gender IN ('M', 'F')),
    registration_date DATE, NOT NULL, DEFAULT CURRENT_DATE,
    address_id BIGINT,
    FOREIGN KEY (address_id), NOT NULL, REFERENCES address(id)
);

CREATE TABLE IF NOT EXISTS images (
    id UUID PRIMARY KEY,
    image BYTEA
);

CREATE TABLE IF NOT EXISTS supplier (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address_id BIGINT,
    phone_number VARCHAR(20),
    FOREIGN KEY (address_id) REFERENCES address(id)
);

CREATE TABLE IF NOT EXISTS product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL CHECK (price > 0.0),
    available_stock INT CHECK (available_stock >= 0),
    last_update_date DATE,
    supplier_id BIGINT,
    image_id UUID,
    FOREIGN KEY (supplier_id) REFERENCES supplier(id),
    FOREIGN KEY (image_id) REFERENCES images(id)
);

CREATE INDEX idx_client_address_id ON client(address_id);
CREATE INDEX idx_supplier_address_id ON supplier(address_id);

-- Включение расширения для генерации UUID с помощью функцию uuid_generate_v4()
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Тригер для автогенерации UUID
CREATE OR REPLACE FUNCTION generate_image_id() RETURNS TRIGGER AS $$
BEGIN
    NEW.id := uuid_generate_v4();
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER image_id_generator
    BEFORE INSERT ON images
    FOR EACH ROW
    EXECUTE FUNCTION generate_image_id();

-- Создание таблиц
CREATE TABLE IF NOT EXISTS address (
                                       id SERIAL PRIMARY KEY,
                                       country VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    street VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS client (
                                      id SERIAL PRIMARY KEY,
                                      client_name VARCHAR(255) NOT NULL,
    client_surname VARCHAR(255),
    birthday DATE,
    gender CHAR(1) CHECK (gender IN ('M', 'F')),
    registration_date DATE NOT NULL DEFAULT CURRENT_DATE,
    address_id BIGINT,
    FOREIGN KEY (address_id) REFERENCES address(id) ON DELETE CASCADE
    );

-- Создание хранимой процедуры для удаления адреса при удалении клиента
CREATE OR REPLACE FUNCTION delete_client_with_address(p_client_id bigint)
RETURNS VOID AS $$
BEGIN
DELETE FROM address WHERE id = p_client_id;
END;
$$ LANGUAGE plpgsql;


-- SELECT rolname FROM pg_roles;
-- CREATE USER user WITH PASSWORD 'your_password';
