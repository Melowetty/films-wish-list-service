-- changeset denismalinin:1732744509000-1
ALTER TABLE users
    ADD     provider       VARCHAR(256) NOT NULL DEFAULT 'LOCAL';

-- changeset denismalinin:1732744509000-2
INSERT INTO role (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO role (id, name) VALUES (2, 'ROLE_ADMIN');