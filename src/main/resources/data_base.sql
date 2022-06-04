-- Table: public.user_account

-- DROP TABLE public.user_account;

CREATE TABLE public.user_account
(
    id bigint NOT NULL,
    balance double precision,
    connected boolean,
    email character varying(255) COLLATE pg_catalog."default",
    first_name character varying(255) COLLATE pg_catalog."default",
    last_name character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default",
    role character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT user_account_pkey PRIMARY KEY (id),
    CONSTRAINT user_unique UNIQUE (email)
)

-- Table: public.buddy

-- DROP TABLE public.buddy;

CREATE TABLE public.buddy
(
    id bigint NOT NULL,
    id_buddy bigint NOT NULL,
    id_user bigint NOT NULL,
    CONSTRAINT buddy_pkey PRIMARY KEY (id),
    CONSTRAINT buddy_unique UNIQUE (id_user, id_buddy),
    CONSTRAINT fk93uav99qhaq73m8bpminh9nq2 FOREIGN KEY (id_user)
        REFERENCES public.user_account (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkdv5pgb4phcfehsgo381djw4jj FOREIGN KEY (id_buddy)
        REFERENCES public.user_account (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

-- Table: public.transaction

-- DROP TABLE public.transaction;

CREATE TABLE public.transaction
(
    id bigint NOT NULL,
    amount real,
    description character varying(255) COLLATE pg_catalog."default",
    payment_date timestamp without time zone,
    type_transaction character varying(255) COLLATE pg_catalog."default",
    id_credeted bigint NOT NULL,
    id_debited bigint,
    CONSTRAINT transaction_pkey PRIMARY KEY (id),
    CONSTRAINT fk1xaor9mycktp81rjo6a84ybh9 FOREIGN KEY (id_debited)
        REFERENCES public.user_account (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkdqg20g3r56w5qhbembjga8dah FOREIGN KEY (id_credeted)
        REFERENCES public.user_account (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

-- SEQUENCE: public.user_sequence

-- DROP SEQUENCE public.user_sequence;

CREATE SEQUENCE public.user_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

-- SEQUENCE: public.transaction_sequence

-- DROP SEQUENCE public.transaction_sequence;

CREATE SEQUENCE public.transaction_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

-- SEQUENCE: public.hibernate_sequence

-- DROP SEQUENCE public.hibernate_sequence;

CREATE SEQUENCE public.hibernate_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;