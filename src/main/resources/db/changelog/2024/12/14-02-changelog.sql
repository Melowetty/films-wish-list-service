-- liquibase formatted sql

-- changeset denismalinin:1734200846069-10
CREATE SEQUENCE IF NOT EXISTS translate_seq START WITH 1 INCREMENT BY 50;

-- changeset denismalinin:1734200846069-17
CREATE TABLE translate
(
    id      BIGINT       NOT NULL,
    english VARCHAR(512) NOT NULL,
    russian VARCHAR(512) NOT NULL,
    CONSTRAINT pk_translate PRIMARY KEY (id)
);

-- changeset denismalinin:1734200846069-18
ALTER TABLE movie
    ADD description_id BIGINT;
ALTER TABLE movie
    ADD title_id BIGINT;

-- changeset denismalinin:1734200846069-19
ALTER TABLE actor
    ADD name_id BIGINT;

-- changeset denismalinin:1734200846069-20
ALTER TABLE country
    ADD name_id BIGINT;

-- changeset denismalinin:1734200846069-21
ALTER TABLE director
    ADD name_id BIGINT;

-- changeset denismalinin:1734200846069-22
ALTER TABLE genre
    ADD name_id BIGINT;

-- changeset denismalinin:1734200846069-23
ALTER TABLE language
    ADD name_id BIGINT;

-- changeset denismalinin:1734200846069-24
ALTER TABLE writer
    ADD name_id BIGINT;

-- changeset denismalinin:1734200846069-26
ALTER TABLE actor
    ADD CONSTRAINT FK_ACTOR_ON_NAME FOREIGN KEY (name_id) REFERENCES translate (id);

-- changeset denismalinin:1734200846069-27
ALTER TABLE country
    ADD CONSTRAINT FK_COUNTRY_ON_NAME FOREIGN KEY (name_id) REFERENCES translate (id);

-- changeset denismalinin:1734200846069-28
ALTER TABLE director
    ADD CONSTRAINT FK_DIRECTOR_ON_NAME FOREIGN KEY (name_id) REFERENCES translate (id);

-- changeset denismalinin:1734200846069-29
ALTER TABLE genre
    ADD CONSTRAINT FK_GENRE_ON_NAME FOREIGN KEY (name_id) REFERENCES translate (id);

-- changeset denismalinin:1734200846069-30
ALTER TABLE language
    ADD CONSTRAINT FK_LANGUAGE_ON_NAME FOREIGN KEY (name_id) REFERENCES translate (id);

-- changeset denismalinin:1734200846069-31
ALTER TABLE movie
    ADD CONSTRAINT FK_MOVIE_ON_DESCRIPTION FOREIGN KEY (description_id) REFERENCES translate (id);

-- changeset denismalinin:1734200846069-32
ALTER TABLE movie
    ADD CONSTRAINT FK_MOVIE_ON_TITLE FOREIGN KEY (title_id) REFERENCES translate (id);

-- changeset denismalinin:1734200846069-33
ALTER TABLE writer
    ADD CONSTRAINT FK_WRITER_ON_NAME FOREIGN KEY (name_id) REFERENCES translate (id);

-- changeset denismalinin:1734200846069-47
ALTER TABLE movie
    DROP COLUMN description;
ALTER TABLE movie
    DROP COLUMN title;

-- changeset denismalinin:1734200846069-48
ALTER TABLE actor
    DROP COLUMN name;

-- changeset denismalinin:1734200846069-49
ALTER TABLE country
    DROP COLUMN name;

-- changeset denismalinin:1734200846069-50
ALTER TABLE director
    DROP COLUMN name;

-- changeset denismalinin:1734200846069-51
ALTER TABLE genre
    DROP COLUMN name;

-- changeset denismalinin:1734200846069-52
ALTER TABLE language
    DROP COLUMN name;

-- changeset denismalinin:1734200846069-53
ALTER TABLE writer
    DROP COLUMN name;

