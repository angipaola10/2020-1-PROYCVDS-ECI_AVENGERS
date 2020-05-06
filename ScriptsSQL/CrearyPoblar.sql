create extension pgcrypto;

CREATE TABLE IF NOT EXISTS Rol(tipo VARCHAR(14) PRIMARY KEY);

CREATE TABLE IF NOT EXISTS Estado(id SERIAL PRIMARY KEY, estado VARCHAR(30));

CREATE TABLE IF NOT EXISTS Usuario(id BIGINT NOT NULL, tid VARCHAR(2) NOT NULL, nombre VARCHAR(50) NOT NULL, telefono BIGINT NOT NULL, correo VARCHAR(50) PRIMARY KEY, clave VARCHAR(20) NOT NULL, estado VARCHAR(15) NOT NULL, rol VARCHAR(14) REFERENCES Rol(tipo));
ALTER TABLE Usuario ADD CONSTRAINT telefono_unico UNIQUE(telefono);
ALTER TABLE Usuario ADD CONSTRAINT id_unico UNIQUE(id);

CREATE TABLE IF NOT EXISTS Iniciativa(nombrePropuesta VARCHAR(50) NOT NULL, id SERIAL PRIMARY KEY, descripcion varchar(150)  NOT NULL, fechaInicio date, area varchar(50)  NOT NULL, usuario VARCHAR(50) REFERENCES Usuario(correo) NOT NULL, estado_Propuesta VARCHAR(30) NOT NULL);

ALTER TABLE Iniciativa ADD CONSTRAINT FK_Iniciativa_Usuario FOREIGN KEY (usuario) REFERENCES Usuario(correo);

ALTER TABLE Iniciativa ADD CONSTRAINT nombrePropuesta_unico UNIQUE(nombrePropuesta);

CREATE TABLE IF NOT EXISTS PalabraClave (id SERIAL PRIMARY KEY, palabraClave VARCHAR(20) NOT NULL);

CREATE TABLE IF NOT EXISTS PCIniciativa (ini_id INT NOT NULL, palabras_clave INT NOT NULL);

ALTER TABLE PCIniciativa ADD CONSTRAINT PK_PCIniciativa_ids PRIMARY KEY(ini_id, palabras_clave);

ALTER TABLE PCIniciativa ADD CONSTRAINT FK_PCIniciativa_iniciativa FOREIGN KEY (ini_id) REFERENCES Iniciativa(id);

ALTER TABLE PCIniciativa ADD CONSTRAINT FK_PCIniciativa_palabraClave FOREIGN KEY (palabras_clave) REFERENCES PalabraClave(id);

CREATE TABLE IF NOT EXISTS MeGusta (id SERIAL PRIMARY KEY, idIniciativa INT NOT NULL REFERENCES Iniciativa(id), idUsuario VARCHAR(50) NOT NULL REFERENCES Usuario(correo));

CREATE TABLE IF NOT EXISTS Comentario (id SERIAL PRIMARY KEY, idIniciativa INT NOT NULL REFERENCES Iniciativa(id), idUsuario VARCHAR(50) NOT NULL REFERENCES Usuario(correo), comentario VARCHAR(600) NOT NULL);

CREATE TABLE IF NOT EXISTS MeInteresa (id SERIAl PRIMARY KEY, idIniciativa INT NOT NULL REFERENCES Iniciativa(id), idUsuario VARCHAR(50) NOT NULL REFERENCES Usuario(correo));

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
INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (1015442711, 'CC', 'Tony Stark', 3108427536, 'tony.stark', '8a9bcf1e51e812d0af8465a8dbcc9f741064bf0af3b3d08e6b0246437c19f7fb', 'Activo', 'Publico');
INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (1015411111, 'CC', 'Sylvester Stallone', 3007527536, 'sylvester.stallone', c775e7b757ede630cd0aa1113bd102661ab38829ca52a6422ab782862f268646, 'Activo', 'Publico');


INSERT INTO Iniciativa (nombrePropuesta, id, descripcion, fechaInicio, area, usuario, estado_Propuesta) VALUES ('HoloLens Aid', '1', 'El uso de las HoloLens de Microsoft en ayuda para la atención de desastres naturales.', 2019-12-23, 'Ingeniería', 'angied.ruiz', 'En espera de revisión');
INSERT INTO Iniciativa (nombrePropuesta, id, descripcion, fechaInicio, area, usuario, estado_Propuesta) VALUES ('El arte de ingeniar', '2', 'El uso del arte para el desarrollo de la ingenieria.', 2020-01-09, 'Artes', 'angied.ruiz', 'En espera de revisión');
INSERT INTO Iniciativa (nombrePropuesta, id, descripcion, fechaInicio, area, usuario, estado_Propuesta) VALUES ('Reducir costo matricula', '3', 'Debido al semestre virtual, no es justo que cobren lo mismo.', 2020-02-23, 'Economía', 'henry.sanchez', 'Proyecto');
INSERT INTO Iniciativa (nombrePropuesta, id, descripcion, fechaInicio, area, usuario, estado_Propuesta) VALUES ('Primeros auxilios', '4', 'Curso de primeros auxilios para la comidad ECI.', 2019-08-16, 'Ciencias de la salud', 'angied.ruiz', 'En revisión');
INSERT INTO Iniciativa (nombrePropuesta, id, descripcion, fechaInicio, area, usuario, estado_Propuesta) VALUES ('Fundamentacion matemática para todos', '5', 'Brindar fundamentacion matematica a todo estudiante de la ECI que así lo desee.', 2019-12-09, 'Matemáticas', 'angied.ruiz', 'Solucionado');
INSERT INTO Iniciativa (nombrePropuesta, id, descripcion, fechaInicio, area, usuario, estado_Propuesta) VALUES ('La ingenieria detrás del cuerpo humano', '6', 'Brindar una aproximación a la maravilla del cuerpo humano en su interior.', 2020-02-02, 'Ciencias Naturales', 'henry.sanchez', 'En espera de revisión');
INSERT INTO Iniciativa (nombrePropuesta, id, descripcion, fechaInicio, area, usuario, estado_Propuesta) VALUES ('Política para ingenieros', '7', 'Dar un espacio a un semillero para los estudiantes de la ECI intersados en la política.', 2020-02-02, 'Ciencias Sociales', 'angied.ruiz', 'Proyecto');

INSERT INTO PalabraClave (id, palabraClave) VALUES (1, 'Equipo');
INSERT INTO PalabraClave (id, palabraClave) VALUES (2, 'Tecnología');
INSERT INTO PalabraClave (id, palabraClave) VALUES (3, 'Ingeniería');
INSERT INTO PalabraClave (id, palabraClave) VALUES (4, 'Salud');
INSERT INTO PalabraClave (id, palabraClave) VALUES (5, 'Estudiantes');