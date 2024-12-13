-- liquibase formatted sql

-- changeset denismalinin:1734129800656-13
ALTER TABLE movie
    ALTER COLUMN released TYPE date USING (released::date);

