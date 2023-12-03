--liquibase formatted sql

--changeset kosvad9:1
CREATE TABLE IF NOT EXISTS revision(
   id SERIAL PRIMARY KEY,
   timestamp BIGINT
);