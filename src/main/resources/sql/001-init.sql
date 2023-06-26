DROP SCHEMA IF EXISTS todoapp CASCADE;
CREATE SCHEMA todoapp;
CREATE EXTENSION IF NOT EXISTS unaccent;

CREATE TABLE todoapp.scripts
(
    filename character varying(255) NOT NULL,
    passed   timestamp without time zone NOT NULL DEFAULT now(),
    CONSTRAINT scripts_pkey PRIMARY KEY (filename)
);

CREATE TABLE todoapp.todo_item
(
    id      bigserial,
    is_done boolean NOT NULL DEFAULT false,
    label   text
);