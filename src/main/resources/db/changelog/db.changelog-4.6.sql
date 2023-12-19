--liquibase formatted sql

--changeset kosvad9:1
alter table client
    add image VARCHAR;