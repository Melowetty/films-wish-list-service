-- liquibase formatted sql

-- changeset denismalinin:1731784769684-1
CREATE SEQUENCE IF NOT EXISTS actor_seq START WITH 1 INCREMENT BY 50;

-- changeset denismalinin:1731784769684-2
CREATE SEQUENCE IF NOT EXISTS country_seq START WITH 1 INCREMENT BY 50;

-- changeset denismalinin:1731784769684-3
CREATE SEQUENCE IF NOT EXISTS director_seq START WITH 1 INCREMENT BY 50;

-- changeset denismalinin:1731784769684-4
CREATE SEQUENCE IF NOT EXISTS genre_seq START WITH 1 INCREMENT BY 50;

-- changeset denismalinin:1731784769684-5
CREATE SEQUENCE IF NOT EXISTS language_seq START WITH 1 INCREMENT BY 50;

-- changeset denismalinin:1731784769684-6
CREATE SEQUENCE IF NOT EXISTS movie_seq START WITH 1 INCREMENT BY 50;

-- changeset denismalinin:1731784769684-8
CREATE SEQUENCE IF NOT EXISTS writer_seq START WITH 1 INCREMENT BY 50;

-- changeset denismalinin:1731784769684-9
CREATE TABLE actor
(
    id   BIGINT       NOT NULL,
    name VARCHAR(127) NOT NULL,
    CONSTRAINT pk_actor PRIMARY KEY (id)
);

-- changeset denismalinin:1731784769684-10
CREATE TABLE country
(
    id   BIGINT       NOT NULL,
    name VARCHAR(64) NOT NULL,
    CONSTRAINT pk_country PRIMARY KEY (id)
);

-- changeset denismalinin:1731784769684-11
CREATE TABLE director
(
    id   BIGINT       NOT NULL,
    name VARCHAR(127) NOT NULL,
    CONSTRAINT pk_director PRIMARY KEY (id)
);

-- changeset denismalinin:1731784769684-12
CREATE TABLE genre
(
    id   BIGINT       NOT NULL,
    name VARCHAR(64) NOT NULL,
    CONSTRAINT pk_genre PRIMARY KEY (id)
);

-- changeset denismalinin:1731784769684-13
CREATE TABLE language
(
    id   BIGINT       NOT NULL,
    name VARCHAR(64) NOT NULL,
    CONSTRAINT pk_language PRIMARY KEY (id)
);

-- changeset denismalinin:1731784769684-14
CREATE TABLE movie
(
    id            BIGINT                      NOT NULL,
    dtype         VARCHAR(31),
    created       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    modified      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    imdb_id       VARCHAR(32)                NOT NULL,
    title         VARCHAR(64)                NOT NULL,
    year          INTEGER                     NOT NULL,
    rating        VARCHAR(64)                NOT NULL,
    released      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    duration      INTEGER                     NOT NULL,
    description   VARCHAR(255)                NOT NULL,
    poster_link   VARCHAR(255)                NOT NULL,
    imdb_rating   FLOAT                       NOT NULL,
    seasons_count INTEGER                     NOT NULL,
    box_office    BIGINT                      NOT NULL,
    CONSTRAINT pk_movie PRIMARY KEY (id)
);

-- changeset denismalinin:1731784769684-15
CREATE TABLE movie_actors
(
    actors_id BIGINT NOT NULL,
    movies_id BIGINT NOT NULL
);

-- changeset denismalinin:1731784769684-16
CREATE TABLE movie_countries
(
    countries_id BIGINT NOT NULL,
    movies_id    BIGINT NOT NULL,
    CONSTRAINT pk_movies_countries PRIMARY KEY (countries_id, movies_id)
);

-- changeset denismalinin:1731784769684-17
CREATE TABLE movie_directors
(
    directors_id BIGINT NOT NULL,
    movies_id    BIGINT NOT NULL
);

-- changeset denismalinin:1731784769684-18
CREATE TABLE movie_genres
(
    genres_id BIGINT NOT NULL,
    movies_id BIGINT NOT NULL,
    CONSTRAINT pk_movies_genres PRIMARY KEY (genres_id, movies_id)
);

-- changeset denismalinin:1731784769684-19
CREATE TABLE movie_languages
(
    languages_id BIGINT NOT NULL,
    movies_id    BIGINT NOT NULL,
    CONSTRAINT pk_movies_languages PRIMARY KEY (languages_id, movies_id)
);

-- changeset denismalinin:1731784769684-20
CREATE TABLE movie_writers
(
    movies_id  BIGINT NOT NULL,
    writers_id BIGINT NOT NULL
);

-- changeset denismalinin:1731784769684-27
CREATE TABLE writer
(
    id   BIGINT       NOT NULL,
    name VARCHAR(127) NOT NULL,
    CONSTRAINT pk_writer PRIMARY KEY (id)
);

-- changeset denismalinin:1731784769684-28
ALTER TABLE movie
    ADD CONSTRAINT uc_movies_imdbid UNIQUE (imdb_id);

-- changeset denismalinin:1731784769684-29
ALTER TABLE movie_actors
    ADD CONSTRAINT fk_movact_on_actor_entity FOREIGN KEY (actors_id) REFERENCES actor (id);

-- changeset denismalinin:1731784769684-30
ALTER TABLE movie_actors
    ADD CONSTRAINT fk_movact_on_movie_entity FOREIGN KEY (movies_id) REFERENCES movie (id);

-- changeset denismalinin:1731784769684-31
ALTER TABLE movie_countries
    ADD CONSTRAINT fk_movcou_on_country_entity FOREIGN KEY (countries_id) REFERENCES country (id);

-- changeset denismalinin:1731784769684-32
ALTER TABLE movie_countries
    ADD CONSTRAINT fk_movcou_on_movie_entity FOREIGN KEY (movies_id) REFERENCES movie (id);

-- changeset denismalinin:1731784769684-33
ALTER TABLE movie_directors
    ADD CONSTRAINT fk_movdir_on_director_entity FOREIGN KEY (directors_id) REFERENCES director (id);

-- changeset denismalinin:1731784769684-34
ALTER TABLE movie_directors
    ADD CONSTRAINT fk_movdir_on_movie_entity FOREIGN KEY (movies_id) REFERENCES movie (id);

-- changeset denismalinin:1731784769684-35
ALTER TABLE movie_genres
    ADD CONSTRAINT fk_movgen_on_genre_entity FOREIGN KEY (genres_id) REFERENCES genre (id);

-- changeset denismalinin:1731784769684-36
ALTER TABLE movie_genres
    ADD CONSTRAINT fk_movgen_on_movie_entity FOREIGN KEY (movies_id) REFERENCES movie (id);

-- changeset denismalinin:1731784769684-37
ALTER TABLE movie_languages
    ADD CONSTRAINT fk_movlan_on_language_entity FOREIGN KEY (languages_id) REFERENCES language (id);

-- changeset denismalinin:1731784769684-38
ALTER TABLE movie_languages
    ADD CONSTRAINT fk_movlan_on_movie_entity FOREIGN KEY (movies_id) REFERENCES movie (id);

-- changeset denismalinin:1731784769684-39
ALTER TABLE movie_writers
    ADD CONSTRAINT fk_movwri_on_movie_entity FOREIGN KEY (movies_id) REFERENCES movie (id);

-- changeset denismalinin:1731784769684-40
ALTER TABLE movie_writers
    ADD CONSTRAINT fk_movwri_on_writer_entity FOREIGN KEY (writers_id) REFERENCES writer (id);

