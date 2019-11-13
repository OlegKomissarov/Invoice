CREATE DATABASE invoice
    WITH
    OWNER = postgres
    ENCODING = 'UTF8';

CREATE SCHEMA invoice
    AUTHORIZATION postgres;

GRANT ALL ON SCHEMA invoice TO postgres;

GRANT ALL ON SCHEMA invoice TO PUBLIC;