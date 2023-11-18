--liquibase formatted sql

--changeset kosvad9:1
alter table card
    add status varchar(32) not null default 'ACTIVE';