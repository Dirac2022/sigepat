--
-- PostgreSQL database dump
--

-- Dumped from database version 17.0
-- Dumped by pg_dump version 17.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: cod_seguridad_dom; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.cod_seguridad_dom AS character(3)
	CONSTRAINT cod_seguridad_dom_check CHECK ((VALUE ~ '^\d{3}$'::text));


ALTER DOMAIN public.cod_seguridad_dom OWNER TO postgres;

--
-- Name: email_dom; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.email_dom AS character varying(100)
	CONSTRAINT email_dom_check CHECK (((VALUE)::text ~* '^[A-Za-Z0-9._-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'::text));


ALTER DOMAIN public.email_dom OWNER TO postgres;

--
-- Name: estado_pago_dom; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.estado_pago_dom AS text
	CONSTRAINT estado_pago_dom_check CHECK ((VALUE = ANY (ARRAY['PENDIENTE'::text, 'COMPLETADO'::text, 'CANCELADO'::text])));


ALTER DOMAIN public.estado_pago_dom OWNER TO postgres;

--
-- Name: estado_reserva_dom; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.estado_reserva_dom AS character varying(15)
	CONSTRAINT estado_reserva_dom_check CHECK (((VALUE)::text = ANY ((ARRAY['PENDIENTE'::character varying, 'CONFIRMADA'::character varying, 'MODIFICADA'::character varying, 'CANCELADA'::character varying, 'COMPLETADA'::character varying])::text[])));


ALTER DOMAIN public.estado_reserva_dom OWNER TO postgres;

--
-- Name: fecha_venc_dom; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.fecha_venc_dom AS character(5)
	CONSTRAINT fecha_venc_dom_check CHECK ((VALUE ~ '^(2[5-9])-(0[1-9]|1[0-2])$'::text));


ALTER DOMAIN public.fecha_venc_dom OWNER TO postgres;

--
-- Name: metodo_pago_dom; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.metodo_pago_dom AS text
	CONSTRAINT metodo_pago_dom_check CHECK ((VALUE = ANY (ARRAY['CREDITO'::text, 'DEBITO'::text])));


ALTER DOMAIN public.metodo_pago_dom OWNER TO postgres;

--
-- Name: nro_tarjeta_dom; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.nro_tarjeta_dom AS character varying(20)
	CONSTRAINT nro_tarjeta_dom_check CHECK (((VALUE)::text ~ '^\d+$'::text));


ALTER DOMAIN public.nro_tarjeta_dom OWNER TO postgres;

--
-- Name: num_celular_dom; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.num_celular_dom AS character varying(15)
	CONSTRAINT num_celular_dom_check CHECK (((VALUE)::text ~ '^\+?[0-9]{10,15}$'::text));


ALTER DOMAIN public.num_celular_dom OWNER TO postgres;

--
-- Name: password_dom; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.password_dom AS character varying(50)
	CONSTRAINT password_dom_check CHECK (((VALUE)::text ~ '^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{8,}$'::text));


ALTER DOMAIN public.password_dom OWNER TO postgres;

--
-- Name: sex_dom; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.sex_dom AS character varying(10)
	CONSTRAINT sex_dom_check CHECK (((VALUE)::text = ANY ((ARRAY['M'::character varying, 'F'::character varying])::text[])));


ALTER DOMAIN public.sex_dom OWNER TO postgres;

--
-- Name: tipo_auto_dom; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.tipo_auto_dom AS character varying(10)
	CONSTRAINT tipo_auto_dom_check CHECK (((VALUE)::text = ANY ((ARRAY['COMPACTO'::character varying, 'MEDIANO'::character varying, 'CAMIONETA'::character varying, 'MINIVAN'::character varying])::text[])));


ALTER DOMAIN public.tipo_auto_dom OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: actividad_turistica; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.actividad_turistica (
    id integer NOT NULL,
    precio numeric(10,2),
    cancelable boolean NOT NULL,
    modificable boolean NOT NULL,
    nombre character varying(100) NOT NULL,
    descripcion text,
    fechahora timestamp without time zone NOT NULL
);


ALTER TABLE public.actividad_turistica OWNER TO postgres;

--
-- Name: actividad_turistica_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.actividad_turistica_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.actividad_turistica_id_seq OWNER TO postgres;

--
-- Name: actividad_turistica_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.actividad_turistica_id_seq OWNED BY public.actividad_turistica.id;


--
-- Name: alojamiento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.alojamiento (
    id integer NOT NULL,
    precio numeric(10,2) NOT NULL,
    cancelable boolean NOT NULL,
    modificable boolean NOT NULL,
    nombre character varying(100) NOT NULL,
    ciudad character varying(50) NOT NULL,
    direccion character varying(100) NOT NULL,
    clasificacion integer NOT NULL,
    habitacion_ind_disponible integer NOT NULL,
    habitacion_dob_disponible integer
);


ALTER TABLE public.alojamiento OWNER TO postgres;

--
-- Name: alojamiento_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.alojamiento_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.alojamiento_id_seq OWNER TO postgres;

--
-- Name: alojamiento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.alojamiento_id_seq OWNED BY public.alojamiento.id;


--
-- Name: alquiler_auto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.alquiler_auto (
    id integer NOT NULL,
    precio numeric(10,2) NOT NULL,
    cancelable boolean NOT NULL,
    modificable boolean NOT NULL,
    fechahora_retiro timestamp without time zone NOT NULL,
    fechahora_devolucion timestamp without time zone NOT NULL
);


ALTER TABLE public.alquiler_auto OWNER TO postgres;

--
-- Name: alquiler_auto_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.alquiler_auto_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.alquiler_auto_id_seq OWNER TO postgres;

--
-- Name: alquiler_auto_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.alquiler_auto_id_seq OWNED BY public.alquiler_auto.id;


--
-- Name: auto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.auto (
    id integer NOT NULL,
    tipo_auto public.tipo_auto_dom NOT NULL,
    marca character varying(50) NOT NULL,
    modelo character varying(50) NOT NULL,
    capacidad integer NOT NULL,
    disponible boolean NOT NULL
);


ALTER TABLE public.auto OWNER TO postgres;

--
-- Name: auto_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.auto_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.auto_id_seq OWNER TO postgres;

--
-- Name: auto_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.auto_id_seq OWNED BY public.auto.id;


--
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente (
    id_cliente integer NOT NULL,
    nombres character varying(50) NOT NULL,
    apellido_paterno character varying(50) NOT NULL,
    apellido_materno character varying(50) NOT NULL,
    fecha_nac date NOT NULL,
    sexo public.sex_dom NOT NULL,
    email public.email_dom NOT NULL,
    numcelular public.num_celular_dom,
    password_cliente public.password_dom NOT NULL
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- Name: cliente_id_cliente_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cliente_id_cliente_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.cliente_id_cliente_seq OWNER TO postgres;

--
-- Name: cliente_id_cliente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cliente_id_cliente_seq OWNED BY public.cliente.id_cliente;


--
-- Name: movilidad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.movilidad (
    id integer NOT NULL,
    precio numeric(10,2),
    cancelable boolean NOT NULL,
    modificable boolean NOT NULL,
    fechahora_ida timestamp without time zone NOT NULL,
    fechahora_vuelta timestamp without time zone NOT NULL
);


ALTER TABLE public.movilidad OWNER TO postgres;

--
-- Name: movilidad_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.movilidad_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.movilidad_id_seq OWNER TO postgres;

--
-- Name: movilidad_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.movilidad_id_seq OWNED BY public.movilidad.id;


--
-- Name: pago; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pago (
    id_pago integer NOT NULL,
    monto numeric(10,2) NOT NULL,
    estado public.estado_pago_dom NOT NULL,
    metodo_pago public.metodo_pago_dom NOT NULL
);


ALTER TABLE public.pago OWNER TO postgres;

--
-- Name: pago_id_pago_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pago_id_pago_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.pago_id_pago_seq OWNER TO postgres;

--
-- Name: pago_id_pago_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pago_id_pago_seq OWNED BY public.pago.id_pago;


--
-- Name: paquete_turistico; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.paquete_turistico (
    id integer NOT NULL,
    precio_total numeric(10,2) NOT NULL
);


ALTER TABLE public.paquete_turistico OWNER TO postgres;

--
-- Name: paquete_turistico_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.paquete_turistico_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.paquete_turistico_id_seq OWNER TO postgres;

--
-- Name: paquete_turistico_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.paquete_turistico_id_seq OWNED BY public.paquete_turistico.id;


--
-- Name: pasajero; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pasajero (
    id integer NOT NULL,
    nombres character varying(50) NOT NULL,
    apellidos character varying(50) NOT NULL,
    pais_residencia character varying(50) NOT NULL,
    tipo_doc character varying(20) NOT NULL,
    nro_doc character varying(20) NOT NULL,
    fecha_nac date NOT NULL,
    sexo public.sex_dom NOT NULL
);


ALTER TABLE public.pasajero OWNER TO postgres;

--
-- Name: pasajero_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pasajero_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.pasajero_id_seq OWNER TO postgres;

--
-- Name: pasajero_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pasajero_id_seq OWNED BY public.pasajero.id;


--
-- Name: reserva; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reserva (
    id integer NOT NULL,
    estado public.estado_reserva_dom NOT NULL,
    fecha_reserva timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


ALTER TABLE public.reserva OWNER TO postgres;

--
-- Name: reserva_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.reserva_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.reserva_id_seq OWNER TO postgres;

--
-- Name: reserva_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.reserva_id_seq OWNED BY public.reserva.id;


--
-- Name: tarjeta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tarjeta (
    nro_tarjeta public.nro_tarjeta_dom NOT NULL,
    titular character varying(100) NOT NULL,
    nro_doc_titular character varying(20) NOT NULL,
    fecha_venc public.fecha_venc_dom NOT NULL,
    cod_seguridad public.cod_seguridad_dom NOT NULL
);


ALTER TABLE public.tarjeta OWNER TO postgres;

--
-- Name: vuelo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vuelo (
    nro_vuelo integer NOT NULL,
    precio numeric(10,2) NOT NULL,
    cancelable boolean NOT NULL,
    modificable boolean NOT NULL,
    lugar_origen character varying(100) NOT NULL,
    lugar_destino character varying(100) NOT NULL,
    fecha_hora_ida timestamp without time zone NOT NULL,
    fecha_hora_regreso timestamp without time zone NOT NULL,
    clase character varying(20),
    aerolinea character varying(50)
);


ALTER TABLE public.vuelo OWNER TO postgres;

--
-- Name: vuelo_nro_vuelo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.vuelo_nro_vuelo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.vuelo_nro_vuelo_seq OWNER TO postgres;

--
-- Name: vuelo_nro_vuelo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.vuelo_nro_vuelo_seq OWNED BY public.vuelo.nro_vuelo;


--
-- Name: actividad_turistica id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.actividad_turistica ALTER COLUMN id SET DEFAULT nextval('public.actividad_turistica_id_seq'::regclass);


--
-- Name: alojamiento id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alojamiento ALTER COLUMN id SET DEFAULT nextval('public.alojamiento_id_seq'::regclass);


--
-- Name: alquiler_auto id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alquiler_auto ALTER COLUMN id SET DEFAULT nextval('public.alquiler_auto_id_seq'::regclass);


--
-- Name: auto id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.auto ALTER COLUMN id SET DEFAULT nextval('public.auto_id_seq'::regclass);


--
-- Name: cliente id_cliente; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente ALTER COLUMN id_cliente SET DEFAULT nextval('public.cliente_id_cliente_seq'::regclass);


--
-- Name: movilidad id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movilidad ALTER COLUMN id SET DEFAULT nextval('public.movilidad_id_seq'::regclass);


--
-- Name: pago id_pago; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pago ALTER COLUMN id_pago SET DEFAULT nextval('public.pago_id_pago_seq'::regclass);


--
-- Name: paquete_turistico id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paquete_turistico ALTER COLUMN id SET DEFAULT nextval('public.paquete_turistico_id_seq'::regclass);


--
-- Name: pasajero id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pasajero ALTER COLUMN id SET DEFAULT nextval('public.pasajero_id_seq'::regclass);


--
-- Name: reserva id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reserva ALTER COLUMN id SET DEFAULT nextval('public.reserva_id_seq'::regclass);


--
-- Name: vuelo nro_vuelo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vuelo ALTER COLUMN nro_vuelo SET DEFAULT nextval('public.vuelo_nro_vuelo_seq'::regclass);


--
-- Data for Name: actividad_turistica; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.actividad_turistica (id, precio, cancelable, modificable, nombre, descripcion, fechahora) FROM stdin;
\.


--
-- Data for Name: alojamiento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.alojamiento (id, precio, cancelable, modificable, nombre, ciudad, direccion, clasificacion, habitacion_ind_disponible, habitacion_dob_disponible) FROM stdin;
1	250.00	t	f	Hotel Estelar Miraflores	Lima	Av. Pardo y Aliaga 1035, Miraflores, Lima	4	50	100
2	400.00	f	t	JW Marriott Hotel Lima	Lima	Malec¢n de la Reserva 615, Miraflores, Lima	5	100	200
3	300.00	t	t	Belmond Hotel Monasterio	Cuzco	Calle Palacio 136, Cusco	5	60	90
4	200.00	f	f	JW Marriott El Convento Cusco	Cuzco	Esquina de la Calle Ruinas 432 y San Agust¡n, Cusco	5	70	80
5	180.00	t	t	Casa Andina Premium Arequipa	Arequipa	Calle Ugarte 403, Arequipa	4	40	60
6	220.00	f	t	Hotel Libertador Arequipa	Arequipa	Plaza Bol¡var s/n, Selva Alegre, Arequipa	5	50	70
7	150.00	t	f	Costa del Sol Wyndham Piura	Piura	Av. Loreto 649, Piura	4	60	80
8	120.00	f	t	Hotel Gran Palma Piura	Piura	Jr. Huancavelica 210, Piura	3	30	50
9	200.00	t	t	Costa del Sol Wyndham Trujillo	Trujillo	Av. Los Cocoteros 505, Urb. El Golf, Trujillo	4	70	90
10	140.00	f	f	Hotel El Gran Marqu‚s	Trujillo	Av. Mansiche 1440, Trujillo	3	40	60
11	110.00	t	t	Hotel Costa del Sol Wyndham Cajamarca	Cajamarca	Jr. Cruz de Piedra 707, Cajamarca	4	50	70
12	95.00	f	t	Hotel El Portal del Marqu‚s	Cajamarca	Jr. Del Comercio 644, Cajamarca	3	30	40
13	60.00	t	t	Hotel Casa Jauja	Jauja	Jr. Jun¡n 555, Jauja	2	20	30
14	50.00	f	f	Hotel Hatun Wasi	Jauja	Av. Ricardo Palma 123, Jauja	2	15	25
15	120.00	t	t	Manish Hotel Ecol¢gico	Pucallpa	Av. San Mart¡n 1230, Pucallpa	3	40	60
16	100.00	f	t	Hotel Sol del Oriente Pucallpa	Pucallpa	Jr. Tarapac  945, Pucallpa	3	35	55
\.


--
-- Data for Name: alquiler_auto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.alquiler_auto (id, precio, cancelable, modificable, fechahora_retiro, fechahora_devolucion) FROM stdin;
\.


--
-- Data for Name: auto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.auto (id, tipo_auto, marca, modelo, capacidad, disponible) FROM stdin;
\.


--
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cliente (id_cliente, nombres, apellido_paterno, apellido_materno, fecha_nac, sexo, email, numcelular, password_cliente) FROM stdin;
\.


--
-- Data for Name: movilidad; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.movilidad (id, precio, cancelable, modificable, fechahora_ida, fechahora_vuelta) FROM stdin;
\.


--
-- Data for Name: pago; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pago (id_pago, monto, estado, metodo_pago) FROM stdin;
\.


--
-- Data for Name: paquete_turistico; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.paquete_turistico (id, precio_total) FROM stdin;
\.


--
-- Data for Name: pasajero; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pasajero (id, nombres, apellidos, pais_residencia, tipo_doc, nro_doc, fecha_nac, sexo) FROM stdin;
\.


--
-- Data for Name: reserva; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reserva (id, estado, fecha_reserva) FROM stdin;
\.


--
-- Data for Name: tarjeta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tarjeta (nro_tarjeta, titular, nro_doc_titular, fecha_venc, cod_seguridad) FROM stdin;
\.


--
-- Data for Name: vuelo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.vuelo (nro_vuelo, precio, cancelable, modificable, lugar_origen, lugar_destino, fecha_hora_ida, fecha_hora_regreso, clase, aerolinea) FROM stdin;
\.


--
-- Name: actividad_turistica_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.actividad_turistica_id_seq', 1, false);


--
-- Name: alojamiento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.alojamiento_id_seq', 1, false);


--
-- Name: alquiler_auto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.alquiler_auto_id_seq', 1, false);


--
-- Name: auto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.auto_id_seq', 1, false);


--
-- Name: cliente_id_cliente_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cliente_id_cliente_seq', 1, false);


--
-- Name: movilidad_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.movilidad_id_seq', 1, false);


--
-- Name: pago_id_pago_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pago_id_pago_seq', 1, false);


--
-- Name: paquete_turistico_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.paquete_turistico_id_seq', 1, false);


--
-- Name: pasajero_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pasajero_id_seq', 1, false);


--
-- Name: reserva_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reserva_id_seq', 1, false);


--
-- Name: vuelo_nro_vuelo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.vuelo_nro_vuelo_seq', 1, false);


--
-- Name: actividad_turistica actividad_turistica_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.actividad_turistica
    ADD CONSTRAINT actividad_turistica_pkey PRIMARY KEY (id);


--
-- Name: alojamiento alojamiento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alojamiento
    ADD CONSTRAINT alojamiento_pkey PRIMARY KEY (id);


--
-- Name: alquiler_auto alquiler_auto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alquiler_auto
    ADD CONSTRAINT alquiler_auto_pkey PRIMARY KEY (id);


--
-- Name: auto auto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.auto
    ADD CONSTRAINT auto_pkey PRIMARY KEY (id);


--
-- Name: cliente cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id_cliente);


--
-- Name: movilidad movilidad_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movilidad
    ADD CONSTRAINT movilidad_pkey PRIMARY KEY (id);


--
-- Name: pago pago_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pago
    ADD CONSTRAINT pago_pkey PRIMARY KEY (id_pago);


--
-- Name: paquete_turistico paquete_turistico_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paquete_turistico
    ADD CONSTRAINT paquete_turistico_pkey PRIMARY KEY (id);


--
-- Name: pasajero pasajero_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pasajero
    ADD CONSTRAINT pasajero_pkey PRIMARY KEY (id);


--
-- Name: reserva reserva_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reserva
    ADD CONSTRAINT reserva_pkey PRIMARY KEY (id);


--
-- Name: tarjeta tarjeta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tarjeta
    ADD CONSTRAINT tarjeta_pkey PRIMARY KEY (nro_tarjeta);


--
-- Name: vuelo vuelo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vuelo
    ADD CONSTRAINT vuelo_pkey PRIMARY KEY (nro_vuelo);


--
-- PostgreSQL database dump complete
--

