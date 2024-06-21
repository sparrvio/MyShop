GRANT CREATE ON SCHEMA public TO postgres;
GRANT ALL ON ALL TABLES IN SCHEMA public TO postgres;
CREATE TABLE IF NOT EXISTS address
(
    id      SERIAL PRIMARY KEY,
    country VARCHAR(255),
    city    VARCHAR(255),
    street  VARCHAR(255)
);

INSERT INTO address (country, city, street)
VALUES ('Russia', 'Moscow', 'Lenina'),
       ('Russia', 'Saint Petersburg', 'Moskovskaya'),
       ('Russia', 'Kazan', 'Rostovskaya');