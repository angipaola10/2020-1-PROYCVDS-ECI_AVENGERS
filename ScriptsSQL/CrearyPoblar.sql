CREATE TABLE IF NOT EXISTS Rol(tipo VARCHAR(14) PRIMARY KEY);

CREATE TABLE IF NOT EXISTS Estado(id SERIAL PRIMARY KEY, estado VARCHAR(30));

ALTER TABLE Estado ADD CONSTRAINT estado_unico UNIQUE(estado);

CREATE TABLE IF NOT EXISTS Usuario(id BIGINT NOT NULL, tid VARCHAR(2) NOT NULL, nombre VARCHAR(50) NOT NULL, telefono BIGINT NOT NULL, correo VARCHAR(50) PRIMARY KEY, clave VARCHAR(20) NOT NULL, estado VARCHAR(15) NOT NULL, rol VARCHAR(14) REFERENCES Rol(tipo));

ALTER TABLE Usuario ADD CONSTRAINT id_unico UNIQUE(id);

CREATE TABLE IF NOT EXISTS Iniciativa(nombrePropuesta VARCHAR(50) NOT NULL, id SERIAL PRIMARY KEY, descripcion varchar(150)  NOT NULL, fechaInicio date, area varchar(50)  NOT NULL, usuario VARCHAR(50) REFERENCES Usuario(correo), estado_Propuesta VARCHAR(30) REFERENCES Estado(estado));

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


INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (9303130, 'CC', 'Angi Jimenez', 3186759533, 'angi.jimenez', 'Angie2020', 'Activo', 'Administrador');
INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (1019150998, 'CC', 'Daniela Ruiz', 3178484579, 'angied.ruiz', 'RuizAlf123', 'Activo', 'Proponente');
INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (1003587553, 'CC', 'Edwin Yesid', 3008427536, 'edwin.rodriguez', '123456789', 'Activo', 'Administrador');
INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (1015442700, 'CC', 'Henry Sanchez', 3057786453, 'henry.sanchez', 'santafe1948', 'Activo', 'Proponente');


INSERT INTO Iniciativa (nombrePropuesta, id, descripcion, fechaInicio, area, usuario, estado_Propuesta) VALUES ('HoloLens Aid', '1', 'El uso de las HoloLens de Microsoft en ayuda para la atención de desastres naturales.', '04/16/2020', 'Ingeniería', 'angied.ruiz', 'En espera de revisión');
INSERT INTO Iniciativa (nombrePropuesta, id, descripcion, fechaInicio, area, usuario, estado_Propuesta) VALUES ('El arte de ingeniar', '2', 'El uso del arte para el desarrollo de la ingenieria.', '02/23/2020', 'Artes', 'angied.ruiz', 'En espera de revisión');
