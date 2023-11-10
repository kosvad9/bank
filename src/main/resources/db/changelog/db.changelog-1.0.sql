--liquibase formatted sql

--changeset kosvad9:1
CREATE TABLE users(
    id BIGSERIAL,
    phone_number VARCHAR(13) UNIQUE,
    password VARCHAR,
    first_name VARCHAR(64),
    last_name VARCHAR(64),
    patronymic VARCHAR(64),
    UNIQUE (first_name, last_name, patronymic)
);

--changeset kosvad9:2
CREATE TABLE staff(
    id BIGSERIAL,
    id_user BIGINT UNIQUE REFERENCES users(id) ON DELETE CASCADE,
    role VARCHAR(32),
    status VARCHAR(32)
);

--changeset kosvad9:3
CREATE TABLE client(
    id BIGSERIAL,
    id_user BIGINT UNIQUE REFERENCES users(id),
    birthdate DATE,
    passport_number VARCHAR UNIQUE,
    passport_id VARCHAR UNIQUE,
    passport_date DATE
);

--changeset kosvad9:4
CREATE TABLE credit(
    id BIGSERIAL,
    id_client BIGINT REFERENCES client(id),
    amount DECIMAL(14,2),
    debt DECIMAL(14,2),
    date_end DATE,
    interest_rate INT
);

--changeset kosvad9:5
CREATE TABLE credit_program(
    id SERIAL,
    description VARCHAR,
    interest_rate INT,
    max_amount DECIMAL(14,2),
    max_period_month INT
);

--changeset kosvad9:6
CREATE TABLE application(
    id BIGSERIAL,
    id_client BIGINT REFERENCES client(id),
    date DATE,
    id_program INT REFERENCES credit_program(id),
    status VARCHAR(32),
    description VARCHAR
);

--changeset kosvad9:7
CREATE TABLE currency(
    id SERIAL,
    code VARCHAR(3),
    name VARCHAR(32)
);

--changeset kosvad9:8
CREATE TABLE account(
    id BIGSERIAL,
    id_client BIGINT REFERENCES client(id),
    iban VARCHAR(34),
    amount DECIMAL(14,2),
    id_currency INT REFERENCES currency(id),
    date_create DATE,
    date_close DATE,
    status VARCHAR(32)
);

--changeset kosvad9:9
CREATE TABLE card(
     id BIGSERIAL,
     billing_system VARCHAR(32),
     number VARCHAR,
     expiration_date DATE,
     CVV VARCHAR,
     id_account BIGINT REFERENCES account(id)
);