-- liquibase formatted sql

-- changeset denismalinin:1734200846070-1
ALTER TABLE movie
    ALTER box_office DROP NOT NULL;

-- changeset denismalinin:1734200846070-2
ALTER TABLE movie
    ALTER seasons_count DROP NOT NULL;

-- changeset denismalinin:1734200846070-3
ALTER TABLE movie
    ALTER seasons_count DROP NOT NULL;

-- changeset denismalinin:1734200846070-4
ALTER TABLE movie
    ALTER duration DROP NOT NULL;

-- changeset denismalinin:1734200846070-5
ALTER TABLE movie
    ALTER poster_link DROP NOT NULL;

-- changeset denismalinin:1734200846070-6
ALTER TABLE movie
    ALTER imdb_rating DROP NOT NULL;

-- changeset denismalinin:1734200846070-7
ALTER TABLE movie
    ALTER rating DROP NOT NULL;

-- changeset denismalinin:1734200846070-8
ALTER TABLE movie
    ALTER description_id DROP NOT NULL;

-- changeset denismalinin:1734200846070-9
ALTER TABLE users
    ADD COLUMN language VARCHAR(64) NOT NULL default 'RUSSIAN';

-- changeset denismalinin:1734200846070-10
ALTER TABLE movie
    ALTER COLUMN imdb_rating TYPE NUMERIC(2, 1);