GRANT CREATE ON SCHEMA public TO postgres;
GRANT ALL ON ALL TABLES IN SCHEMA public TO postgres;
CREATE TABLE IF NOT EXISTS address
(
    id      SERIAL PRIMARY KEY,
    country VARCHAR(255) NOT NULL,
    city    VARCHAR(255) NOT NULL,
    street  VARCHAR(255) NOT NULL
);

INSERT INTO address (country, city, street)
VALUES ('Russia', 'Moscow', 'Lenina'),
       ('Russia', 'Saint Petersburg', 'Moskovskaya'),
       ('Russia', 'Kazan', 'Rostovskaya');