DROP SEQUENCE public.human_id_seq;

CREATE SEQUENCE public.human_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.human_id_seq
  OWNER TO highgo;
  
DROP TABLE public.human;

CREATE TABLE public.human
(
  id integer NOT NULL DEFAULT nextval('human_id_seq'::regclass),
  name character varying,
  sex character(1)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.human
  OWNER TO highgo;