--liquibase formatted sql

--changeset kosvad9:1
alter table bank.credit
    add "last_payment_date" DATE;