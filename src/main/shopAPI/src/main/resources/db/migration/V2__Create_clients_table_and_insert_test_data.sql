CREATE TABLE IF NOT EXISTS client (
    id SERIAL PRIMARY KEY,
    client_name VARCHAR(255) NOT NULL,
    client_surname VARCHAR(255),
    birthday DATE DEFAULT '1900-01-01',
    gender CHAR(1) CHECK (gender IN ('M', 'F')),
    registration_date DATE NOT NULL DEFAULT CURRENT_DATE,
    address_id BIGINT NOT NULL
    );


INSERT INTO client (client_name, client_surname, birthday, gender, address_id) VALUES
    ('John', 'Doe', '1980-05-15', 'M', 1),
    ('Jane', 'Smith', '1990-07-20', 'F', 2),
    ('Alice', 'Johnson', '2000-09-25', 'F', 3);
