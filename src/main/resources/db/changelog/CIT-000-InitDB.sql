-- liquibase formatted sql
-- changeset frantisek.schneider:mun-000-init-schemas

create table if not exists city
(
    id      bigint          not null primary key auto_increment,
    country varchar(50)     not null,
    name    varchar(200)    not null,
    lat     decimal(60, 30) not null,
    lng     decimal(60, 30) not null

);
