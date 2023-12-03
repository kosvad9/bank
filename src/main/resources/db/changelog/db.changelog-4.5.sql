--liquibase formatted sql

--changeset kosvad9:1
create table if not exists account_aud (
    id bigint not null,
    rev integer not null,
    revtype smallint,
    amount numeric(38,2),
    date_close date,
    date_create date,
    iban varchar(255),
    status varchar(255),
    id_client bigint,
    id_currency integer,
    primary key (rev, id)
);