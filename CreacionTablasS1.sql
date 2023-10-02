CREATE TABLE clientes (
    id_clientes NUMBER NOT NULL,
    nombre      VARCHAR2(20),
    reservas    NUMBER,
    consumos    NUMBER,
    tipodeplan  VARCHAR2(20)
);

ALTER TABLE clientes ADD CONSTRAINT clientes_pk PRIMARY KEY ( id_clientes );
                                                        productos_id_productos );

CREATE TABLE habitacion (
    id_habitacion  NUMBER NOT NULL,
    tipo           VARCHAR2(30),
    capacidad      NUMBER(10, 1),
    costo          NUMBER,
    cuenta         NUMBER
);

ALTER TABLE habitacion ADD CONSTRAINT habitacion_pk PRIMARY KEY ( id_habitacion );

CREATE TABLE hotel (
    id_hotel       NUMBER NOT NULL,
    nombre         VARCHAR2(20),
    cadenahotelera VARCHAR2(40),
    direccion      VARCHAR2(20),
    categoria      VARCHAR2(20)
);

ALTER TABLE hotel ADD CONSTRAINT hotel_pk PRIMARY KEY ( id_hotel );

CREATE TABLE plandeconsumo (
    id_plan              NUMBER NOT NULL,
    nombre               VARCHAR2(30),
    descripcion          VARCHAR2(100),
    descuento            NUMBER,
    inclusiones          VARCHAR2(100)
);

ALTER TABLE plandeconsumo ADD CONSTRAINT plandeconsumo_pk PRIMARY KEY ( id_plan );

CREATE TABLE productos (
    id_productos NUMBER NOT NULL,
    nombre       VARCHAR2(20),
    costo        NUMBER
);

ALTER TABLE productos ADD CONSTRAINT productos_pk PRIMARY KEY ( id_productos );


CREATE TABLE reserva (
    id_reserva               NUMBER NOT NULL,
    fechaentrada             VARCHAR2(10),
    fechasalida              VARCHAR2(10),
    numpersonas              NUMBER
);


ALTER TABLE reserva ADD CONSTRAINT reserva_pk PRIMARY KEY ( id_reserva );

CREATE TABLE servicio (
    id_servicio    NUMBER NOT NULL,
    nombre         VARCHAR2(20),
    descripcion    VARCHAR2(100),
    costoadicional NUMBER,
    horario        NUMBER,
    disponibilidad NUMBER
);

ALTER TABLE servicio ADD CONSTRAINT servicio_pk PRIMARY KEY ( id_servicio );

CREATE TABLE usuarios (
    tipodocumento     VARCHAR2(20),
    tipo              VARCHAR2(20),
    numerodocumento   NUMBER NOT NULL,
    nombre            VARCHAR2(20),
    correoelectronico VARCHAR2(40)

);

ALTER TABLE usuarios ADD CONSTRAINT usuarios_pk PRIMARY KEY ( numerodocumento );



ALTER TABLE habitacion
    ADD CONSTRAINT habitacion_hotel_fk FOREIGN KEY ( hotel_id_hotel )
        REFERENCES hotel ( id_hotel );

ALTER TABLE plandeconsumo
    ADD CONSTRAINT plandeconsumo_clientes_fk FOREIGN KEY ( clientes_id_clientes )
        REFERENCES clientes ( id_clientes );

ALTER TABLE plandeconsumo
    ADD CONSTRAINT plandeconsumo_hotel_fk FOREIGN KEY ( hotel_id_hotel )
        REFERENCES hotel ( id_hotel );

ALTER TABLE plandeconsumo
    ADD CONSTRAINT plandeconsumo_servicio_fk FOREIGN KEY ( servicio_id_servicio )
        REFERENCES servicio ( id_servicio );

ALTER TABLE reserva
    ADD CONSTRAINT reserva_clientes_fk FOREIGN KEY ( clientes_id_clientes )
        REFERENCES clientes ( id_clientes );

ALTER TABLE reserva
    ADD CONSTRAINT reserva_habitacion_fk FOREIGN KEY ( habitacion_id_habitacion )
        REFERENCES habitacion ( id_habitacion );

ALTER TABLE reserva
    ADD CONSTRAINT reserva_plandeconsumo_fk FOREIGN KEY ( plandeconsumo_id_plan )
        REFERENCES plandeconsumo ( id_plan );

ALTER TABLE servicio
    ADD CONSTRAINT servicio_hotel_fk FOREIGN KEY ( hotel_id_hotel )
        REFERENCES hotel ( id_hotel );

ALTER TABLE usuarios
    ADD CONSTRAINT usuarios_hotel_fk FOREIGN KEY ( hotel_id_hotel )
        REFERENCES hotel ( id_hotel );






