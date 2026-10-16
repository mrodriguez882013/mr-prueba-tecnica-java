--
-- PostgreSQL database dump
--

\restrict V89CinwJp2DDq0b4HUmJcA2dWUzx7eaVUSWYYJldfWJrZpcspBeSxWqaD3umeVJ

-- Dumped from database version 18.6
-- Dumped by pg_dump version 18.6

-- Started on 2026-10-16 01:12:35

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 222 (class 1259 OID 16403)
-- Name: productos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.productos (
    id bigint NOT NULL,
    nombre character varying(255) NOT NULL,
    descripcion character varying(255),
    precio double precision NOT NULL,
    existencias integer NOT NULL,
    fotografia character varying(255),
    proveedor_id bigint NOT NULL,
    CONSTRAINT productos_existencias_check CHECK ((existencias >= 0)),
    CONSTRAINT productos_precio_check CHECK ((precio >= ((0)::numeric)::double precision))
);


ALTER TABLE public.productos OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16402)
-- Name: productos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.productos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.productos_id_seq OWNER TO postgres;

--
-- TOC entry 5028 (class 0 OID 0)
-- Dependencies: 221
-- Name: productos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.productos_id_seq OWNED BY public.productos.id;


--
-- TOC entry 220 (class 1259 OID 16390)
-- Name: proveedores; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.proveedores (
    id bigint NOT NULL,
    nombre character varying(255) NOT NULL,
    numero_telefono character varying(255),
    correo_electronico character varying(255),
    direccion character varying(255)
);


ALTER TABLE public.proveedores OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16389)
-- Name: proveedores_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.proveedores_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.proveedores_id_seq OWNER TO postgres;

--
-- TOC entry 5029 (class 0 OID 0)
-- Dependencies: 219
-- Name: proveedores_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.proveedores_id_seq OWNED BY public.proveedores.id;


--
-- TOC entry 4862 (class 2604 OID 16423)
-- Name: productos id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.productos ALTER COLUMN id SET DEFAULT nextval('public.productos_id_seq'::regclass);


--
-- TOC entry 4861 (class 2604 OID 16460)
-- Name: proveedores id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.proveedores ALTER COLUMN id SET DEFAULT nextval('public.proveedores_id_seq'::regclass);


--
-- TOC entry 5022 (class 0 OID 16403)
-- Dependencies: 222
-- Data for Name: productos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.productos (id, nombre, descripcion, precio, existencias, fotografia, proveedor_id) FROM stdin;
1	Teclado Mecánico RGB	Teclado mecánico con switches red y retroiluminación RGB.	85.5	45	teclado_rgb.jpg	1
2	Mouse Ergonómico Inalámbrico	Mouse inalámbrico con sensor óptico de alta precisión y batería recargable.	45.99	60	mouse_ergonomico.jpg	2
3	Monitor Gamer 27"	Monitor Curvo de 27 pulgadas, 165Hz y 1ms de respuesta.	299.99	12	monitor_gamer_27.jpg	3
4	Auriculares Over-Ear Bluetooth	Auriculares con cancelación activa de ruido y 40 horas de autonomía.	120	25	auriculares_bluetooth.jpg	4
5	Memoria RAM DDR4 16GB	Módulo de memoria RAM de 16GB a 3200MHz con disipador térmico.	65	80	ram_ddr4_16gb.jpg	5
6	Disco Duro Externo 2TB	Disco duro portátil USB 3.0 para respaldos de alta velocidad.	89.99	35	hdd_externo_2tb.jpg	1
7	Unidad SSD M.2 NVMe 1TB	SSD de alta velocidad con lecturas de hasta 3500 MB/s.	105.5	50	ssd_m2_1tb.jpg	2
8	Tarjeta de Video RTX 4060	Tarjeta gráfica para gaming en 1080p y 1440p con trazado de rayos.	349	8	rtx_4060.jpg	3
9	Procesador Intel Core i7	Procesador de 12 núcleos de última generación para rendimiento extremo.	310	15	intel_i7.jpg	4
10	Fuente de Poder 750W	Fuente de alimentación con certificación 80 Plus Gold semimodular.	95.25	20	fuente_750w.jpg	5
11	Gabinete ATX Mid-Tower	Chasis para PC con panel de vidrio templado y 3 ventiladores incluidos.	79.99	18	gabinete_atx.jpg	1
12	Placa Madre B550M	Motherboard compatible con procesadores AMD Ryzen y ranuras PCIe 4.0.	115	22	placa_madre_b550.jpg	2
13	Cámara Web 4K Ultra HD	Webcam para streaming y videoconferencias con micrófono estéreo.	135	14	webcam_4k.jpg	3
14	Silla Gamer Ergonómica	Silla ajustable con soporte lumbar y reposabrazos 3D.	189.99	10	silla_gamer.jpg	4
15	Hub USB-C de 7 Puertos	Adaptador multipuerto con salidas HDMI, USB 3.0 y lector de tarjetas SD.	34.5	40	hub_usbc_7p.jpg	5
16	Laptop Latitude 5440	Computadora portátil con procesador Intel Core i5, 16GB de memoria RAM y 512GB de disco de estado sólido (SSD).	750	25	https://ejemplo.com	4
18	string	string	0.1	0	string	2
\.


--
-- TOC entry 5020 (class 0 OID 16390)
-- Dependencies: 220
-- Data for Name: proveedores; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.proveedores (id, nombre, numero_telefono, correo_electronico, direccion) FROM stdin;
2	Tecnología Global S.A.	555-1234	contacto@tecnologiaglobal.com	Av. Principal #123
3	Distribuidora Global S.A.	+34600112233	contacto@distribuidoraglobal.com	Avenida de la Industria, Número 45, Planta 2, Madrid, España
1	La constancia	+5032245-5656	lconst@gmail.com	Av Independencia #655
4	La favorita sa	+5032245-5656	contacto@contacto.com	Av Independe 556
5	Miguel	+5037298-6434	mrodriguez882013@gmail.com	La dirección #255
6	string	string	user@example.com	string
\.


--
-- TOC entry 5030 (class 0 OID 0)
-- Dependencies: 221
-- Name: productos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.productos_id_seq', 18, true);


--
-- TOC entry 5031 (class 0 OID 0)
-- Dependencies: 219
-- Name: proveedores_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.proveedores_id_seq', 6, true);


--
-- TOC entry 4870 (class 2606 OID 16425)
-- Name: productos productos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.productos
    ADD CONSTRAINT productos_pkey PRIMARY KEY (id);


--
-- TOC entry 4866 (class 2606 OID 16477)
-- Name: proveedores proveedores_correo_electronico_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.proveedores
    ADD CONSTRAINT proveedores_correo_electronico_key UNIQUE (correo_electronico);


--
-- TOC entry 4868 (class 2606 OID 16462)
-- Name: proveedores proveedores_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.proveedores
    ADD CONSTRAINT proveedores_pkey PRIMARY KEY (id);


--
-- TOC entry 4871 (class 2606 OID 16464)
-- Name: productos fk_producto_proveedor; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.productos
    ADD CONSTRAINT fk_producto_proveedor FOREIGN KEY (proveedor_id) REFERENCES public.proveedores(id) ON UPDATE CASCADE ON DELETE RESTRICT;


-- Completed on 2026-10-16 01:12:35

--
-- PostgreSQL database dump complete
--

\unrestrict V89CinwJp2DDq0b4HUmJcA2dWUzx7eaVUSWYYJldfWJrZpcspBeSxWqaD3umeVJ

