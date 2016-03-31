--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.1
-- Dumped by pg_dump version 9.5.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: batches; Type: TABLE; Schema: public; Owner: diana
--

CREATE TABLE batches (
    id integer NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    expires_at date NOT NULL,
    identifier character varying(255) NOT NULL,
    manufactured_at date NOT NULL,
    quantity bigint NOT NULL,
    product_id integer NOT NULL,
    user_id integer NOT NULL,
    CONSTRAINT batches_quantity_check CHECK ((quantity >= 1))
);


ALTER TABLE batches OWNER TO diana;

--
-- Name: batches_aud; Type: TABLE; Schema: public; Owner: diana
--

CREATE TABLE batches_aud (
    id integer NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    expires_at date,
    identifier character varying(255),
    manufactured_at date,
    quantity bigint,
    product_id integer NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE batches_aud OWNER TO diana;

--
-- Name: batches_aud_id_seq; Type: SEQUENCE; Schema: public; Owner: diana
--

CREATE SEQUENCE batches_aud_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE batches_aud_id_seq OWNER TO diana;

--
-- Name: batches_aud_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: diana
--

ALTER SEQUENCE batches_aud_id_seq OWNED BY batches_aud.id;


--
-- Name: batches_aud_product_id_seq; Type: SEQUENCE; Schema: public; Owner: diana
--

CREATE SEQUENCE batches_aud_product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE batches_aud_product_id_seq OWNER TO diana;

--
-- Name: batches_aud_product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: diana
--

ALTER SEQUENCE batches_aud_product_id_seq OWNED BY batches_aud.product_id;


--
-- Name: batches_aud_user_id_seq; Type: SEQUENCE; Schema: public; Owner: diana
--

CREATE SEQUENCE batches_aud_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE batches_aud_user_id_seq OWNER TO diana;

--
-- Name: batches_aud_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: diana
--

ALTER SEQUENCE batches_aud_user_id_seq OWNED BY batches_aud.user_id;


--
-- Name: batches_id_seq; Type: SEQUENCE; Schema: public; Owner: diana
--

CREATE SEQUENCE batches_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE batches_id_seq OWNER TO diana;

--
-- Name: batches_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: diana
--

ALTER SEQUENCE batches_id_seq OWNED BY batches.id;


--
-- Name: batches_product_id_seq; Type: SEQUENCE; Schema: public; Owner: diana
--

CREATE SEQUENCE batches_product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE batches_product_id_seq OWNER TO diana;

--
-- Name: batches_product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: diana
--

ALTER SEQUENCE batches_product_id_seq OWNED BY batches.product_id;


--
-- Name: batches_user_id_seq; Type: SEQUENCE; Schema: public; Owner: diana
--

CREATE SEQUENCE batches_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE batches_user_id_seq OWNER TO diana;

--
-- Name: batches_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: diana
--

ALTER SEQUENCE batches_user_id_seq OWNED BY batches.user_id;


--
-- Name: categories; Type: TABLE; Schema: public; Owner: diana
--

CREATE TABLE categories (
    id integer NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    description text,
    name character varying(255) NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE categories OWNER TO diana;

--
-- Name: categories_aud; Type: TABLE; Schema: public; Owner: diana
--

CREATE TABLE categories_aud (
    id integer NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    description text,
    name character varying(255),
    user_id integer NOT NULL
);


ALTER TABLE categories_aud OWNER TO diana;

--
-- Name: categories_aud_id_seq; Type: SEQUENCE; Schema: public; Owner: diana
--

CREATE SEQUENCE categories_aud_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE categories_aud_id_seq OWNER TO diana;

--
-- Name: categories_aud_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: diana
--

ALTER SEQUENCE categories_aud_id_seq OWNED BY categories_aud.id;


--
-- Name: categories_aud_user_id_seq; Type: SEQUENCE; Schema: public; Owner: diana
--

CREATE SEQUENCE categories_aud_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE categories_aud_user_id_seq OWNER TO diana;

--
-- Name: categories_aud_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: diana
--

ALTER SEQUENCE categories_aud_user_id_seq OWNED BY categories_aud.user_id;


--
-- Name: categories_id_seq; Type: SEQUENCE; Schema: public; Owner: diana
--

CREATE SEQUENCE categories_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE categories_id_seq OWNER TO diana;

--
-- Name: categories_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: diana
--

ALTER SEQUENCE categories_id_seq OWNED BY categories.id;


--
-- Name: categories_user_id_seq; Type: SEQUENCE; Schema: public; Owner: diana
--

CREATE SEQUENCE categories_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE categories_user_id_seq OWNER TO diana;

--
-- Name: categories_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: diana
--

ALTER SEQUENCE categories_user_id_seq OWNED BY categories.user_id;


--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: diana
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO diana;

--
-- Name: products; Type: TABLE; Schema: public; Owner: diana
--

CREATE TABLE products (
    id integer NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    name character varying(255) NOT NULL,
    price numeric(16,2) NOT NULL,
    category_id integer NOT NULL,
    user_id integer NOT NULL,
    CONSTRAINT products_price_check CHECK ((price >= (0)::numeric))
);


ALTER TABLE products OWNER TO diana;

--
-- Name: products_aud; Type: TABLE; Schema: public; Owner: diana
--

CREATE TABLE products_aud (
    id integer NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    name character varying(255),
    price numeric(16,2),
    category_id integer NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE products_aud OWNER TO diana;

--
-- Name: products_aud_category_id_seq; Type: SEQUENCE; Schema: public; Owner: diana
--

CREATE SEQUENCE products_aud_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE products_aud_category_id_seq OWNER TO diana;

--
-- Name: products_aud_category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: diana
--

ALTER SEQUENCE products_aud_category_id_seq OWNED BY products_aud.category_id;


--
-- Name: products_aud_id_seq; Type: SEQUENCE; Schema: public; Owner: diana
--

CREATE SEQUENCE products_aud_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE products_aud_id_seq OWNER TO diana;

--
-- Name: products_aud_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: diana
--

ALTER SEQUENCE products_aud_id_seq OWNED BY products_aud.id;


--
-- Name: products_aud_user_id_seq; Type: SEQUENCE; Schema: public; Owner: diana
--

CREATE SEQUENCE products_aud_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE products_aud_user_id_seq OWNER TO diana;

--
-- Name: products_aud_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: diana
--

ALTER SEQUENCE products_aud_user_id_seq OWNED BY products_aud.user_id;


--
-- Name: products_category_id_seq; Type: SEQUENCE; Schema: public; Owner: diana
--

CREATE SEQUENCE products_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE products_category_id_seq OWNER TO diana;

--
-- Name: products_category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: diana
--

ALTER SEQUENCE products_category_id_seq OWNED BY products.category_id;


--
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: diana
--

CREATE SEQUENCE products_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE products_id_seq OWNER TO diana;

--
-- Name: products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: diana
--

ALTER SEQUENCE products_id_seq OWNED BY products.id;


--
-- Name: products_user_id_seq; Type: SEQUENCE; Schema: public; Owner: diana
--

CREATE SEQUENCE products_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE products_user_id_seq OWNER TO diana;

--
-- Name: products_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: diana
--

ALTER SEQUENCE products_user_id_seq OWNED BY products.user_id;


--
-- Name: revinfo; Type: TABLE; Schema: public; Owner: diana
--

CREATE TABLE revinfo (
    rev integer NOT NULL,
    revtstmp bigint
);


ALTER TABLE revinfo OWNER TO diana;

--
-- Name: users; Type: TABLE; Schema: public; Owner: diana
--

CREATE TABLE users (
    id integer NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    active boolean NOT NULL,
    email character varying(255) NOT NULL,
    full_name character varying(255) NOT NULL,
    password_digest character varying(255) NOT NULL,
    profile integer NOT NULL
);


ALTER TABLE users OWNER TO diana;

--
-- Name: users_aud; Type: TABLE; Schema: public; Owner: diana
--

CREATE TABLE users_aud (
    id integer NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    active boolean,
    email character varying(255),
    full_name character varying(255),
    password_digest character varying(255),
    profile integer
);


ALTER TABLE users_aud OWNER TO diana;

--
-- Name: users_aud_id_seq; Type: SEQUENCE; Schema: public; Owner: diana
--

CREATE SEQUENCE users_aud_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_aud_id_seq OWNER TO diana;

--
-- Name: users_aud_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: diana
--

ALTER SEQUENCE users_aud_id_seq OWNED BY users_aud.id;


--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: diana
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_id_seq OWNER TO diana;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: diana
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: diana
--

ALTER TABLE ONLY batches ALTER COLUMN id SET DEFAULT nextval('batches_id_seq'::regclass);


--
-- Name: product_id; Type: DEFAULT; Schema: public; Owner: diana
--

ALTER TABLE ONLY batches ALTER COLUMN product_id SET DEFAULT nextval('batches_product_id_seq'::regclass);


--
-- Name: user_id; Type: DEFAULT; Schema: public; Owner: diana
--

ALTER TABLE ONLY batches ALTER COLUMN user_id SET DEFAULT nextval('batches_user_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: diana
--

ALTER TABLE ONLY batches_aud ALTER COLUMN id SET DEFAULT nextval('batches_aud_id_seq'::regclass);


--
-- Name: product_id; Type: DEFAULT; Schema: public; Owner: diana
--

ALTER TABLE ONLY batches_aud ALTER COLUMN product_id SET DEFAULT nextval('batches_aud_product_id_seq'::regclass);


--
-- Name: user_id; Type: DEFAULT; Schema: public; Owner: diana
--

ALTER TABLE ONLY batches_aud ALTER COLUMN user_id SET DEFAULT nextval('batches_aud_user_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: diana
--

ALTER TABLE ONLY categories ALTER COLUMN id SET DEFAULT nextval('categories_id_seq'::regclass);


--
-- Name: user_id; Type: DEFAULT; Schema: public; Owner: diana
--

ALTER TABLE ONLY categories ALTER COLUMN user_id SET DEFAULT nextval('categories_user_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: diana
--

ALTER TABLE ONLY categories_aud ALTER COLUMN id SET DEFAULT nextval('categories_aud_id_seq'::regclass);


--
-- Name: user_id; Type: DEFAULT; Schema: public; Owner: diana
--

ALTER TABLE ONLY categories_aud ALTER COLUMN user_id SET DEFAULT nextval('categories_aud_user_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: diana
--

ALTER TABLE ONLY products ALTER COLUMN id SET DEFAULT nextval('products_id_seq'::regclass);


--
-- Name: category_id; Type: DEFAULT; Schema: public; Owner: diana
--

ALTER TABLE ONLY products ALTER COLUMN category_id SET DEFAULT nextval('products_category_id_seq'::regclass);


--
-- Name: user_id; Type: DEFAULT; Schema: public; Owner: diana
--

ALTER TABLE ONLY products ALTER COLUMN user_id SET DEFAULT nextval('products_user_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: diana
--

ALTER TABLE ONLY products_aud ALTER COLUMN id SET DEFAULT nextval('products_aud_id_seq'::regclass);


--
-- Name: category_id; Type: DEFAULT; Schema: public; Owner: diana
--

ALTER TABLE ONLY products_aud ALTER COLUMN category_id SET DEFAULT nextval('products_aud_category_id_seq'::regclass);


--
-- Name: user_id; Type: DEFAULT; Schema: public; Owner: diana
--

ALTER TABLE ONLY products_aud ALTER COLUMN user_id SET DEFAULT nextval('products_aud_user_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: diana
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: diana
--

ALTER TABLE ONLY users_aud ALTER COLUMN id SET DEFAULT nextval('users_aud_id_seq'::regclass);


--
-- Data for Name: batches; Type: TABLE DATA; Schema: public; Owner: diana
--

COPY batches (id, created_at, updated_at, expires_at, identifier, manufactured_at, quantity, product_id, user_id) FROM stdin;
\.


--
-- Data for Name: batches_aud; Type: TABLE DATA; Schema: public; Owner: diana
--

COPY batches_aud (id, rev, revtype, expires_at, identifier, manufactured_at, quantity, product_id, user_id) FROM stdin;
\.


--
-- Name: batches_aud_id_seq; Type: SEQUENCE SET; Schema: public; Owner: diana
--

SELECT pg_catalog.setval('batches_aud_id_seq', 1, false);


--
-- Name: batches_aud_product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: diana
--

SELECT pg_catalog.setval('batches_aud_product_id_seq', 1, false);


--
-- Name: batches_aud_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: diana
--

SELECT pg_catalog.setval('batches_aud_user_id_seq', 1, false);


--
-- Name: batches_id_seq; Type: SEQUENCE SET; Schema: public; Owner: diana
--

SELECT pg_catalog.setval('batches_id_seq', 1, false);


--
-- Name: batches_product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: diana
--

SELECT pg_catalog.setval('batches_product_id_seq', 1, false);


--
-- Name: batches_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: diana
--

SELECT pg_catalog.setval('batches_user_id_seq', 1, false);


--
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: diana
--

COPY categories (id, created_at, updated_at, description, name, user_id) FROM stdin;
\.


--
-- Data for Name: categories_aud; Type: TABLE DATA; Schema: public; Owner: diana
--

COPY categories_aud (id, rev, revtype, description, name, user_id) FROM stdin;
\.


--
-- Name: categories_aud_id_seq; Type: SEQUENCE SET; Schema: public; Owner: diana
--

SELECT pg_catalog.setval('categories_aud_id_seq', 1, false);


--
-- Name: categories_aud_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: diana
--

SELECT pg_catalog.setval('categories_aud_user_id_seq', 1, false);


--
-- Name: categories_id_seq; Type: SEQUENCE SET; Schema: public; Owner: diana
--

SELECT pg_catalog.setval('categories_id_seq', 1, false);


--
-- Name: categories_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: diana
--

SELECT pg_catalog.setval('categories_user_id_seq', 1, false);


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: diana
--

SELECT pg_catalog.setval('hibernate_sequence', 1, false);


--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: diana
--

COPY products (id, created_at, updated_at, name, price, category_id, user_id) FROM stdin;
\.


--
-- Data for Name: products_aud; Type: TABLE DATA; Schema: public; Owner: diana
--

COPY products_aud (id, rev, revtype, name, price, category_id, user_id) FROM stdin;
\.


--
-- Name: products_aud_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: diana
--

SELECT pg_catalog.setval('products_aud_category_id_seq', 1, false);


--
-- Name: products_aud_id_seq; Type: SEQUENCE SET; Schema: public; Owner: diana
--

SELECT pg_catalog.setval('products_aud_id_seq', 1, false);


--
-- Name: products_aud_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: diana
--

SELECT pg_catalog.setval('products_aud_user_id_seq', 1, false);


--
-- Name: products_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: diana
--

SELECT pg_catalog.setval('products_category_id_seq', 1, false);


--
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: diana
--

SELECT pg_catalog.setval('products_id_seq', 1, false);


--
-- Name: products_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: diana
--

SELECT pg_catalog.setval('products_user_id_seq', 1, false);


--
-- Data for Name: revinfo; Type: TABLE DATA; Schema: public; Owner: diana
--

COPY revinfo (rev, revtstmp) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: diana
--

COPY users (id, created_at, updated_at, active, email, full_name, password_digest, profile) FROM stdin;
\.


--
-- Data for Name: users_aud; Type: TABLE DATA; Schema: public; Owner: diana
--

COPY users_aud (id, rev, revtype, active, email, full_name, password_digest, profile) FROM stdin;
\.


--
-- Name: users_aud_id_seq; Type: SEQUENCE SET; Schema: public; Owner: diana
--

SELECT pg_catalog.setval('users_aud_id_seq', 1, false);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: diana
--

SELECT pg_catalog.setval('users_id_seq', 1, false);


--
-- Name: batches_aud_pkey; Type: CONSTRAINT; Schema: public; Owner: diana
--

ALTER TABLE ONLY batches_aud
    ADD CONSTRAINT batches_aud_pkey PRIMARY KEY (id, rev);


--
-- Name: batches_pkey; Type: CONSTRAINT; Schema: public; Owner: diana
--

ALTER TABLE ONLY batches
    ADD CONSTRAINT batches_pkey PRIMARY KEY (id);


--
-- Name: categories_aud_pkey; Type: CONSTRAINT; Schema: public; Owner: diana
--

ALTER TABLE ONLY categories_aud
    ADD CONSTRAINT categories_aud_pkey PRIMARY KEY (id, rev);


--
-- Name: categories_pkey; Type: CONSTRAINT; Schema: public; Owner: diana
--

ALTER TABLE ONLY categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (id);


--
-- Name: products_aud_pkey; Type: CONSTRAINT; Schema: public; Owner: diana
--

ALTER TABLE ONLY products_aud
    ADD CONSTRAINT products_aud_pkey PRIMARY KEY (id, rev);


--
-- Name: products_pkey; Type: CONSTRAINT; Schema: public; Owner: diana
--

ALTER TABLE ONLY products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- Name: revinfo_pkey; Type: CONSTRAINT; Schema: public; Owner: diana
--

ALTER TABLE ONLY revinfo
    ADD CONSTRAINT revinfo_pkey PRIMARY KEY (rev);


--
-- Name: uk_6dotkott2kjsp8vw4d0m25fb7; Type: CONSTRAINT; Schema: public; Owner: diana
--

ALTER TABLE ONLY users
    ADD CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);


--
-- Name: uk_o61fmio5yukmmiqgnxf8pnavn; Type: CONSTRAINT; Schema: public; Owner: diana
--

ALTER TABLE ONLY products
    ADD CONSTRAINT uk_o61fmio5yukmmiqgnxf8pnavn UNIQUE (name);


--
-- Name: uk_t8o6pivur7nn124jehx7cygw5; Type: CONSTRAINT; Schema: public; Owner: diana
--

ALTER TABLE ONLY categories
    ADD CONSTRAINT uk_t8o6pivur7nn124jehx7cygw5 UNIQUE (name);


--
-- Name: users_aud_pkey; Type: CONSTRAINT; Schema: public; Owner: diana
--

ALTER TABLE ONLY users_aud
    ADD CONSTRAINT users_aud_pkey PRIMARY KEY (id, rev);


--
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: diana
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: fk6k06kmh1y6nl11msapwqdabl7; Type: FK CONSTRAINT; Schema: public; Owner: diana
--

ALTER TABLE ONLY batches
    ADD CONSTRAINT fk6k06kmh1y6nl11msapwqdabl7 FOREIGN KEY (user_id) REFERENCES users(id);


--
-- Name: fk6tl8f0jwdqkxgr4702msumd4t; Type: FK CONSTRAINT; Schema: public; Owner: diana
--

ALTER TABLE ONLY products_aud
    ADD CONSTRAINT fk6tl8f0jwdqkxgr4702msumd4t FOREIGN KEY (rev) REFERENCES revinfo(rev);


--
-- Name: fkdb050tk37qryv15hd932626th; Type: FK CONSTRAINT; Schema: public; Owner: diana
--

ALTER TABLE ONLY products
    ADD CONSTRAINT fkdb050tk37qryv15hd932626th FOREIGN KEY (user_id) REFERENCES users(id);


--
-- Name: fke0jgkdy8dtdsnygqls5ae2df3; Type: FK CONSTRAINT; Schema: public; Owner: diana
--

ALTER TABLE ONLY categories_aud
    ADD CONSTRAINT fke0jgkdy8dtdsnygqls5ae2df3 FOREIGN KEY (rev) REFERENCES revinfo(rev);


--
-- Name: fkghuylkwuedgl2qahxjt8g41kb; Type: FK CONSTRAINT; Schema: public; Owner: diana
--

ALTER TABLE ONLY categories
    ADD CONSTRAINT fkghuylkwuedgl2qahxjt8g41kb FOREIGN KEY (user_id) REFERENCES users(id);


--
-- Name: fkhr5xfg8csgde4ns0pqxtmqsok; Type: FK CONSTRAINT; Schema: public; Owner: diana
--

ALTER TABLE ONLY batches_aud
    ADD CONSTRAINT fkhr5xfg8csgde4ns0pqxtmqsok FOREIGN KEY (rev) REFERENCES revinfo(rev);


--
-- Name: fkinrdywgyurfk2ojrfkard4ejn; Type: FK CONSTRAINT; Schema: public; Owner: diana
--

ALTER TABLE ONLY users_aud
    ADD CONSTRAINT fkinrdywgyurfk2ojrfkard4ejn FOREIGN KEY (rev) REFERENCES revinfo(rev);


--
-- Name: fkjb38v1mk479a6t6ay2mewo03m; Type: FK CONSTRAINT; Schema: public; Owner: diana
--

ALTER TABLE ONLY batches
    ADD CONSTRAINT fkjb38v1mk479a6t6ay2mewo03m FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE;


--
-- Name: fkog2rp4qthbtt2lfyhfo32lsw9; Type: FK CONSTRAINT; Schema: public; Owner: diana
--

ALTER TABLE ONLY products
    ADD CONSTRAINT fkog2rp4qthbtt2lfyhfo32lsw9 FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

