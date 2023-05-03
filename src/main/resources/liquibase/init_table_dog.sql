--liquibase formatted sql
--changeset tinkov:3
CREATE TABLE dog
(
    id            BIGINT generated by default as identity primary key,
    breed         VARCHAR             NOT NULL,
    "name"        VARCHAR             NOT NULL,
    year_of_birth INTEGER             NOT NULL,
    description   VARCHAR
--     FOREIGN KEY (id) REFERENCES userDog (dog_id)
);