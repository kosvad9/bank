--liquibase formatted sql

--changeset kosvad9:1
create sequence card_number_seq
    start with 1111
    maxvalue 999999999;