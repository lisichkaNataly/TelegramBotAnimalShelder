--liquibase formatted sql

--changeset tinkov:1
create table if not exists pets
(
    id        bigint primary key,
    name      varchar not null,
    age       int not null,
    which_pet varchar not null,
    breed     varchar
);
create table if not exists photo_report
(
    id         bigint primary key,
    file_path  varchar not null,
    file_size  bigint  not null,
    media_type varchar not null,
    pet_id     bigint references pets (id)
);
create table if not exists user_cat
(
    id      bigint primary key,
    chat_id bigint  not null,
    name    varchar not null,
    mail    varchar not null,
    phone   varchar not null,
    pet_id  bigint references pets (id)
);
create table if not exists user_dog
(
    id      bigint primary key,
    chat_id bigint  not null,
    name    varchar not null,
    mail    varchar not null,
    phone   varchar not null,
    pet_id  bigint references pets (id)
);
create table if not exists report_pet
(
    id                  bigint primary key,
    chat_id             bigint    not null,
    info_pet            varchar   not null,
    date_time           timestamp not null,
    quality             boolean,
    photo_report_id     bigint references photo_report (id),
    user_dog_id bigint references user_dog (id),
    user_cat_id bigint references user_cat (id)
);
create table if not exists users
(
    chat_id bigint primary key,
    shelter varchar,
    name    varchar,
    phone   varchar,
    mail    varchar
);
create table if not exists volunteers
(
    id      bigint primary key,
    chat_id bigint  not null,
    name    varchar not null
);
