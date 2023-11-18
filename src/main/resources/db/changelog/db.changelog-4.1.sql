--liquibase formatted sql

--changeset kosvad9:1
alter table application
    add amount numeric(14, 2);

alter table application
    add period_month integer;