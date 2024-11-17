-- liquibase formatted sql

-- changeset denismalinin:1731805008569-1
CREATE SEQUENCE IF NOT EXISTS role_seq START WITH 1 INCREMENT BY 50;

-- changeset denismalinin:1731805008569-2
CREATE SEQUENCE IF NOT EXISTS users_seq START WITH 1 INCREMENT BY 50;

-- changeset denismalinin:1731805008569-3
CREATE TABLE role
(
    id   BIGINT       NOT NULL,
    name VARCHAR(64)  NOT NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

-- changeset denismalinin:1731805008569-4
CREATE TABLE users
(
    id       BIGINT                      NOT NULL,
    created  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    modified TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    username VARCHAR(64)                 NOT NULL,
    password VARCHAR(255)                NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

-- changeset denismalinin:1731805008569-5
CREATE TABLE users_roles
(
    roles_id BIGINT NOT NULL,
    users_id BIGINT NOT NULL,
    CONSTRAINT pk_users_roles PRIMARY KEY (roles_id, users_id)
);

-- changeset denismalinin:1731805008569-6
ALTER TABLE role
    ADD CONSTRAINT uc_role_name UNIQUE (name);

-- changeset denismalinin:1731805008569-7
ALTER TABLE users
    ADD CONSTRAINT uc_users_username UNIQUE (username);

-- changeset denismalinin:1731805008569-8
ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_role_entity FOREIGN KEY (roles_id) REFERENCES role (id);

-- changeset denismalinin:1731805008569-9
ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_user_entity FOREIGN KEY (users_id) REFERENCES users (id);

