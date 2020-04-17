CREATE TABLE IF NOT EXISTS Rol(tipo VARCHAR(14) PRIMARY KEY);

CREATE TABLE IF NOT EXISTS Usuario(id BIGINT NOT NULL, tid VARCHAR(2) NOT NULL, nombre VARCHAR(50) NOT NULL, telefono BIGINT NOT NULL, correo VARCHAR(50) PRIMARY KEY, clave VARCHAR(20) NOT NULL, estado VARCHAR(15) NOT NULL, rol VARCHAR(12) REFERENCES Rol(tipo));

ALTER TABLE Usuario ADD CONSTRAINT id_unico UNIQUE(id);

CREATE TABLE IF NOT EXISTS Iniciativa (nombrePropuesta VARCHAR(50) NOT NULL, id SERIAL PRIMARY KEY, descripcion varchar(150)  NOT NULL, fechaInicio date  NOT NULL, area varchar(100)  NOT NULL, usuario_id INT REFERENCES Usuario(id), estado VARCHAR(50) NOT NULL);

ALTER TABLE Iniciativa ADD CONSTRAINT nombrePropuesta_unico UNIQUE(nombrePropuesta);

CREATE TABLE IF NOT EXISTS PalabraClave (id INT PRIMARY KEY, palabraClave VARCHAR(20) NOT NULL);

INSERT INTO Rol(tipo) VALUES ('Administrador');
INSERT INTO Rol(tipo) VALUES ('Proponente');
INSERT INTO Rol(tipo) VALUES ('PersonalPMO');
INSERT INTO Rol(tipo) VALUES ('Publico');

INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (9303130, 'CC', 'Angi Jimenez', 3186759533, 'angi.jimenez', 'Angie2020', 'Activo', 'Administrador');
INSERT INTO Usuario (id, tid, nombre, telefono, correo, clave, estado, rol) VALUES (1019150998, 'CC', 'Daniela Ruiz', 3178484579, 'angied.ruiz', 'RuizAlf123', 'Activo', 'Proponente');
