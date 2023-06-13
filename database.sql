CREATE DATABASE prueba WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'C';


ALTER DATABASE prueba OWNER TO postgres;

\connect prueba

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

--
-- TOC entry 6 (class 2615 OID 16486)
-- Name: tareas; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA tareas;


ALTER SCHEMA tareas OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16494)
-- Name: identificador; Type: SEQUENCE; Schema: tareas; Owner: postgres
--

CREATE SEQUENCE tareas.identificador
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tareas.identificador OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 16487)
-- Name: tarea; Type: TABLE; Schema: tareas; Owner: postgres
--

CREATE TABLE tareas.tarea (
    id_tarea smallint NOT NULL,
    descripcion character varying,
    fecha date,
    vigente boolean
);


ALTER TABLE tareas.tarea OWNER TO postgres;

--
-- TOC entry 3175 (class 2606 OID 16493)
-- Name: tarea tarea_pk; Type: CONSTRAINT; Schema: tareas; Owner: postgres
--

ALTER TABLE ONLY tareas.tarea
    ADD CONSTRAINT tarea_pk PRIMARY KEY (id_tarea);