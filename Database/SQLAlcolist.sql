--
-- PostgreSQL database dump
--

-- Dumped from database version 15.0
-- Dumped by pg_dump version 15.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: cocktail; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cocktail (
    id bigint NOT NULL,
    description character varying(500),
    flavour character varying(50),
    inmenu boolean,
    isalcoholic boolean,
    isiba boolean,
    name character varying(50),
    pathfileimg character varying(255),
    price double precision,
    sold integer,
    uuid character varying(50) NOT NULL
);


ALTER TABLE public.cocktail OWNER TO postgres;

--
-- Name: cocktail_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cocktail_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cocktail_id_seq OWNER TO postgres;

--
-- Name: cocktail_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cocktail_id_seq OWNED BY public.cocktail.id;


--
-- Name: ingredient; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ingredient (
    id bigint NOT NULL,
    is_optional boolean NOT NULL,
    is_present boolean NOT NULL,
    quantity integer,
    uuid character varying(50) NOT NULL,
    cocktail bigint NOT NULL,
    product bigint NOT NULL
);


ALTER TABLE public.ingredient OWNER TO postgres;

--
-- Name: ingredient_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ingredient_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ingredient_id_seq OWNER TO postgres;

--
-- Name: ingredient_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ingredient_id_seq OWNED BY public.ingredient.id;


--
-- Name: message; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.message (
    id bigint NOT NULL,
    creation_date timestamp without time zone,
    note character varying(50),
    ordination_id bigint,
    user_id bigint
);


ALTER TABLE public.message OWNER TO postgres;

--
-- Name: message_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.message_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.message_id_seq OWNER TO postgres;

--
-- Name: message_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.message_id_seq OWNED BY public.message.id;


--
-- Name: ordered_cocktail; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ordered_cocktail (
    id bigint NOT NULL,
    quantity integer,
    cocktail bigint NOT NULL,
    ordination bigint NOT NULL
);


ALTER TABLE public.ordered_cocktail OWNER TO postgres;

--
-- Name: ordered_cocktail_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ordered_cocktail_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ordered_cocktail_id_seq OWNER TO postgres;

--
-- Name: ordered_cocktail_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ordered_cocktail_id_seq OWNED BY public.ordered_cocktail.id;


--
-- Name: ordination; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ordination (
    id bigint NOT NULL,
    date_creation timestamp without time zone,
    date_last_modified timestamp without time zone,
    numbers_of_cocktails integer,
    status integer,
    total double precision,
    uuid character varying(50) NOT NULL,
    created_by bigint NOT NULL,
    delivered_by bigint,
    executed_by bigint,
    id_tables bigint NOT NULL
);


ALTER TABLE public.ordination OWNER TO postgres;

--
-- Name: ordination_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ordination_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ordination_id_seq OWNER TO postgres;

--
-- Name: ordination_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ordination_id_seq OWNED BY public.ordination.id;


--
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id bigint NOT NULL,
    alcoholic_percentage double precision,
    category character varying(50),
    is_present boolean,
    ml integer,
    name character varying(50),
    pathfileimg character varying(255),
    uuid character varying(50) NOT NULL
);


ALTER TABLE public.product OWNER TO postgres;

--
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_id_seq OWNER TO postgres;

--
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;


--
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    id bigint NOT NULL,
    name character varying(50) NOT NULL
);


ALTER TABLE public.role OWNER TO postgres;

--
-- Name: role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_id_seq OWNER TO postgres;

--
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.role_id_seq OWNED BY public.role.id;


--
-- Name: tables; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tables (
    id bigint NOT NULL,
    isfree boolean,
    number integer,
    uuid character varying(50)
);


ALTER TABLE public.tables OWNER TO postgres;

--
-- Name: tables_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tables_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tables_id_seq OWNER TO postgres;

--
-- Name: tables_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tables_id_seq OWNED BY public.tables.id;


--
-- Name: user_account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_account (
    id bigint NOT NULL,
    email character varying(50) NOT NULL,
    main_role character varying(64) NOT NULL,
    name character varying(50) NOT NULL,
    password character varying(64) NOT NULL,
    surname character varying(50) NOT NULL,
    uuid character varying(50) NOT NULL
);


ALTER TABLE public.user_account OWNER TO postgres;

--
-- Name: user_account_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_account_id_seq OWNER TO postgres;

--
-- Name: user_account_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_account_id_seq OWNED BY public.user_account.id;


--
-- Name: user_account_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_account_roles (
    user_account_id bigint NOT NULL,
    roles_id bigint NOT NULL
);


ALTER TABLE public.user_account_roles OWNER TO postgres;

--
-- Name: cocktail id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cocktail ALTER COLUMN id SET DEFAULT nextval('public.cocktail_id_seq'::regclass);


--
-- Name: ingredient id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingredient ALTER COLUMN id SET DEFAULT nextval('public.ingredient_id_seq'::regclass);


--
-- Name: message id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message ALTER COLUMN id SET DEFAULT nextval('public.message_id_seq'::regclass);


--
-- Name: ordered_cocktail id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordered_cocktail ALTER COLUMN id SET DEFAULT nextval('public.ordered_cocktail_id_seq'::regclass);


--
-- Name: ordination id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordination ALTER COLUMN id SET DEFAULT nextval('public.ordination_id_seq'::regclass);


--
-- Name: product id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);


--
-- Name: role id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role ALTER COLUMN id SET DEFAULT nextval('public.role_id_seq'::regclass);


--
-- Name: tables id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tables ALTER COLUMN id SET DEFAULT nextval('public.tables_id_seq'::regclass);


--
-- Name: user_account id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_account ALTER COLUMN id SET DEFAULT nextval('public.user_account_id_seq'::regclass);


--
-- Data for Name: cocktail; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cocktail (id, description, flavour, inmenu, isalcoholic, isiba, name, pathfileimg, price, sold, uuid) FROM stdin;
4	Descrizione Tequila sour	Dolce	t	t	t	Tequila Sour	https://bpour.com/wp-content/uploads/2015/12/Kamikaze.gif	12.5	2	964f23e1-f435-40f8-b999-8cb197edd4c3
12	Amaro del capo è un liquore di erbe frutto di un’antica ricetta Calabrese. Il vecchio Amaro del Capo racchiude in sé i principi attivi di ben 29 Erbe benefiche, servito ad una temperatura di -20 è un ottimo digestivo, grazie anche ai suoi ingredienti principali: Arancia amara, Ginepro, Liquirizia, Miele.	Amaro	t	t	f	Amaro Del Capo	https://www.amaromediterraneo.it/wp-content/uploads/2018/01/bicchierino-3.png	2.5	0	ab500ecd-38d3-4492-a338-133925887fef
37	Descrizione Amen Corner	Dolce	f	t	t	Amen Corner	https://cocktailpartyapp.com/wp-content/uploads/Amen-Corner.webp	13.75	0	ee69744d-0c3b-45b9-97eb-89bc48d0ab74
13	Il Petrus Boonekamp è un amaro di origine olandese, creato nel 1777 a Leidschendam dal liquorista Petrus Boonekamp, la cui esistenza è dimostrata da fonti locali.	Amaro	t	t	f	Amaro Petrus	https://www.petrusbk.com/assets/img/petrus-glass-light.png?v=1.1	7.5	2	8e67b1ed-c931-45eb-a3ee-2b45a1a38eb1
1	Descrizione americano	Dolce	f	t	t	Americano	http://1.bp.blogspot.com/-jS42uQNzSfo/Tw-JjXZAsrI/AAAAAAAAB2I/zoPAtLec3Ws/s1600/negroni.png	10	10	02c6659a-8ae0-11ed-a1eb-0242ac120002
2	Descrizione negroni	Amaro	t	t	t	Negroni	https://www.camparino.com/wp-content/uploads/2022/04/cocktail-americano-milano-centro-camparino_3.png	5.5	9	09a7ae50-8ae0-11ed-a1eb-0242ac120002
38	Odore di frutti di bosco, amaro quanto basta	Dolce	t	f	f	Passione di frutta	https://www.malfygin.com/wp-content/uploads/2022/02/BRAMBLE-COCKTAIL-e1644403939858.png	7	0	143cb154-d7aa-4b99-9096-02340323cc07
39	Descrizione esotic	Aspro	t	t	f	Esotic	https://www.malfygin.com/wp-content/uploads/2020/06/15-DOCE-VITA-SPRITZ.png	19	0	f0f9332a-55e4-4c2e-8eb0-662870f35062
10	Moët Impérial è lo champagne iconico della Maison. Creato nel 1869, incarna lo stile unico di Moët & Chandon, uno stile che si distingue per il suo fruttato brillante, il suo palato seducente e la sua elegante maturità.	Secco	f	t	f	Calice di Prosecco	https://www.mineospizza.it/wp-content/uploads/2022/05/prosecco.png	3	3	574f8f40-d47e-43bf-aa36-c879055ae315
9	Descrizione del vino bianco	Amaro	f	t	f	Calice di Vino Bianco	https://www.vinopuro.com/img/bicc/Vino_Bianco_Frizzante_Calice_Medio.png	7	15	958bed1d-8915-4049-ab55-9c52d13621e9
34	Descrizione calice di vino rosso	Aspro	t	t	f	Calice di Vino Rosso	https://d2j6dbq0eux0bg.cloudfront.net/images/17725047/1126064948.jpg	5	0	093d58f2-4022-45fb-aa14-bf5c59556455
3	Descrizione gin tonic	Amaro	t	t	t	Gin Tonic	https://www.brunovanzan.com/wp-content/uploads/2020/01/gin-tonic.png	6.5	1	14648296-8ae0-11ed-a1eb-0242ac120002
5	Descrizione new york sour	Dolce	t	t	t	New York Sour	https://assets.bacardicontenthub.com/m/60a2f3f630f7a53/Medium_1000_PNG-AllMarkets_Angel-sEnvy_Silhouette_Global_WhiskeySour_SST.png	9.5	0	41d399c1-b5db-4286-bf50-195bd621439a
7	Il Moscow mule è un cocktail dal sapore pungente, fresco e dissetante: perfetto per le serate estive è semplice da preparare anche a casa. Ci occorrono solo 3 ingredienti: vodka, succo di lime e ginger beer. Il gusto particolare di questo drink è dato proprio dal mix di lime e zenzero.	Aspro	t	t	t	Moscow Mule	https://s3.amazonaws.com/newamsterdamspirits.com/mooseassets2/s3fs-public/NEWAM_Mule_Lemon_0.png?aI_KH9tSfRlmhSq6FcEf3QyXYBiVO8DH	7.5	9	a1b7bc72-a16c-4a2b-b213-62ff1fd4c848
6	Il Tequila Sunrise è un cocktail alcolico internazionale riconosciuto ufficialmente dalla International Bartenders Association a base di Tequila.	Dolce	t	t	t	Tequila Sunrise	http://arrieta32.com/wp-content/uploads/2020/09/COCKTAILS-02.png	7.5	1	42b5ce82-bf79-4cbe-9084-fcd1e0a46e25
35	Descrizione angelo azzurro	Amaro	t	t	t	Angelo azzurro	https://images.cocktailflow.com/v1/cocktail/w_300,h_540/cocktail_angelo_azzurro-1.png	12.5	0	88276e08-40e2-436f-afbe-687e6f79c625
36	Descrizione homemade whiskey sour	Secco	t	t	t	Homemade whiskey sour	https://cocktailpartyapp.com/wp-content/uploads/John-Collins.png	17	0	dd59b1c8-8a33-49b6-ad70-fd4e641af8c8
\.


--
-- Data for Name: ingredient; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ingredient (id, is_optional, is_present, quantity, uuid, cocktail, product) FROM stdin;
5	f	t	30	41d339c1-b5db-4286-bf50-195bd621439a	2	2
14	f	t	2	maria231-8915-4049-ab55-9c12d13621e9	5	29
15	f	t	30	mario214-d47e-43bf-aa36-c873055ae315	5	26
16	f	t	15	melania1-d47e-43bf-aa36-c873095ae315	5	17
17	f	t	30	melany31-d47e-43bf-aa36-c873095ae315	5	27
22	f	t	15	958bed1d-8915-4049-ab55-9c52d13621e9	7	26
23	f	t	10	574f8f40-d47e-43bf-aa36-c879055ae315	7	33
26	f	f	250	8e67b1ed-c931-45eb-a3ee-2b45a1a38eb1	10	20
71	f	t	\N	d89c3b3c-158f-4ca0-b571-892b1aac859d	39	33
13	f	f	60	fede1245-4a65-4676-a944-a7s910c491f8	5	28
2	f	f	30	09a7ae50-8ae0-11ed-a1eb-0242ac620002	1	7
72	f	t	40	3793343a-3f65-4229-95e2-a1720ec2ba38	39	23
73	f	t	\N	88884a8b-0978-4154-a044-e7aebd5ac282	39	25
74	f	t	\N	79ec2e08-ea05-4b35-a227-4c8f858dbf0f	39	26
19	f	t	1	ccccccc1-d47e-43bf-aa36-c873095ae315	6	24
20	f	t	1	bababab1-d47e-43bf-aa36-c873095ae315	6	31
18	f	t	1	aaaaa214-d47e-43bf-aa36-c873055ae315	6	30
6	t	f	30	4215ce82-bf79-4cbe-9084-fcd1e0a46e25	2	7
21	f	f	45	2f871784-4a65-4676-a944-a7b910c491f8	7	32
30	f	f	33	57c8743b-7854-40d2-9a42-97d5eee5640a	1	32
1	f	f	30	01c6659a-8ae0-11ed-a1eb-0242ac120002	1	10
28	f	t	10	1ertb1ed-c931-45eb-a3ee-2b45a1a38eb1	12	4
37	f	t	10	9193b7c9-fa99-4962-bb5a-19afc51dcee0	12	1
38	f	t	10	658c0fdd-077d-4ace-b915-22965c6c53e1	12	2
39	f	t	10	352a4677-f945-42a3-bf03-aabf6faf73ad	12	3
40	f	t	10	767c7cb0-2301-4a0b-aa7f-55b5e693b23c	12	5
41	f	t	50	924a75ec-d808-4e9d-8bcd-0f1b7cdda0db	12	6
42	f	t	40	a7ce0108-ac9d-4dd5-8870-7abefb508293	12	15
45	f	t	100	becbb08d-9c87-4d38-859f-05e8b17cea4e	4	30
46	f	t	\N	3c472b66-f8cf-47da-935b-a16d14461698	7	29
48	f	t	\N	b0c7edb0-010e-4743-90be-c78e6bfead36	7	11
47	f	t	19	26a29773-7cd9-469a-a7e7-88298306453f	7	31
25	f	f	50	ab500ecd-38d3-4492-a338-133925887fef	9	19
4	f	f	30	964f25e1-f435-40f8-b999-8cb197edd4c3	2	10
9	f	t	200	2e871784-4a65-4676-a944-a7s910c491f8	4	23
11	f	t	0	57ef8f40-d47e-43bf-aa36-c873055ae315	4	26
10	f	t	0	918bed1d-8915-4049-ab55-9c12d13621e9	4	25
12	f	t	0	12jh3h43-d47e-43bf-aa36-c873095ae315	4	27
29	f	t	75	7poib1ed-c931-45eb-a3ee-2b45a1a38eb1	13	5
52	f	t	300	44917ae7-8c5f-40de-935a-979c3451e7df	3	1
53	t	t	\N	8a958e17-d222-40de-9ec8-26631dce5f8d	3	26
54	t	t	20	5615c609-6f90-422a-8a51-18285dfef775	3	2
55	f	t	250	187f9acd-3f3d-4151-a102-b5729d1ebb8d	34	18
56	f	t	30	8dd4649f-ad8f-4216-9972-244a71465be6	35	1
57	f	t	60	06753369-51db-414c-9f73-614a5535b410	35	4
58	f	t	\N	9f9b0521-d554-4bb3-835d-6bd49791606c	35	29
59	t	t	\N	b9cdf93e-60ef-4cba-b074-5ec326b9c5d0	35	33
60	f	t	300	722aa8d2-cada-42b6-854f-6857f2ecec7f	36	28
61	t	t	20	3e4b90ea-29e1-46bd-808f-b3ab255e2639	36	24
62	f	t	0	e1c71c1d-5ee0-4618-8192-4af68979cb09	36	22
63	f	t	30	4d2d2c60-cc19-4dac-838c-e02cbc740a66	37	1
64	t	t	9	191a751b-5be8-4a8c-96ad-33fcaa183c4d	37	24
65	f	t	200	ab9fcd82-26ea-4e22-b160-7b7095078157	38	16
66	f	t	\N	098254f0-3333-4d66-87c2-614770b49edc	38	30
67	f	t	\N	fff4f395-2ff3-4b7a-93cd-58b718e2bf52	38	26
68	f	t	\N	fdcd4f24-adf3-4506-9cbc-8e8934f5c71a	38	31
69	f	t	200	9e26f1ed-7e6f-4f31-8912-84438471c4d6	39	6
70	f	t	300	5358d963-835a-435b-bed1-8c00be370555	39	21
\.


--
-- Data for Name: message; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.message (id, creation_date, note, ordination_id, user_id) FROM stdin;
1	2023-01-16 13:04:09.239	status  Creata -> In Attesa	1	3
2	2023-01-16 13:07:03.213	status  In Attesa -> In Elaborazione	1	3
3	2023-01-16 13:07:17.227	status  In Elaborazione -> In Attesa	1	3
4	2023-01-16 13:08:22.216	status  Creata -> In Attesa	2	3
5	2023-01-16 13:11:04.928	status  In Attesa -> In Elaborazione	1	3
6	2023-01-16 13:11:12.533	status  In Elaborazione -> Rinviata	1	3
7	2023-01-16 13:11:25.841	status  Rinviata -> In Attesa	1	3
8	2023-01-25 12:02:22.296	status  Creata -> In Attesa	4	5
9	2023-01-25 12:04:23.118	status  Creata -> In Attesa	5	5
10	2023-01-25 12:17:38.409	status  In Attesa -> In Elaborazione	1	7
11	2023-01-25 12:17:42.075	status  In Elaborazione -> Da consegnare	1	7
12	2023-01-25 12:17:43.681	status  In Attesa -> In Elaborazione	2	7
13	2023-01-25 12:17:44.316	status  In Elaborazione -> Da consegnare	2	7
14	2023-01-25 12:17:45.05	status  In Attesa -> In Elaborazione	4	7
15	2023-01-25 12:17:45.598	status  In Elaborazione -> Da consegnare	4	7
16	2023-01-25 12:17:46.075	status  In Attesa -> In Elaborazione	5	7
17	2023-01-25 12:17:58.334	status  In Elaborazione -> Rinviata	5	7
18	2023-01-25 12:25:03.031	status  Rinviata -> In Attesa	5	1
19	2023-01-25 12:25:21.933	status  Da consegnare -> Consegnata	4	1
20	2023-01-25 12:25:24.931	status  Consegnata -> Terminata	4	1
21	2023-01-25 17:08:09.302	status  In Attesa -> In Elaborazione	5	1
22	2023-01-25 17:08:17.13	status  In Elaborazione -> Da consegnare	5	1
23	2023-01-25 18:54:19.629	status  Creata -> In Attesa	6	1
24	2023-01-25 19:01:21.973	status  Creata -> In Attesa	7	1
25	2023-01-25 19:10:50.034	status  Creata -> In Attesa	8	1
26	2023-01-25 19:14:16.638	status  Creata -> In Attesa	9	1
27	2023-01-25 19:14:37.4	status  Creata -> In Attesa	3	1
28	2023-01-25 19:15:03.11	status  Creata -> In Attesa	10	1
29	2023-01-25 19:15:10.255	status  Creata -> In Attesa	11	1
30	2023-01-25 19:15:13.549	status  Creata -> In Attesa	12	1
31	2023-01-25 19:15:17.425	status  Creata -> In Attesa	13	1
32	2023-01-26 11:50:40.056	status  In Attesa -> In Elaborazione	10	1
33	2023-01-26 12:01:29.489	status  In Elaborazione -> Rinviata	10	3
34	2023-01-26 12:01:37.406	status  In Attesa -> In Elaborazione	11	3
35	2023-01-26 12:01:41.363	status  In Elaborazione -> Rinviata	11	3
36	2023-01-26 12:01:43.319	status  In Attesa -> In Elaborazione	12	3
37	2023-01-26 12:01:48.896	status  In Elaborazione -> Da consegnare	12	3
38	2023-01-26 12:01:50.054	status  In Attesa -> In Elaborazione	13	3
39	2023-01-26 12:01:50.7	status  In Elaborazione -> Da consegnare	13	3
40	2023-01-26 12:01:52.932	status  In Attesa -> In Elaborazione	3	3
41	2023-01-26 12:01:56.237	status  In Elaborazione -> Rinviata	3	3
42	2023-01-26 12:02:02.934	status  Rinviata -> In Attesa	10	3
43	2023-01-26 12:02:09.634	status  Rinviata -> In Attesa	11	3
44	2023-01-26 12:02:14.717	status  In Attesa -> In Elaborazione	10	3
45	2023-01-26 12:02:18.462	status  In Elaborazione -> Rinviata	10	3
46	2023-01-26 12:02:19.234	status  In Attesa -> In Elaborazione	11	3
47	2023-01-26 12:02:19.817	status  In Elaborazione -> Da consegnare	11	3
48	2023-01-26 12:02:20.67	status  In Attesa -> In Elaborazione	6	3
49	2023-01-26 12:02:21.286	status  In Elaborazione -> Da consegnare	6	3
50	2023-01-26 12:02:21.818	status  In Attesa -> In Elaborazione	7	3
51	2023-01-26 12:02:22.414	status  In Elaborazione -> Da consegnare	7	3
52	2023-01-26 12:02:23.082	status  In Attesa -> In Elaborazione	8	3
53	2023-01-26 12:02:23.73	status  In Elaborazione -> Da consegnare	8	3
54	2023-01-26 12:02:24.317	status  In Attesa -> In Elaborazione	9	3
55	2023-01-26 12:02:25.924	status  In Elaborazione -> Da consegnare	9	3
56	2023-01-26 12:02:37.518	status  Rinviata -> In Attesa	3	3
57	2023-01-26 12:02:39.913	status  Rinviata -> In Attesa	10	3
58	2023-01-26 12:02:54.653	status  In Attesa -> In Elaborazione	3	3
59	2023-01-26 12:03:00.712	status  In Elaborazione -> Da consegnare	3	3
60	2023-01-26 12:03:01.55	status  In Attesa -> In Elaborazione	10	3
61	2023-01-26 12:03:02.669	status  In Elaborazione -> Da consegnare	10	3
62	2023-01-26 12:03:09.368	status  Da consegnare -> Consegnata	13	3
63	2023-01-26 12:03:11.32	status  Consegnata -> Terminata	13	3
64	2023-01-26 12:03:13.022	status  Da consegnare -> Consegnata	1	3
65	2023-01-26 12:03:15.517	status  Consegnata -> Terminata	1	3
66	2023-01-26 12:03:16.886	status  Da consegnare -> Consegnata	2	3
67	2023-01-26 12:03:18.394	status  Consegnata -> Terminata	2	3
68	2023-01-26 12:03:23.177	status  Da consegnare -> Consegnata	3	3
69	2023-01-26 12:03:24.825	status  Da consegnare -> Consegnata	6	3
70	2023-01-26 12:03:25.985	status  Consegnata -> Terminata	6	3
71	2023-01-26 12:03:27.549	status  Da consegnare -> Consegnata	10	3
72	2023-01-26 12:03:29.002	status  Consegnata -> Terminata	3	3
73	2023-01-26 12:03:30.294	status  Da consegnare -> Consegnata	5	3
74	2023-01-26 12:03:31.66	status  Consegnata -> Terminata	10	3
75	2023-01-26 12:03:32.928	status  Consegnata -> Terminata	5	3
76	2023-01-26 12:03:34.102	status  Da consegnare -> Consegnata	11	3
77	2023-01-26 12:03:35.627	status  Consegnata -> Terminata	11	3
78	2023-01-26 12:03:37.694	status  Da consegnare -> Consegnata	7	3
79	2023-01-26 12:03:39.144	status  Consegnata -> Terminata	7	3
80	2023-01-26 12:03:40.71	status  Da consegnare -> Consegnata	12	3
81	2023-01-26 12:04:21.27	status  Creata -> In Attesa	14	3
82	2023-01-26 12:04:25.543	status  In Attesa -> In Elaborazione	14	3
83	2023-01-26 12:04:29.999	status  In Elaborazione -> Da consegnare	14	3
84	2023-01-26 12:04:40.137	status  Da consegnare -> Consegnata	14	3
85	2023-01-26 12:04:42.456	status  Consegnata -> Terminata	14	3
86	2023-01-26 18:31:44.949	status  Creata -> In Attesa	15	3
87	2023-01-26 18:34:52.698	status  Creata -> In Attesa	17	3
88	2023-01-27 13:20:28.983	status  In Attesa -> In Elaborazione	17	3
89	2023-01-27 13:20:30.739	status  In Elaborazione -> Da consegnare	17	3
90	2023-01-27 13:20:31.52	status  In Attesa -> In Elaborazione	15	3
91	2023-01-27 13:20:32.162	status  In Elaborazione -> Da consegnare	15	3
92	2023-01-27 13:24:03.778	status  Da consegnare -> Consegnata	9	3
\.


--
-- Data for Name: ordered_cocktail; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ordered_cocktail (id, quantity, cocktail, ordination) FROM stdin;
11	1	7	8
12	1	7	9
13	1	7	3
37	1	7	16
2	4	1	2
15	1	7	10
1	5	5	1
4	2	10	4
5	2	13	4
17	1	7	11
32	11	10	16
18	3	7	12
19	1	7	13
21	3	1	14
22	2	9	14
28	23	1	15
31	1	5	15
6	9	2	5
7	1	3	6
10	1	6	7
9	2	4	7
30	16	9	15
35	5	2	17
34	12	1	17
\.


--
-- Data for Name: ordination; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ordination (id, date_creation, date_last_modified, numbers_of_cocktails, status, total, uuid, created_by, delivered_by, executed_by, id_tables) FROM stdin;
13	2023-01-25 19:15:16.92	2023-01-25 19:15:16.92	1	6	7.5	31138b8b-3ffb-4aef-9a2a-ff441baf3f43	1	\N	\N	12
1	2023-01-16 13:04:07.45	2023-01-16 13:04:07.45	5	6	40	4f5bb286-a72c-4a5a-8fe8-99f3acc6da0b	3	\N	\N	1
2	2023-01-16 13:08:21.103	2023-01-16 13:08:21.103	4	6	24	7e85de00-3bf8-4ffa-8517-2ce6f6d393a3	3	\N	\N	2
6	2023-01-25 18:54:18.349	2023-01-25 18:54:18.349	1	6	6	bdbdfbd1-bc3c-480e-863d-abad61dfeea9	1	\N	\N	3
3	2023-01-16 13:11:35.368	2023-01-16 13:11:35.368	1	6	7.5	8170d59d-722e-4392-99b0-914df42dcd4b	3	\N	\N	8
10	2023-01-25 19:14:54.698	2023-01-25 19:14:54.698	1	6	7.5	13c9dbd4-7cac-4eb8-a1a0-fa4f9afb045e	1	\N	\N	8
5	2023-01-25 12:04:21.104	2023-01-25 12:04:21.104	9	6	49.5	606bbac4-e32b-4b5e-a43d-76232d890fb7	5	\N	\N	4
11	2023-01-25 19:15:07.779	2023-01-25 19:15:07.779	1	6	7.5	6165c82d-6cb9-43d1-85e7-d79af50b41c5	1	\N	\N	9
7	2023-01-25 18:58:08.83	2023-01-25 18:58:08.83	3	6	21.5	032f0a78-72ff-4e34-8b4f-b990ba7401ba	1	\N	\N	5
17	2023-01-26 18:32:04.055	2023-01-26 18:32:04.055	-6	4	-62	4289b977-ab36-461e-adc6-2ffa604c877f	3	\N	\N	2
12	2023-01-25 19:15:12.647	2023-01-25 19:15:12.647	3	5	22.5	f9d4c14b-ada0-47ed-b3d1-d7b5f1c6ced6	1	\N	\N	10
15	2023-01-26 18:19:10.674	2023-01-26 18:19:10.674	10	4	42.5	c53e5e77-ef41-40a8-8064-91c30bf5bb25	3	\N	\N	1
16	2023-01-26 18:26:59.204	2023-01-26 18:26:59.204	1	0	3	819ff080-c78f-4a99-8526-3052777d8e43	3	\N	\N	3
4	2023-01-25 12:02:20.313	2023-01-25 12:02:20.313	4	6	11	10d83fd9-1aea-4550-a4a3-42c4a577013c	5	\N	\N	3
9	2023-01-25 19:14:14.14	2023-01-25 19:14:14.14	1	5	7.5	fa0b6d8a-8e3d-4e8d-9d5d-2a48929e3dea	1	\N	\N	7
14	2023-01-26 12:04:08.903	2023-01-26 12:04:08.903	5	6	27	facee30a-62db-4ce8-b963-bbe0f97d24ba	3	\N	\N	1
8	2023-01-25 19:10:49.104	2023-01-25 19:10:49.104	1	4	7.5	87cdff2d-efa4-4fc5-af9e-494a5e86a652	1	\N	\N	6
\.


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product (id, alcoholic_percentage, category, is_present, ml, name, pathfileimg, uuid) FROM stdin;
1	40	GIN	t	700	BOMBEI	\N	bc8d67ea-8ada-11ed-a1eb-0242ac120002
3	39	AMARO	t	750	FERNET BRANCA	\N	bddbd3fe-8add-11ed-a1eb-0242ac120002
4	35	AMARO	t	750	DEL CAPO	\N	c566c494-8add-11ed-a1eb-0242ac120002
6	40	AMARO	t	750	UNICUM	\N	ce3d563c-8add-11ed-a1eb-0242ac120002
8	16	VERMUT	t	750	COCCHI	\N	dace3790-8add-11ed-a1eb-0242ac120002
9	18	VERMUT	t	750	DEL PROFESSORE BIANCO	\N	e03830dc-8add-11ed-a1eb-0242ac120002
11	28.5	BITTER	t	700	MARTINI RISERVA SPECIALE	\N	e8cd8170-8add-11ed-a1eb-0242ac120002
12	44.5	BITTER	t	200	ANGOSTURA	\N	f0a75650-8add-11ed-a1eb-0242ac120002
13	0	ACQUA TONICA	t	1000	SCHWEPPES	\N	f7727578-8add-11ed-a1eb-0242ac120002
14	0	ACQUA TONICA	t	200	FEVER-TREE INDIAN PREMIUM	\N	fff31810-8add-11ed-a1eb-0242ac120002
15	0	SODA	t	200	FEVER-TREE SODA WATER	\N	ec58472c-fcaa-481b-9345-1976a9458565
16	0	SODA	t	200	SCHWEPPES SODA	\N	184dc332-1d22-48a7-b478-8ebc23fd1323
18	13	VINO	t	750	AGLIANO	\N	04aaaf30-8ade-11ed-a1eb-0242ac120002
21	12	PROSECCO	t	750	FERRARI	\N	0fd8a79a-8ade-11ed-a1eb-0242ac120002
22	11	PROSECCO	t	750	VAL Di OCA	\N	1551a924-8ade-11ed-a1eb-0242ac120002
25	0	SCIROPPO	t	\N	DI ZUCCHERO	\N	1d999999-8ade-11ed-a1eb-0242ac120002
26	0	SUCCO	t	\N	DI LIME	\N	1d888888-8ade-11ed-a1eb-0242ac120002
27	0	ALBUME	t	\N	DI UOVO	\N	1d777777-8ade-11ed-a1eb-0242ac120002
29	0	SCIROPPO	t	\N	DI ZUCCHERO	\N	1d555555-8ade-11ed-a1eb-0242ac120002
30	0	SUCCO	t	\N	DI ARANCIA	\N	1d444444-8bce-11ed-a1eb-0242ac120002
31	0	SCIROPPO	t	\N	DI GRANATINA	\N	1d333333-8ade-11ed-a1eb-0242ac120002
33	0	GINGER	t	\N	BEER	\N	1d333333-8ade-121d-a1eb-0242ac120002
20	12	PROSECCO	f	0	MOET IMPERIAL	\N	0c7af328-8ade-11ed-a1eb-0242ac120002
24	48	TEQUILA	t	749	JOSE CUERVO CLASSICO	\N	1d238334-8ade-11ed-a1eb-0242ac120002
23	31	TEQUILA	t	748	LICOR 43	\N	1820fbdc-8ade-11ed-a1eb-0242ac120002
19	13	VINO	f	0	FALANGHINA	\N	0953118a-8ade-11ed-a1eb-0242ac120002
28	45	WHISKEY	t	1120	BOURBON	\N	1d666666-8ade-11ed-a1eb-0242ac120002
17	18	VINO	t	750	MARSALA	\N	fbb019b0-8add-11ed-a1eb-0242ac120002
7	18	VERMUT	f	0	MARTINI RISERVA SPECIALE RUBINO	\N	d47c9f30-8add-11ed-a1eb-0242ac120002
32	40	VODKA	t	15	ABSOLUTE	\N	1d444444-8ace-11ed-a1eb-0242ac120002
5	45	AMARO	t	670	PETRUS	\N	c9f78098-8add-11ed-a1eb-0242ac120002
10	25	BITTER	f	0	CAMPARI	\N	e41580a6-8add-11ed-a1eb-0242ac120002
2	42	GIN	t	370	MARE	\N	fd43db20-8ada-11ed-a1eb-0242ac120002
\.


--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.role (id, name) FROM stdin;
1	WAITER
2	BARTENDER
3	MANAGER
\.


--
-- Data for Name: tables; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tables (id, isfree, number, uuid) FROM stdin;
11	t	11	57ef8f40-d47e-43bf-aa36-c873055ae321
1	f	1	01c6659a-8ae0-11ed-a1eb-0242ac120001
2	f	2	09a7ae50-8ae0-11ed-a1eb-0242ac620024
5	f	5	41d339c1-b5db-4286-bf50-195bd621239a
8	f	8	1358344c-68ac-4a69-8fb3-ed85cdf11a78
3	f	3	14648296-8ae0-11ed-a1eb-0242ac320122
4	f	4	964f25e1-f435-40f8-b999-8cb197eddqc3
6	f	6	4215ce82-bf79-4cbe-9084-fcd1e0a46e25
7	f	7	a1b7b172-a16c-4a2b-b213-62ff1324c848
9	f	9	2e871784-4a65-4676-a944-a7s911q491f8
10	f	10	918bed1d-8915-4049-ab55-9c12d13621r4
12	f	12	12jh3h43-d47e-43bf-aa36-c873095ae309
\.


--
-- Data for Name: user_account; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_account (id, email, main_role, name, password, surname, uuid) FROM stdin;
2	mariozaccardi@gmail.com	MANAGER	Mario	cf8276ca60061baf2611917c50717a2b1bcbff0c0d25e00b95ef667ab8f158f0	Zaccardi	20a1d260-ae32-4d4c-9cbc-c60c94c92a43
9	melaniaconte@gmail.com	MANAGER	Melania	cf8276ca60061baf2611917c50717a2b1bcbff0c0d25e00b95ef667ab8f158f0	Conte	7e8da8af-678e-430d-80a8-f9847626f83f
8	mariarosariabaldi@gmail.com	MANAGER	Maria Rosaria	cf8276ca60061baf2611917c50717a2b1bcbff0c0d25e00b95ef667ab8f158f0	Baldi	e4514df1-280c-42e1-8dd0-a1d26be55afa
7	cgravino@gmail.com	WAITER	Carmine	cf8276ca60061baf2611917c50717a2b1bcbff0c0d25e00b95ef667ab8f158f0	Gravino	13f43a0f-83b8-45cd-8a7a-2fcb83b97c7a
6	bartender@gmail.com	BARTENDER	Bart	cf8276ca60061baf2611917c50717a2b1bcbff0c0d25e00b95ef667ab8f158f0	Ender	7a818e06-c6bc-403c-b3e3-2fac9e544591
1	cameriere@gmail.com	WAITER	Came	cf8276ca60061baf2611917c50717a2b1bcbff0c0d25e00b95ef667ab8f158f0	Riere	1da5825f-8dc3-4357-8e3b-11b87fd292e5
3	fedeboomer@gmail.com	MANAGER	Federico	cf8276ca60061baf2611917c50717a2b1bcbff0c0d25e00b95ef667ab8f158f0	Di Zenzo	bfa0df7e-55d9-447c-b857-f7920a9faeaf
4	manager@gmail.com	MANAGER	Mana	cf8276ca60061baf2611917c50717a2b1bcbff0c0d25e00b95ef667ab8f158f0	Ger	53209835-12c5-43c1-a23f-91b21e27401c
5	pagliara@gmail.com	BARTENDER	Nicola	cf8276ca60061baf2611917c50717a2b1bcbff0c0d25e00b95ef667ab8f158f0	Pagliara	acd5b874-8bdc-47cc-b411-747936c32cd7
\.


--
-- Data for Name: user_account_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_account_roles (user_account_id, roles_id) FROM stdin;
2	3
2	2
2	1
9	3
9	2
9	1
8	3
8	2
8	1
7	1
6	2
1	1
3	3
3	2
3	1
4	3
5	2
5	1
\.


--
-- Name: cocktail_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cocktail_id_seq', 40, true);


--
-- Name: ingredient_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ingredient_id_seq', 74, true);


--
-- Name: message_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.message_id_seq', 92, true);


--
-- Name: ordered_cocktail_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ordered_cocktail_id_seq', 37, true);


--
-- Name: ordination_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ordination_id_seq', 17, true);


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_id_seq', 33, true);


--
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.role_id_seq', 3, true);


--
-- Name: tables_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tables_id_seq', 12, true);


--
-- Name: user_account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_account_id_seq', 9, true);


--
-- Name: cocktail cocktail_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cocktail
    ADD CONSTRAINT cocktail_pkey PRIMARY KEY (id);


--
-- Name: ingredient ingredient_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingredient
    ADD CONSTRAINT ingredient_pkey PRIMARY KEY (id);


--
-- Name: message message_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT message_pkey PRIMARY KEY (id);


--
-- Name: ordered_cocktail ordered_cocktail_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordered_cocktail
    ADD CONSTRAINT ordered_cocktail_pkey PRIMARY KEY (id);


--
-- Name: ordination ordination_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordination
    ADD CONSTRAINT ordination_pkey PRIMARY KEY (id);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: tables tables_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tables
    ADD CONSTRAINT tables_pkey PRIMARY KEY (id);


--
-- Name: product uk_24bc4yyyk3fj3h7ku64i3yuog; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT uk_24bc4yyyk3fj3h7ku64i3yuog UNIQUE (uuid);


--
-- Name: role uk_8sewwnpamngi6b1dwaa88askk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT uk_8sewwnpamngi6b1dwaa88askk UNIQUE (name);


--
-- Name: ingredient uk_aak5dtksiqfbvgjo1mnqj8jj3; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingredient
    ADD CONSTRAINT uk_aak5dtksiqfbvgjo1mnqj8jj3 UNIQUE (uuid);


--
-- Name: ordination uk_luqm3s1ejvwkxr9qq4jptvu60; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordination
    ADD CONSTRAINT uk_luqm3s1ejvwkxr9qq4jptvu60 UNIQUE (uuid);


--
-- Name: tables uk_lx788xeiwqg80deseuk5u21yk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tables
    ADD CONSTRAINT uk_lx788xeiwqg80deseuk5u21yk UNIQUE (uuid);


--
-- Name: user_account uk_piri22wyjhfc7odv6x0eyr7i6; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_account
    ADD CONSTRAINT uk_piri22wyjhfc7odv6x0eyr7i6 UNIQUE (uuid);


--
-- Name: cocktail uk_sly0tmf0payl9tnjuloq2rs6a; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cocktail
    ADD CONSTRAINT uk_sly0tmf0payl9tnjuloq2rs6a UNIQUE (uuid);


--
-- Name: user_account user_account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_account
    ADD CONSTRAINT user_account_pkey PRIMARY KEY (id);


--
-- Name: ingredient fk8lbvy4ruetu6u1dup9h52wbyb; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingredient
    ADD CONSTRAINT fk8lbvy4ruetu6u1dup9h52wbyb FOREIGN KEY (cocktail) REFERENCES public.cocktail(id);


--
-- Name: ordination fka34epdokj9n694l0t08m0s870; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordination
    ADD CONSTRAINT fka34epdokj9n694l0t08m0s870 FOREIGN KEY (delivered_by) REFERENCES public.user_account(id);


--
-- Name: ordination fkb8wc9aebdmr0cu39mw4vxhnvi; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordination
    ADD CONSTRAINT fkb8wc9aebdmr0cu39mw4vxhnvi FOREIGN KEY (id_tables) REFERENCES public.tables(id);


--
-- Name: ingredient fkfvww2amibaypxyw6bfikq4tg7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingredient
    ADD CONSTRAINT fkfvww2amibaypxyw6bfikq4tg7 FOREIGN KEY (product) REFERENCES public.product(id);


--
-- Name: ordered_cocktail fkfyr9a1kr5bi77nstuedhaud06; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordered_cocktail
    ADD CONSTRAINT fkfyr9a1kr5bi77nstuedhaud06 FOREIGN KEY (ordination) REFERENCES public.ordination(id);


--
-- Name: user_account_roles fkhgw1hs27q8latvqis3h4u9hh0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_account_roles
    ADD CONSTRAINT fkhgw1hs27q8latvqis3h4u9hh0 FOREIGN KEY (roles_id) REFERENCES public.role(id);


--
-- Name: ordination fkhuwctsbp92nr94w8ltlj8y6dk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordination
    ADD CONSTRAINT fkhuwctsbp92nr94w8ltlj8y6dk FOREIGN KEY (executed_by) REFERENCES public.user_account(id);


--
-- Name: ordination fkjijdu7tk0qe2ke7p8riwavdv8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordination
    ADD CONSTRAINT fkjijdu7tk0qe2ke7p8riwavdv8 FOREIGN KEY (created_by) REFERENCES public.user_account(id);


--
-- Name: message fkjlulh83p0xvnkj2hpi7eq3in1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT fkjlulh83p0xvnkj2hpi7eq3in1 FOREIGN KEY (user_id) REFERENCES public.user_account(id);


--
-- Name: ordered_cocktail fkouaax53f5ahf8gudraby7il6o; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordered_cocktail
    ADD CONSTRAINT fkouaax53f5ahf8gudraby7il6o FOREIGN KEY (cocktail) REFERENCES public.cocktail(id);


--
-- Name: user_account_roles fkpacca51k3kkqoqs0nbmyugdt2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_account_roles
    ADD CONSTRAINT fkpacca51k3kkqoqs0nbmyugdt2 FOREIGN KEY (user_account_id) REFERENCES public.user_account(id);


--
-- Name: message fkt2bvrb455ab7q0gp24i9aabuy; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT fkt2bvrb455ab7q0gp24i9aabuy FOREIGN KEY (ordination_id) REFERENCES public.ordination(id);


--
-- PostgreSQL database dump complete
--

