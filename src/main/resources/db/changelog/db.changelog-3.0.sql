--liquibase formatted sql

--changeset kosvad9:1
alter table application
    drop constraint application_id_client_fkey;

alter table application
    add foreign key (id_client) references client
        on delete cascade;