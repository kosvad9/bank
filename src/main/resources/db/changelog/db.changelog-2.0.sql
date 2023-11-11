--liquibase formatted sql

--changeset kosvad9:1
alter table IF EXISTS client
    rename column birthdate to birth_date;