CREATE TABLE public.Users
(
  id SERIAL PRIMARY KEY NOT NULL,
  name VARCHAR,
  last_name VARCHAR,
  password VARCHAR
);
CREATE UNIQUE INDEX Users_id_uindex ON public.Users (id);