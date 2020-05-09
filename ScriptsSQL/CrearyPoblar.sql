create extension pgcrypto;

CREATE TABLE IF NOT EXISTS Rol(tipo VARCHAR(14) PRIMARY KEY);

CREATE TABLE IF NOT EXISTS Usuario(id BIGINT NOT NULL, tid VARCHAR(2) NOT NULL, nombre VARCHAR(50) NOT NULL, telefono BIGINT NOT NULL, correo VARCHAR(50) PRIMARY KEY, clave VARCHAR(80) NOT NULL, estado VARCHAR(15) NOT NULL, rol VARCHAR(14) REFERENCES Rol(tipo));

ALTER TABLE Usuario ADD CONSTRAINT telefono_unico UNIQUE(telefono);

ALTER TABLE Usuario ADD CONSTRAINT id_unico UNIQUE(id);

CREATE TABLE IF NOT EXISTS Iniciativa(nombrePropuesta VARCHAR(50) NOT NULL, id SERIAL PRIMARY KEY, descripcion varchar(150)  NOT NULL, fechaInicio date, area varchar(50)  NOT NULL, usuario VARCHAR(50) REFERENCES Usuario(correo) NOT NULL, estado_Propuesta VARCHAR(30) NOT NULL);

ALTER TABLE Iniciativa ADD CONSTRAINT FK_Iniciativa_Usuario FOREIGN KEY (usuario) REFERENCES Usuario(correo);

ALTER TABLE Iniciativa ADD CONSTRAINT nombrePropuesta_unico UNIQUE(nombrePropuesta);

CREATE TABLE IF NOT EXISTS PalabraClave (id SERIAL PRIMARY KEY, palabraClave VARCHAR(20) NOT NULL);

ALTER TABLE palabraclave ADD CONSTRAINT uk_palabraclave_palabra UNIQUE (palabraclave);

CREATE TABLE IF NOT EXISTS PCIniciativa (ini_id INT NOT NULL, palabras_clave INT NOT NULL);

ALTER TABLE PCIniciativa ADD CONSTRAINT PK_PCIniciativa_ids PRIMARY KEY(ini_id, palabras_clave);

ALTER TABLE PCIniciativa ADD CONSTRAINT FK_PCIniciativa_iniciativa FOREIGN KEY (ini_id) REFERENCES Iniciativa(id);

ALTER TABLE PCIniciativa ADD CONSTRAINT FK_PCIniciativa_palabraClave FOREIGN KEY (palabras_clave) REFERENCES PalabraClave(id);

CREATE TABLE IF NOT EXISTS MeGusta (id SERIAL PRIMARY KEY, idIniciativa INT NOT NULL REFERENCES Iniciativa(id), idUsuario VARCHAR(50) NOT NULL REFERENCES Usuario(correo));

CREATE TABLE IF NOT EXISTS Comentario (id SERIAL PRIMARY KEY, idIniciativa INT NOT NULL REFERENCES Iniciativa(id), idUsuario VARCHAR(50) NOT NULL REFERENCES Usuario(correo), comentario VARCHAR(600) NOT NULL);

CREATE TABLE IF NOT EXISTS MeInteresa (id SERIAl PRIMARY KEY, idIniciativa INT NOT NULL REFERENCES Iniciativa(id), idUsuario VARCHAR(50) NOT NULL REFERENCES Usuario(correo));

CREATE TABLE public.grupo (idiniciativa int4 NOT NULL,nombre varchar(30) NOT NULL);

ALTER TABLE grupo ADD CONSTRAINT pk_grupo PRIMARY KEY (idiniciativa);

ALTER TABLE grupo ADD CONSTRAINT fk_grupo_iniciativa FOREIGN KEY (idiniciativa) REFERENCES iniciativa(id);

INSERT INTO Rol(tipo) VALUES ('Administrador');
INSERT INTO Rol(tipo) VALUES ('Proponente');
INSERT INTO Rol(tipo) VALUES ('PersonalPMO');
INSERT INTO Rol(tipo) VALUES ('Publico');

INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (9303130, 'CC', 'Angi Jimenez', 3186759533, 'angi.jimenez', '51588ae1ff9e45374efe138a856eec0479efc67a22de0f251e0021a9a2396dc2', 'Activo', 'Administrador'); 
INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (1019150998, 'CC', 'Daniela Ruiz', 3178484579, 'angied.ruiz', 'ceb55d033d440973d9c051a93c4300c977403b0681815564680b02056c01a29a', 'Activo', 'Proponente');
INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (1003587553, 'CC', 'Edwin Yesid', 3008427536, 'edwin.rodriguez', '15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225', 'Activo', 'Administrador'); clave 123456789
INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (1015442700, 'CC', 'Henry Sanchez', 3057786453, 'henry.sanchez', 'f0cb88954b657d42ce1903e0eb4f819d6270314309ad7bdb7ce50e40db4325d8', 'Activo', 'Proponente'); clave santafe1948
INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (1015442711, 'CC', 'Tony Stark', 3108427536, 'tony.stark', '8a9bcf1e51e812d0af8465a8dbcc9f741064bf0af3b3d08e6b0246437c19f7fb', 'Activo', 'Publico'); clave 987654321
INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (1015411111, 'CC', 'Sylvester Stallone', 3007527536, 'sylvester.stallone', c775e7b757ede630cd0aa1113bd102661ab38829ca52a6422ab782862f268646, 'Activo', 'Publico'); clave 1234567890
INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (0000000001, 'CC', 'Micke Lauren', 3007527000, 'micke.lauren', '83F7DF5566A3370312E8B04EA062F65AE5519B1122E78B2C5A4E5AB5A1B21A58', 'Activo', 'Publico');
INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (0000000011, 'CC', 'Leonel Messi', 3003337000, 'leo.messi', '19DDD05D90BBA86BCB51ED0F8CAC69B05419F0870EF3325DC5E54F1F84566597', 'Activo', 'Administrador');
INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (0000000111, 'CC', 'Homero Simpson', 3003337100, 'homero.simpson', '8246A162C5036E6ED1438150C7FFB0ECD5335C36AC23F68FAC87E9F972A2FE40', 'Activo', 'Publico');
INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (0000001111, 'CC', 'Ruben Angel', 3013337100, 'ruben.angel', 'D33FAE508CB2CC2813B14E85F434513B1CBD5C776EC372259F6C05EF3B9F1208', 'Activo', 'Publico');
INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (0000011111, 'CC', 'Gustavo Petro', 3113337100, 'gustavo.petro', 'B229498D5544C2979CE8255ABF8E18880BC7AB8731C07FC97AE81624E543DAE7', 'Activo', 'Publico'); clave petrosky
INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (0000111111, 'CC', 'Simon Bolivar', 3223337100, 'simon.bolivar', '49209538EF921DAA90A1F6290F8589EC3E803D326887BAE9207ACDA1A6576A69', 'Activo', 'Publico');
INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (0001111111, 'CC', 'Jessy Pinkman', 3003338000, 'jessy.pinkman', '08683A28DEBE09AA26B5F9CD0609E5F8217CBF4014069BC12F1E06575C3E2A41', 'Activo', 'Administrador'); clave chapas
INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (0011111111, 'CC', 'Raul Chaparro', 3003377000, 'raul.chaparro', 'EC408E3EF15C219435D4CD84F1603FA700013CD0E673A35A2A384859FF2097F1', 'Activo', 'Administrador');
INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (0111111111, 'CC', 'Oscar Cordoba', 3003377333, 'oscar.cordoba', 'D9EE6370ACF34BDCAE16A61354C73EB1EE735DED71C5AB8F8CDEAB724A252646', 'Activo', 'Administrador');
INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (1111111111, 'CC', 'Juan Londoño', 3003399333, 'juan.londoño', '0E68C8A1ECA762565DE23D19827FB4D20DCE31B2529D5142793838881B18DAFA', 'Activo', 'Administrador');
INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (1000000000, 'CC', 'jhony tapia', 3003399387, 'jhony.tapia', 'BF6D7205EC6E4F3077609E3DC16CB4AF0463F2355C5AA27754E5E62BF39D2AAC', 'Activo', 'Proponente');
INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (1100000000, 'CC', 'Carlos Duty', 3103399387, 'carlos.duty', '02164654C05DAD8F223ED20C34C4C836D2A7BED5EF41DA88497FFC8E48EBE2DA', 'Activo', 'Proponente');
INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (1110000000, 'CC', 'Pilar Trujillo', 3103399334, 'pilar.trujillo', '3D277280559D1C8F286F7C608A6148AA0FEE3CCA7291D5CE70D3D7729BB6BA58', 'Activo', 'Proponente');
INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (1111000000, 'CC', 'Ricardo Arjona', 3183399334, 'ricardo.arjona', 'DA9A9965788AAA0EC9E07BF65EE3AE94D7BECCF99B0A3E41209551E5A54FB4D0', 'Activo', 'Proponente');
INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (1111100000, 'CC', 'Steve Jobs', 3183301334, 'steve.jobs', '9F5F911DD8D4D232C990DA679F5EE29DFB4AF41F722A2739833408ECE62D72C1', 'Activo', 'Proponente');
INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (1111110000, 'CC', 'German Garmendia', 3183753334, 'german.garmendia', '791F65752840C6D4CEB629D3FE64F0986F4FDA8ECC807A797C33D6B0671A13C4', 'Activo', 'PersonalPMO');

INSERT INTO Iniciativa (nombrePropuesta, id, descripcion, fechaInicio, area, usuario, estado_Propuesta) VALUES ('HoloLens Aid', '1', 'El uso de las HoloLens de Microsoft en ayuda para la atención de desastres naturales.', 2019-12-23, 'Ingeniería', 'angied.ruiz', 'En espera de revisión');
INSERT INTO Iniciativa (nombrePropuesta, id, descripcion, fechaInicio, area, usuario, estado_Propuesta) VALUES ('El arte de ingeniar', '2', 'El uso del arte para el desarrollo de la ingenieria.', 2020-01-09, 'Artes', 'angied.ruiz', 'En espera de revisión');
INSERT INTO Iniciativa (nombrePropuesta, id, descripcion, fechaInicio, area, usuario, estado_Propuesta) VALUES ('Reducir costo matricula', '3', 'Debido al semestre virtual, no es justo que cobren lo mismo.', 2020-02-23, 'Economía', 'henry.sanchez', 'Proyecto');
INSERT INTO Iniciativa (nombrePropuesta, id, descripcion, fechaInicio, area, usuario, estado_Propuesta) VALUES ('Primeros auxilios', '4', 'Curso de primeros auxilios para la comidad ECI.', 2019-08-16, 'Ciencias de la salud', 'angied.ruiz', 'En revisión');
INSERT INTO Iniciativa (nombrePropuesta, id, descripcion, fechaInicio, area, usuario, estado_Propuesta) VALUES ('Fundamentacion matemática para todos', '5', 'Brindar fundamentacion matematica a todo estudiante de la ECI que así lo desee.', 2019-12-09, 'Matemáticas', 'angied.ruiz', 'Solucionado');
INSERT INTO Iniciativa (nombrePropuesta, id, descripcion, fechaInicio, area, usuario, estado_Propuesta) VALUES ('La ingenieria detrás del cuerpo humano', '6', 'Brindar una aproximación a la maravilla del cuerpo humano en su interior.', 2020-02-02, 'Ciencias Naturales', 'henry.sanchez', 'En espera de revisión');
INSERT INTO Iniciativa (nombrePropuesta, id, descripcion, fechaInicio, area, usuario, estado_Propuesta) VALUES ('Política para ingenieros', '7', 'Dar un espacio a un semillero para los estudiantes de la ECI intersados en la política.', 2020-02-02, 'Ciencias Sociales', 'angied.ruiz', 'Proyecto');
INSERT INTO Iniciativa (nombrePropuesta, descripcion,  area, usuario, estado_Propuesta) VALUES ('Mishapp', 'Aplicacion disminuir muertes en accidentes de transito', 'Ingeniería', 'carlos.duty', 'En espera de revisión');
INSERT INTO Iniciativa (nombrePropuesta, descripcion,  area, usuario, estado_Propuesta) VALUES ('Sillas kioscos', 'Aumentar las sillas de los kioscos de la Escuela', 'Administración', 'jhony.tapia', 'En espera de revisión');
INSERT INTO Iniciativa (nombrePropuesta, descripcion,  area, usuario, estado_Propuesta) VALUES ('Olimpiadas matematicas', 'Realizar unas olimpiadas matematicas', 'Matemáticas', 'ricardo.arjona', 'En espera de revisión');
INSERT INTO Iniciativa (nombrePropuesta, descripcion,  area, usuario, estado_Propuesta) VALUES ('Curso teatral', 'Que los estudiantes puedan abrir un curso teatral', 'Artes', 'pilar.trujillo', 'En espera de revisión');
INSERT INTO Iniciativa (nombrePropuesta, descripcion,  area, usuario, estado_Propuesta) VALUES ('Mejorar estado de las canchas', 'Hacer un mejoramiento a las canchas de la escuela', 'Ciencias Naturales', 'steve.jobs', 'En espera de revisión');

INSERT INTO PalabraClave (id, palabraClave) VALUES (1, 'Equipo');
INSERT INTO PalabraClave (id, palabraClave) VALUES (2, 'Tecnología');
INSERT INTO PalabraClave (id, palabraClave) VALUES (3, 'Ingeniería');
INSERT INTO PalabraClave (id, palabraClave) VALUES (4, 'Salud');
INSERT INTO PalabraClave (id, palabraClave) VALUES (5, 'Estudiantes');
