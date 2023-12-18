CREATE TABLE public.jobs(
    id UUID PRIMARY KEY UNIQUE NOT NULL,
    name TEXT NOT NULL,
    description TEXT,
    active BOOLEAN NOT NULL
);

CREATE TABLE public.users(
    id UUID PRIMARY KEY UNIQUE NOT NULL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    date_of_birth DATE NOT NULL,
    phone_number TEXT NOT NULL,
    ssn Integer NOT NULL UNIQUE,
    nif Integer NOT NULL UNIQUE,
    login TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    address TEXT NOT NULL,
    zip_code TEXT NOT NULL,
    city TEXT NOT NULL,
    state TEXT NOT NULL,
    role TEXT NOT NULL,
    active BOOLEAN NOT NULL,
    job_id UUID REFERENCES public.jobs(id)
)

