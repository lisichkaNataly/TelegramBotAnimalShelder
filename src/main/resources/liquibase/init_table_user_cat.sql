--liquibase formatted sql
--changeset tinkov:4
CREATE TABLE user_cat
(
    id            BIGINT generated by default as identity primary key,
    "name"        VARCHAR(30) NOT NULL,
    year_of_birth INTEGER     NOT NULL,
    phone         VARCHAR     NOT NULL,
    mail          VARCHAR     NOT NULL,
    address       VARCHAR     NOT NULL,
    chat_id       BIGINT      NOT NULL,
    dog_id        BIGINT      NOT NULL,
    cat_id        BIGINT      NOT NULL,
    status        INT         NOT NULL
--     FOREIGN KEY (id) REFERENCES reportData (person_id)
);