-- liquibase formatted sql

-- changeset denismalinin:1732747926121-2
CREATE TABLE user_role
(
    user_id BIGINT NOT NULL,
    roles   VARCHAR(64)
);

-- changeset denismalinin:1732747926121-4
ALTER TABLE user_role
    ADD CONSTRAINT fk_user_role_on_user_entity FOREIGN KEY (user_id) REFERENCES users (id);

-- changeset denismalinin:1732747926121-25
DROP TABLE users_roles CASCADE;

-- changeset denismalinin:1732747926121-26
ALTER TABLE role DROP CONSTRAINT uc_role_name;

-- changeset denismalinin:1732747926121-27
DROP TABLE role CASCADE;

