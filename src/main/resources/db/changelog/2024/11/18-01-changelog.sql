-- liquibase formatted sql

-- changeset denismalinin:1731877417812-1
CREATE SEQUENCE IF NOT EXISTS wish_movie_seq START WITH 1 INCREMENT BY 50;

-- changeset denismalinin:1731877417812-2
CREATE TABLE wish_movie
(
    id          BIGINT  NOT NULL,
    user_id     BIGINT,
    movie_id    BIGINT,
    is_watched  BOOLEAN NOT NULL,
    user_rating INTEGER,
    CONSTRAINT pk_wish_movie PRIMARY KEY (id)
);

-- changeset denismalinin:1731877417812-3
ALTER TABLE wish_movie
    ADD CONSTRAINT FK_WISH_MOVIE_ON_MOVIE FOREIGN KEY (movie_id) REFERENCES movie (id);

-- changeset denismalinin:1731877417812-4
ALTER TABLE wish_movie
    ADD CONSTRAINT FK_WISH_MOVIE_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

-- changeset denismalinin:1731877417812-5
ALTER TABLE wish_movie
    ADD     created       TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP;