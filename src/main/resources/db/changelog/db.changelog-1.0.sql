--liquibase formatted sql

--changeset kosvad9:1
CREATE TABLE IF NOT EXISTS users(
    id BIGSERIAL PRIMARY KEY,
    phone_number VARCHAR(13) UNIQUE NOT NULL,
    password VARCHAR,
    first_name VARCHAR(64) NOT NULL ,
    last_name VARCHAR(64) NOT NULL ,
    patronymic VARCHAR(64),
    UNIQUE (first_name, last_name, patronymic)
);

--changeset kosvad9:2
CREATE TABLE IF NOT EXISTS staff(
    id_user BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    role VARCHAR(32),
    status VARCHAR(32),
    PRIMARY KEY (id_user)
);

--changeset kosvad9:3
CREATE TABLE IF NOT EXISTS client(
    id_user BIGINT NOT NULL REFERENCES users(id),
    birthdate DATE,
    passport_number VARCHAR UNIQUE NOT NULL ,
    passport_id VARCHAR UNIQUE NOT NULL ,
    passport_date DATE,
    PRIMARY KEY (id_user)
);

--changeset kosvad9:4
CREATE TABLE IF NOT EXISTS credit(
    id BIGSERIAL PRIMARY KEY,
    id_client BIGINT NOT NULL REFERENCES client(id_user),
    amount DECIMAL(14,2) NOT NULL CHECK ( amount >= 0 ),
    debt DECIMAL(14,2) NOT NULL CHECK ( debt >= 0 ),
    date_end DATE NOT NULL,
    interest_rate INT CHECK ( interest_rate >= 0 )
);

--changeset kosvad9:5
CREATE TABLE IF NOT EXISTS credit_program(
    id SERIAL PRIMARY KEY,
    description VARCHAR,
    interest_rate INT CHECK ( interest_rate >= 0 ),
    max_amount DECIMAL(14,2) CHECK ( max_amount > 0 ),
    max_period_month INT CHECK ( max_period_month > 0 )
);

--changeset kosvad9:6
CREATE TABLE IF NOT EXISTS application(
    id BIGSERIAL PRIMARY KEY,
    id_client BIGINT NOT NULL REFERENCES client(id_user),
    date DATE,
    id_program INT NOT NULL REFERENCES credit_program(id),
    status VARCHAR(32),
    description VARCHAR
);

--changeset kosvad9:7
CREATE TABLE IF NOT EXISTS currency(
    id SERIAL PRIMARY KEY,
    code VARCHAR(3) UNIQUE NOT NULL,
    name VARCHAR(32)
);

--changeset kosvad9:8
CREATE TABLE IF NOT EXISTS account(
    id BIGSERIAL PRIMARY KEY,
    id_client BIGINT NOT NULL REFERENCES client(id_user),
    iban VARCHAR(34) UNIQUE NOT NULL,
    amount DECIMAL(14,2) CHECK ( amount >= 0 ),
    id_currency INT NOT NULL REFERENCES currency(id),
    date_create DATE,
    date_close DATE,
    status VARCHAR(32)
);

--changeset kosvad9:9
CREATE TABLE IF NOT EXISTS card(
     id BIGSERIAL PRIMARY KEY,
     billing_system VARCHAR(32) NOT NULL,
     number VARCHAR UNIQUE NOT NULL,
     expiration_date DATE NOT NULL,
     CVV VARCHAR NOT NULL,
     id_account BIGINT NOT NULL REFERENCES account(id)
);