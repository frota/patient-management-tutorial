-- V1__schema.sql

CREATE TABLE IF NOT EXISTS public.users (
    id bigserial PRIMARY KEY,
    email varchar(255) UNIQUE NOT NULL,
    password varchar(80) NOT NULL,
    role varchar(50) NOT NULL);

INSERT INTO public.users (email, password, role)
    SELECT 'testuser@test.com'::varchar(255), '$2b$12$7hoRZfJrRKD2nIm2vHLs7OBETy.LWenXXMLKf99W8M4PUwO6KB7fu'::varchar(80), 'ADMIN'::varchar(50)
        WHERE NOT EXISTS (SELECT 1 FROM public.users WHERE email = 'testuser@test.com'); -- password = password123
