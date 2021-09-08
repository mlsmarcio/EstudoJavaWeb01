CREATE DATABASE "curso-jsp"
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Portuguese_Brazil.1252'
       LC_CTYPE = 'Portuguese_Brazil.1252'
       CONNECTION LIMIT = -1;


CREATE SEQUENCE public.serialuser
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 109
  CACHE 1;
ALTER TABLE public.serialuser
  OWNER TO postgres;

CREATE TABLE public.usuario
(
  login character varying,
  senha character varying,
  id bigint NOT NULL DEFAULT nextval('serialuser'::regclass),
  nome character varying
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.usuario
  OWNER TO postgres;
  
  
ALTER TABLE public.usuario ADD COLUMN telefone character varying(15);

-------------------------------------------------
-------------- TELEFONES ------------------------

CREATE SEQUENCE fonesequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE fonesequence
  OWNER TO postgres;

CREATE TABLE telefone
(
	id bigint NOT NULL DEFAULT nextval('fonesequence'::regclass),
	numero character varying(15),
	tipo character varying(15),
	usuario bigint NOT NULL,
	CONSTRAINT fone_pkey PRIMARY KEY (id)
)
WITH (
	OIDS=FALSE
);
ALTER TABLE telefone
OWNER TO postgres;

