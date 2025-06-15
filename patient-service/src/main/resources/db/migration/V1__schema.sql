-- V1__schema.sql

CREATE TABLE IF NOT EXISTS public.patient (
    id bigserial PRIMARY KEY,
    name varchar(80) NOT NULL,
    email varchar(255) UNIQUE NOT NULL,
    address varchar(255) NOT NULL,
    date_of_birth date NOT NULL,
    registered_date timestamp NOT NULL);

INSERT INTO public.patient (name, email, address, date_of_birth, registered_date) VALUES -- ids 1..15
    ('John Doe', 'john.doe@example.com', '123 Main St, Springfield', '1985-06-15', '2024-01-10'),
    ('Jane Smith', 'jane.smith@example.com', '456 Elm St, Shelbyville', '1990-09-23', '2023-12-01'),
    ('Alice Johnson', 'alice.johnson@example.com', '789 Oak St, Capital City', '1978-03-12', '2022-06-20'),
    ('Bob Brown', 'bob.brown@example.com', '321 Pine St, Springfield', '1982-11-30', '2023-05-14'),
    ('Emily Davis', 'emily.davis@example.com', '654 Maple St, Shelbyville', '1995-02-05', '2024-03-01'),
    ('Michael Green', 'michael.green@example.com', '987 Cedar St, Springfield', '1988-07-25', '2024-02-15'),
    ('Sarah Taylor', 'sarah.taylor@example.com', '123 Birch St, Shelbyville', '1992-04-18', '2023-08-25'),
    ('David Wilson', 'david.wilson@example.com', '456 Ash St, Capital City', '1975-01-11', '2022-10-10'),
    ('Laura White', 'laura.white@example.com', '789 Palm St, Springfield', '1989-09-02', '2024-04-20'),
    ('James Harris', 'james.harris@example.com', '321 Cherry St, Shelbyville', '1993-11-15', '2023-06-30'),
    ('Emma Moore', 'emma.moore@example.com', '654 Spruce St, Capital City', '1980-08-09', '2023-01-22'),
    ('Ethan Martinez', 'ethan.martinez@example.com', '987 Redwood St, Springfield', '1984-05-03', '2024-05-12'),
    ('Sophia Clark', 'sophia.clark@example.com', '123 Hickory St, Shelbyville', '1991-12-25', '2022-11-11'),
    ('Daniel Lewis', 'daniel.lewis@example.com', '456 Cypress St, Capital City', '1976-06-08', '2023-09-19'),
    ('Isabella Walker', 'isabella.walker@example.com', '789 Willow St, Springfield', '1987-10-17', '2024-03-29');
