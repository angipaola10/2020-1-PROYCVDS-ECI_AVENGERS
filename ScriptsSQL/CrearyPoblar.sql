create extension pgcrypto;

CREATE TABLE IF NOT EXISTS Rol(tipo VARCHAR(14) PRIMARY KEY);

CREATE TABLE IF NOT EXISTS Estado(id SERIAL PRIMARY KEY, estado VARCHAR(30));

CREATE TABLE IF NOT EXISTS Usuario(id BIGINT NOT NULL, tid VARCHAR(2) NOT NULL, nombre VARCHAR(50) NOT NULL, telefono BIGINT NOT NULL, correo VARCHAR(50) PRIMARY KEY, clave VARCHAR(20) NOT NULL, estado VARCHAR(15) NOT NULL, rol VARCHAR(14) REFERENCES Rol(tipo));

ALTER TABLE Usuario ADD CONSTRAINT id_unico UNIQUE(id);

CREATE TABLE IF NOT EXISTS Iniciativa(nombrePropuesta VARCHAR(50) NOT NULL, id SERIAL PRIMARY KEY, descripcion varchar(150)  NOT NULL, fechaInicio date, area varchar(50)  NOT NULL, usuario VARCHAR(50) REFERENCES Usuario(correo), estado_Propuesta VARCHAR(30) NOT NULL);

ALTER TABLE Iniciativa ADD CONSTRAINT nombrePropuesta_unico UNIQUE(nombrePropuesta);

CREATE TABLE IF NOT EXISTS PalabraClave (id SERIAL PRIMARY KEY, palabraClave VARCHAR(20) NOT NULL);

CREATE TABLE IF NOT EXISTS PCIniciativa (ini_id INT NOT NULL, palabras_clave INT NOT NULL);

ALTER TABLE PCIniciativa ADD CONSTRAINT PK_PCIniciativa_ids PRIMARY KEY(ini_id, palabras_clave);

ALTER TABLE PCIniciativa ADD CONSTRAINT FK_PCIniciativa_iniciativa FOREIGN KEY (ini_id) REFERENCES Iniciativa(id);

ALTER TABLE PCIniciativa ADD CONSTRAINT FK_PCIniciativa_palabraClave FOREIGN KEY (palabras_clave) REFERENCES PalabraClave(id);



INSERT INTO Rol(tipo) VALUES ('Administrador');
INSERT INTO Rol(tipo) VALUES ('Proponente');
INSERT INTO Rol(tipo) VALUES ('PersonalPMO');
INSERT INTO Rol(tipo) VALUES ('Publico');


INSERT INTO Estado(estado) VALUES ('En espera de revisión');
INSERT INTO Estado(estado) VALUES ('En revisión');
INSERT INTO Estado(estado) VALUES ('Proyecto');
INSERT INTO Estado(estado) VALUES ('Solucionado');


INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (9303130, 'CC', 'Angi Jimenez', 3186759533, 'angi.jimenez', '51588ae1ff9e45374efe138a856eec0479efc67a22de0f251e0021a9a2396dc2', 'Activo', 'Administrador');
INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (1019150998, 'CC', 'Daniela Ruiz', 3178484579, 'angied.ruiz', 'ceb55d033d440973d9c051a93c4300c977403b0681815564680b02056c01a29a', 'Activo', 'Proponente');
INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (1003587553, 'CC', 'Edwin Yesid', 3008427536, 'edwin.rodriguez', '15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225', 'Activo', 'Administrador');
INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (1015442700, 'CC', 'Henry Sanchez', 3057786453, 'henry.sanchez', 'f0cb88954b657d42ce1903e0eb4f819d6270314309ad7bdb7ce50e40db4325d8', 'Activo', 'Proponente');


INSERT INTO Iniciativa (nombrePropuesta, id, descripcion, fechaInicio, area, usuario, estado_Propuesta) VALUES ('HoloLens Aid', '1', 'El uso de las HoloLens de Microsoft en ayuda para la atención de desastres naturales.', '04/16/2020', 'Ingeniería', 'angied.ruiz', 'En espera de revisión');
INSERT INTO Iniciativa (nombrePropuesta, id, descripcion, fechaInicio, area, usuario, estado_Propuesta) VALUES ('El arte de ingeniar', '2', 'El uso del arte para el desarrollo de la ingenieria.', '02/23/2020', 'Artes', 'angied.ruiz', 'En espera de revisión');
